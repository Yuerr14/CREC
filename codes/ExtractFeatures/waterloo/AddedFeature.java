package waterloo;

import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

import edu.pku.sei.codeclone.predictor.CloneGroupFeature;
import edu.pku.sei.codeclone.predictor.CloneInstanceFeature;
import edu.pku.sei.codeclone.predictor.History;
import edu.pku.sei.codeclone.predictor.HistoryGen;
import edu.pku.sei.codeclone.predictor.MyCloneClass;
import edu.pku.sei.codeclone.predictor.MyFragment;
import edu.pku.sei.codeclone.predictor.MyVersion;
import edu.pku.sei.codeclone.predictor.MyVersionList;
import edu.pku.sei.codeclone.predictor.RefactoredInstance;
import edu.pku.sei.codeclone.predictor.code.JavaClass;
import edu.pku.sei.codeclone.predictor.code.MethodVisitor;
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import waterloo.History.GroupHistoryFeature;
import waterloo.History.InstanceHistoryFeature;
import waterloo.History.NewRefactorCommitLocator;
import waterloo.Util.GlobalSettings;
import edu.pku.sei.codeclone.predictor.code.ClassNode;

public class AddedFeature {
	String refactorFileLabel = "refactored";
	String unrefactorFileLabel = "unrefactored";
	String arffFileSuffix = ".arff";
	long refactoredGroupCnt = 0, unrefactoredGroupCnt = 0;

	String repoFolderPath = null, cloneDataPath = null;
		
	String oldArffFilePath;
	String newArffFilePath;
	String tmpArffFilePath;
	
	String projectPath = null, filteredLogFilePath = null, totalLogFilePath = null;
	ArrayList<String> filteredCommitList;
	ArrayList<String> totalCommitList;
	int totalAuthorNum = 0, totalCommitNum = 0;
	
	String tmpFolderPath = "/home/sonia/NewExperiment/NewFolder-after6.26/tmpVersions/";
	
	Git git;
	Repository repo;
	
	public AddedFeature(String repoFolderPath, String cloneDataPath, String oldArffFilePath, String projectPath, String filteredLogFilePath, String totalLogFilePath) {
		this.cloneDataPath = cloneDataPath;
		this.oldArffFilePath = oldArffFilePath;
		int dotIndex = oldArffFilePath.lastIndexOf(".");
		this.newArffFilePath = oldArffFilePath.substring(0, dotIndex) + "Add" + oldArffFilePath.substring(dotIndex);
		this.tmpArffFilePath = oldArffFilePath.substring(0, oldArffFilePath.lastIndexOf(GlobalSettings.pathSep) + 1)
				+ "tmpAdd" + oldArffFilePath.substring(dotIndex);
		this.repoFolderPath = repoFolderPath;
		
		
		this.projectPath = projectPath;
		
		this.tmpFolderPath += projectPath.substring(projectPath.lastIndexOf("/") + 1) + "/";
		System.out.println(tmpFolderPath);
		
		this.filteredLogFilePath = filteredLogFilePath;
		this.totalLogFilePath = totalLogFilePath;

		filteredCommitList = new ArrayList<String>();
		totalCommitList = new ArrayList<String>();
		setCommitList(this.filteredLogFilePath, filteredCommitList);
		setCommitList(this.totalLogFilePath, totalCommitList);
		this.initStudyRangeInfo();

		System.out.println("TotalCommitNum:" + totalCommitNum);
		System.out.println("TotalAuthorNum:" + totalAuthorNum);

		File gitFile = new File(projectPath + GlobalSettings.pathSep + ".git");
		try {
			git = Git.open(gitFile);
			repo = git.getRepository();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setCommitList(String logFilePath, ArrayList<String> commitList) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(logFilePath));
			String line = null;
			while ((line = in.readLine()) != null) {
				commitList.add(line);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initStudyRangeInfo() {
		HashSet<String> authorSet = new HashSet<String>();
		for (String commitStr : this.totalCommitList) {
			if (!commitStr.contains(","))
				continue;
			String[] tmp = commitStr.split(",");
			if (tmp.length >= 5) {
				this.totalCommitNum++;
				authorSet.add(tmp[2]);
			}
		}
		System.out.println("TotalAuthorSet:" + authorSet);
		this.totalAuthorNum = authorSet.size();
	}
	
	
	public String getArffFilePath() {
		return newArffFilePath;
	}
	
	public void computeAddedFeature() {
		try {
			File tmpHisArffFile = new File(this.tmpArffFilePath);
			if (!tmpHisArffFile.exists())
				tmpHisArffFile.createNewFile();
			PrintWriter pw = new PrintWriter(tmpHisArffFile);

			File cloneFileFolder = new File(cloneDataPath);
			ArrayList<File> unrefactorCloneFileList = new ArrayList<File>();
			ArrayList<File> refactorCloneFileList = new ArrayList<File>();
			for (File cloneFile : cloneFileFolder.listFiles()) {
				String fileName = cloneFile.getName();
				if (fileName.contains("readable"))
					continue;
				if (fileName.contains(unrefactorFileLabel))
					unrefactorCloneFileList.add(cloneFile);
				else if (fileName.contains(refactorFileLabel))
					refactorCloneFileList.add(cloneFile);
			}
			Collections.sort(unrefactorCloneFileList, new SortByVersion());
			Collections.sort(refactorCloneFileList, new SortByVersion());
			System.out.print("afterReadInstanceData:");
			outputCurrentTime();

			for (File cloneFile : unrefactorCloneFileList) {
				processSingleCloneFile(cloneFile, unrefactorFileLabel, pw);
			}
			System.out.print("afterProcessingUnRefactorClone:");
			outputCurrentTime();

			for (File cloneFile : refactorCloneFileList) {
				processSingleCloneFile(cloneFile, refactorFileLabel, pw);
			}
			System.out.print("afterProcessingRefactorClone:");
			outputCurrentTime();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processSingleCloneFile(File cloneFile, String cloneFileLabel, PrintWriter pw) {
		Vector<RefactoredInstance> refactoredInsList = new Vector<RefactoredInstance>();
		List<MyCloneClass> unrefactoredCloneList = new ArrayList<MyCloneClass>();

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cloneFile));
			if (cloneFileLabel.equals(refactorFileLabel))
				refactoredInsList = (Vector<RefactoredInstance>) ois.readObject();
			else
				unrefactoredCloneList = (List<MyCloneClass>) ois.readObject();
			if (cloneFileLabel.equals(refactorFileLabel)) {
				for (RefactoredInstance refactoredIns : refactoredInsList) {
					Vector<MyFragment> frags = refactoredIns.getFragments();
					computeSingleCloneGroup(frags, cloneFileLabel, pw, refactoredIns);
					System.out.print("AfterGettingOneRefactorInsFeature:");
					this.outputCurrentTime();
				}
			} else {
				for (MyCloneClass clazz : unrefactoredCloneList) {
					List<MyFragment> frags = clazz.getFragments();
					computeSingleCloneGroup(frags, cloneFileLabel, pw, null);
					System.out.print("AfterGettingOneUnrefactorInsFeature:");
					this.outputCurrentTime();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void computeSingleCloneGroup(List<MyFragment> frags, String cloneFileLabel, PrintWriter pw, RefactoredInstance refactorIns) {
				//add locator
				String fragHash = null, methodHash = null;
				String fragBaseHash = this.getHashStrFromFilterLog(frags.get(0).getVersionRepoName());
				fragHash = fragBaseHash;
				NewRefactorCommitLocator locator = new NewRefactorCommitLocator(this.projectPath, this.filteredLogFilePath,
						this.totalLogFilePath, this.repoFolderPath);
				locator.setHashvalueList();
				if (refactorIns != null) {
					methodHash = locator.getRealChangeID(refactorIns, GlobalSettings.simi);
				}
				if (methodHash != null)
					fragHash = getPreHash(methodHash);
				//System.out.println("Frag Hash:" + fragHash + " " + fragBaseHash);
				List<MyFragment> newfrags = null;
				if (fragHash.equals(fragBaseHash)) {
					newfrags = frags;
				}
				else {
					newfrags = rebuildFrags(frags, fragHash);
				}
				frags = newfrags;
				
				//add end
		
		
		
		// compute un/refactorGroupCount
		if (!isSingleGroupParserable(frags)) {
			return;
		}
		String newCloneFileLabel = null;
		if (cloneFileLabel.equals(refactorFileLabel)) {
			refactoredGroupCnt++;
			newCloneFileLabel = "refactor" + refactoredGroupCnt;
		} else if (cloneFileLabel.equals(unrefactorFileLabel)) {
			unrefactoredGroupCnt++;
			newCloneFileLabel = "unrefactor" + unrefactoredGroupCnt;
		}
		
		List<AddedInstanceFeature> cloneInsFeatureListForOneGroup = new ArrayList<AddedInstanceFeature>();
		
		for (MyFragment frag : frags) {
			if (!isParserable(frag))
				continue;
			AddedInstanceFeature cloneInsFeature = new AddedInstanceFeature(frag);
			cloneInsFeatureListForOneGroup.add(cloneInsFeature);
		}
		
		AddedGroupFeature cloneGroupFeature = new AddedGroupFeature(frags);

		for (AddedInstanceFeature feature : cloneInsFeatureListForOneGroup) {
			pw.print(newCloneFileLabel + "," + feature.toString());
			// Write one feature for a clone group
			break;
		}
		
		pw.print("," + cloneGroupFeature.treeDistance);
		pw.println();
		pw.flush();
	}
	
	private List<MyFragment> rebuildFrags(List<MyFragment> frags, String hash) {
		String originBasePath = repoFolderPath + "/00000/";
		String newBasePath = tmpFolderPath + hash + "/";
		List<MyFragment> ret = new Vector<MyFragment>();
		for (MyFragment frag : frags) {
			String predPath = frag.getFilePath();
			String nextPath = newBasePath + predPath.substring(originBasePath.length());
			//System.out.println("pred path:" + predPath);
			//System.out.println("next path:" + nextPath);
			int[] linemap = buildLineMap(nextPath, predPath);
			if (linemap == null) {
				System.out.println("linemap is null");
				continue;
			}

			if (linemap.length <= frag.startLine - 1) {
				System.out.println("Wrong here. frag:\n" + frag);
				System.out.println("linemap:\n" + linemap.length);
				continue;
			}
			int nextStart, nextEnd;
			
			File file = new File(predPath);
			if (!file.exists())
				System.out.println("predFile unExist");
			
			file = new File(nextPath);
			if (!file.exists())
				System.out.println("nextFile unExist");

			int predstart = frag.startLine, predend = frag.endLine;
			//System.out.println("predFragLineRange:" + predstart + "-" + predend);
			nextStart = linemap[predstart - 1] + 1;
			if (linemap[predstart - 1] == -1) {
				for (int i = predstart - 1; i <= predend - 1; i++)
					if (linemap[i] != -1) {
						nextStart = linemap[i];
						break;
					}
			}
			nextEnd = linemap[predend - 1] + 1;
			if (linemap[predend - 1] == -1) {
				for (int i = predend - 1; i >= predstart - 1; i--)
					if (linemap[i] != -1) {
						nextEnd = linemap[i];
						break;
					}
			}
			if (nextStart <= 0 || nextEnd <= 0) {
				System.out.println("predPath:" + predPath);
				System.out.println("nextPath:" + nextPath);
				System.out.println("predRange:" + predstart + " " + predend);
				System.out.println("nextRange:" + nextStart + " " + nextEnd);
				continue;
			}
			//System.out.println("nextLineRange:" + nextStart + "-" + nextEnd);
			MyFragment newFrag = new MyFragment(nextStart, nextEnd, -1, -1, nextPath, -1, hash);
			ret.add(newFrag);
		}
		return ret;
	}
	
	public int[] buildLineMap(String curPath, String predPath) {
        
        File tempFile = new File(curPath);
        if(!tempFile.exists()){
            return null;
        }
        tempFile = new File(predPath);
        if (!tempFile.exists()){
        	return null;
        }
    
		int[] linemap = null;
        if(linemap==null){
        	List<String> currentValue = getSrc(curPath);
            List<String> predValue = getSrc(predPath);
            Patch p = DiffUtils.diff(predValue, currentValue);
            List<Delta> deltas = p.getDeltas();
                
            for(int i = 0; i<deltas.size(); i++){
                for(int j = i+1; j<deltas.size(); j++){
                    if(deltas.get(i).getOriginal().getPosition()<deltas.get(j).getOriginal().getPosition()){
                        Delta d = deltas.get(i);
                        deltas.set(i, deltas.get(j));
                        deltas.set(j, d);
                    }
                }
            }
        
            linemap = new int[predValue.size() + 1];
            int index1 = 0;
            int index2 = 0;
        
            for(int k = deltas.size()-1;k>=0;k--){
                Delta del = deltas.get(k);
                int lineNumber = del.getOriginal().getPosition();
                int linesOld = del.getOriginal().getLines().size();
                int linesNew = del.getRevised().getLines().size();
                for(int i = index1; i<lineNumber;i++){
                    linemap[i] = index2; 
                    index2++;
                }
                index1=lineNumber;
                if(del.getType().equals(Delta.TYPE.INSERT)) {
                    index2+=linesNew;
                }else if(del.getType().equals(Delta.TYPE.DELETE)){
                    for(int i = index1; i<index1+linesOld; i++){
                        linemap[i] = -1;
                    }
                    index1+=linesOld;
                }else if(del.getType().equals(Delta.TYPE.CHANGE)){
                    for(int i = index1; i<index1+linesOld; i++){
                        linemap[i] = -1;
                    }
                    index1+=linesOld;
                    index2+=linesNew;
                }
            }
            for(int i = index1; i<predValue.size();i++){
                linemap[i] = index2;
                index2++;
            }
        }
        return linemap;
	}

public static List<String> getSrc(String path) {
    // TODO Auto-generated method stub
    List<String> lines = new ArrayList<String>();
    try{
        BufferedReader in = new BufferedReader(new FileReader(path));
        for(String line = in.readLine(); line!=null; line = in.readLine()){
            lines.add(line);
        }
        lines.add("");
        in.close();
    }catch(IOException e){
        e.printStackTrace();
    }
    return lines;
}
	
	private String getPreHash(String nowHash) {
		int which = -1;
		for (int i = 0; i < totalCommitList.size(); i++) {
			if (totalCommitList.get(i).startsWith(nowHash)) {
				which = i;
				break;
			}
		}
		String ret;
		String commit = totalCommitList.get(which + 1);
		ret = commit.substring(0, commit.indexOf(","));
		return ret;
	}
	
	private String getHashStrFromFilterLog(String repoFolderName) {
		int which = filteredCommitList.size() - Integer.parseInt(repoFolderName);
		String commitStr = filteredCommitList.get(which);
		String[] tmp = commitStr.split(",");
		if (tmp.length >= 5)
			return tmp[0];
		return null;
	}
	
	private boolean isSingleGroupParserable(List<MyFragment> frags) {
		for (MyFragment frag : frags) {
			if (isParserable(frag))
				return true;
		}
		return false;
	}


	private boolean isParserable(MyFragment frag) {
		File file = new File(frag.getFilePath());
		try {
			CompilationUnit cu = JavaParser.parse(file);
		} catch (Exception e) {
			return false;
		} catch (Error e) {
			return false;
		}
		return true;
	}


	private void combineFeature(String oldArffFilePath, String tmpArffFilePath, String newArffFilePath) {
		try {
			File oldArffFile = new File(oldArffFilePath);
			File tmpArffFile = new File(tmpArffFilePath);
			BufferedReader brOld = new BufferedReader(new FileReader(oldArffFile));
			BufferedReader brTmp = new BufferedReader(new FileReader(tmpArffFile));
			File newArffFile = new File(newArffFilePath);
			if (!newArffFile.exists())
				newArffFile.createNewFile();
			PrintWriter pw = new PrintWriter(new FileWriter(newArffFile));
			String featureStr = null;
			boolean afterData = false;
			while ((featureStr = brOld.readLine()) != null) {
				// System.out.println("featureStr:"+featureStr);
				if (!afterData) {
					if (featureStr.startsWith("@attribute cloneEval {refactored, unrefactored}")) {
						pw.println("@attribute isTest {true, false}");
						pw.println("@attribute globalVars real");
						pw.println("@attribute treeDis real");
						pw.println(featureStr);
						pw.println(brOld.readLine());
						pw.println(brOld.readLine());
						afterData = true;
					} else
						pw.println(featureStr);
				} else {
					String hisFeatureStr = brTmp.readLine();
					String hisFeature = hisFeatureStr.substring(hisFeatureStr.indexOf(","));

					String oldFeatureWithoutLabel = featureStr.substring(0, featureStr.lastIndexOf(","));
					String label = featureStr.substring(featureStr.lastIndexOf(","));

					String newFeatureStr = oldFeatureWithoutLabel + hisFeature + label;
					pw.println(newFeatureStr);
				}
			}
			pw.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void combineFeature() {
		combineFeature(this.oldArffFilePath, this.tmpArffFilePath, this.newArffFilePath);
	}

	private void outputCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(new Date()));
	}
}
