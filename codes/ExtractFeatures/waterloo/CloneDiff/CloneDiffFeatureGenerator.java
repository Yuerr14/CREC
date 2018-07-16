package waterloo.CloneDiff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

import edu.pku.sei.codeclone.predictor.MyCloneClass;
import edu.pku.sei.codeclone.predictor.MyFragment;
import edu.pku.sei.codeclone.predictor.RefactoredInstance;
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import mcidiff.main.TokenMCIDiff;
import mcidiff.model.CloneInstance;
import mcidiff.model.CloneSet;
import mcidiff.model.TokenMultiset;
import waterloo.SortByVersion;
import waterloo.History.NewRefactorCommitLocator;
import waterloo.Util.GlobalSettings;

public class CloneDiffFeatureGenerator {
	String refactorFileLabel = "refactored";
	String unrefactorFileLabel = "unrefactored";
	long refactoredGroupCnt = 0, unrefactoredGroupCnt = 0;
	String cloneDataPath=null,oldArffFilePath=null;
	String tmpArffFilePath=null,newArffFilePath=null;
	String tmpNorArffFilePath=null,norArffFilePath=null;
	
	public int cnt = 0;
	String repoFolderPath = null;
	String projectPath = null, filteredLogFilePath = null, totalLogFilePath = null;
	ArrayList<String> filteredCommitList;
	ArrayList<String> totalCommitList;
	int totalAuthorNum = 0, totalCommitNum = 0;
	
	String tmpFolderPath = "/home/sonia/NewExperiment/NewFolder-after6.26/tmpVersions/";
	
	Git git;
	Repository repo;
	
	public CloneDiffFeatureGenerator(String cloneDataPath,String oldArffFilePath, String projectPath, String filteredLogFilePath, String totalLogFilePath, 
			String repoFolderPath){
		this.cloneDataPath=cloneDataPath;
		this.oldArffFilePath=oldArffFilePath;
		int dotIndex=oldArffFilePath.lastIndexOf(".");
		this.newArffFilePath=oldArffFilePath.substring(0, dotIndex)+"Clonediff"+oldArffFilePath.substring(dotIndex);
		this.norArffFilePath=oldArffFilePath.substring(0, dotIndex)+"ClonediffNor"+oldArffFilePath.substring(dotIndex);
		this.tmpArffFilePath=oldArffFilePath.substring(0,oldArffFilePath.lastIndexOf(GlobalSettings.pathSep)+1)+"tmpDiff"+oldArffFilePath.substring(dotIndex);	
		this.tmpNorArffFilePath=oldArffFilePath.substring(0,oldArffFilePath.lastIndexOf(GlobalSettings.pathSep)+1)+"tmpNorDiff"+oldArffFilePath.substring(dotIndex);	
	
		this.projectPath = projectPath;
		this.repoFolderPath = repoFolderPath;
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
	
	
	public String getNewArffFilePath(){
		return this.newArffFilePath;
	}
	
	public String getNorArffFilePath() {
		return this.norArffFilePath;
	}
	
	private void outputCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(new Date()));
	}
	
	public void computeCloneDiffFeature() {
		try {
			File tmpDiffArffFile=new File(this.tmpArffFilePath);
			File tmpNorDiffArffFile = new File(this.tmpNorArffFilePath);
			if(!tmpDiffArffFile.exists())
				tmpDiffArffFile.createNewFile();
			if (!tmpNorDiffArffFile.exists())
				tmpNorDiffArffFile.createNewFile();
			PrintWriter pw = new PrintWriter(tmpDiffArffFile);
			PrintWriter pwnor = new PrintWriter(tmpNorDiffArffFile);
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
				processSingleCloneFile(cloneFile, unrefactorFileLabel, pw, pwnor);
			}
			System.out.print("afterProcessingUnRefactorClone:");
			outputCurrentTime();

			for (File cloneFile : refactorCloneFileList) {
				processSingleCloneFile(cloneFile, refactorFileLabel, pw, pwnor);
			}
			System.out.print("afterProcessingRefactorClone:");
			outputCurrentTime();
			pw.close();
			pwnor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processSingleCloneFile(File cloneFile, String cloneFileLabel, PrintWriter pw, PrintWriter pwnor) {
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
					computeSingleCloneGroupDiff(frags, cloneFileLabel, pw, pwnor, refactoredIns);
				}
			} else {
				cnt = 0;
				for (MyCloneClass clazz : unrefactoredCloneList) {
					List<MyFragment> frags = clazz.getFragments();
					computeSingleCloneGroupDiff(frags, cloneFileLabel, pw, pwnor, null);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void computeSingleCloneGroupDiff(List<MyFragment> frags, String cloneFileLabel, PrintWriter pw, PrintWriter pwnor, RefactoredInstance refactorIns) {
		
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
		if (!isSingleGroupParserable(frags))
			return;
		cnt ++;
		String newCloneFileLabel = null;
		if (cloneFileLabel.equals(refactorFileLabel)) {
			refactoredGroupCnt++;
			newCloneFileLabel = "refactor" + refactoredGroupCnt;
		} else if (cloneFileLabel.equals(unrefactorFileLabel)) {
			unrefactoredGroupCnt++;
			newCloneFileLabel = "unrefactor" + unrefactoredGroupCnt;
		}
		/*
		if (cnt == 49 || cnt == 58 || cnt == 116 || cnt == 201 || cnt == 277 || cnt == 466 || cnt == 476 || cnt == 577 || cnt == 559 || cnt == 612 || cnt == 619 || cnt == 681)
			return;
		 */
		// transfer frags
		CloneSet set = new CloneSet("0");
		for (MyFragment frag : frags) {
			CloneInstance instance = new CloneInstance(frag.getFilePath(), frag.getStartLine(), frag.getEndLine());
			set.addInstance(instance);
		}
		// compute differences
		int /*paraMultisetNum = 0, gappedMultisetNum = 0,*/ partiallySameMultisetNum = 0;
		int callDiffMultisetNum = 0, varDiffMultisetNum = 0, typeDiffMultisetNum = 0;
		try {
			ArrayList<TokenMultiset> multisets = new TokenMCIDiff().diff(set, null);
			for (TokenMultiset multiset : multisets) {
				//if (multiset.isParamertized())
				//	paraMultisetNum++;
				//if (multiset.isGapped())
				//	gappedMultisetNum++;
				if (multiset.isPartiallySame())
					partiallySameMultisetNum++;
				if (multiset.isMethodCallDiff())
					callDiffMultisetNum++;
				if (multiset.isVariableDiff())
					varDiffMultisetNum++;
				if (multiset.isTypeDiff())
					typeDiffMultisetNum++;
			}
			pw.println(newCloneFileLabel + "," + multisets.size() + "," /*+ paraMultisetNum + "," + gappedMultisetNum
					+ "," */+ partiallySameMultisetNum + "," + callDiffMultisetNum + "," + varDiffMultisetNum
					+ "," + typeDiffMultisetNum);
			if(multisets.size() == 0) {
				pwnor.println(newCloneFileLabel + "," + multisets.size() + "," /*+ "-1" + "," + "-1"
						+ "," */+ "-1" + "," + "-1" + "," + "-1"
						+ "," + "-1");
			}
			else {
				pwnor.println(newCloneFileLabel + "," + multisets.size() + "," /*+ paraMultisetNum/(double)multisets.size() + "," + gappedMultisetNum/(double)multisets.size()
					+ "," */+ partiallySameMultisetNum/(double)multisets.size() + "," + callDiffMultisetNum/(double)multisets.size() + "," + varDiffMultisetNum/(double)multisets.size()
					+ "," + typeDiffMultisetNum/(double)multisets.size());
			}
			pw.flush();
			pwnor.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void combineCloneDiff() {
		try {
			File oldArffFile=new File(this.oldArffFilePath);
			File tmpDiffArffFile=new File(this.tmpArffFilePath);
			File tmpNorDiffArffFile = new File(this.tmpNorArffFilePath);
			BufferedReader brOld = new BufferedReader(new FileReader(oldArffFile));
			BufferedReader brDiff = new BufferedReader(new FileReader(tmpDiffArffFile));
			BufferedReader brDiffNor = new BufferedReader(new FileReader(tmpNorDiffArffFile));
			File newArffFile=new File(this.newArffFilePath);
			if(!newArffFile.exists())
				newArffFile.createNewFile();
			PrintWriter pw = new PrintWriter(new FileWriter(newArffFile));
			File norArffFile = new File(this.norArffFilePath);
			if (!norArffFile.exists())
				norArffFile.createNewFile();
			PrintWriter pwnor = new PrintWriter(new FileWriter(norArffFile));
			String featureStr = null;
			boolean afterData = false;
			while ((featureStr = brOld.readLine()) != null) {
				// System.out.println("featureStr:"+featureStr);
				if (!afterData) {
					if (featureStr.startsWith("@attribute cloneEval {refactored, unrefactored}")) {
						pw.println("@attribute numTotalMultiset real");
						//pw.println("@attribute numParaMultiset real");
						//pw.println("@attribute numGappedMultiset real");
						pw.println("@attribute numPartiallySameMultiset real");
						pw.println("@attribute numCallDiffMultiset real");
						pw.println("@attribute numVarDiffMultiset real");
						pw.println("@attribute numTypeDiffMultiset real");
						pw.println(featureStr);
						String t1 = brOld.readLine();
						String t2 = brOld.readLine();
						pw.println(t1);
						pw.println(t2);
						pwnor.println("@attribute numTotalMultiset real");
						//pwnor.println("@attribute numParaMultiset real");
						//pwnor.println("@attribute numGappedMultiset real");
						pwnor.println("@attribute numPartiallySameMultiset real");
						pwnor.println("@attribute numCallDiffMultiset real");
						pwnor.println("@attribute numVarDiffMultiset real");
						pwnor.println("@attribute numTypeDiffMultiset real");
						pwnor.println(featureStr);
						pwnor.println(t1);
						pwnor.println(t2);
						afterData = true;
					} else {
						pw.println(featureStr);
						pwnor.println(featureStr);
					}
						
				} else {
					String cloneDiffStr = brDiff.readLine();
					String cloneDiff = cloneDiffStr.substring(cloneDiffStr.indexOf(","));

					String oldFeatureWithoutLabel = featureStr.substring(0, featureStr.lastIndexOf(","));
					String label = featureStr.substring(featureStr.lastIndexOf(","));
					
					String newFeatureStr = oldFeatureWithoutLabel + cloneDiff + label;
					pw.println(newFeatureStr);
					
					String cloneDiffStrNor = brDiffNor.readLine();
					String cloneDiffNor = cloneDiffStrNor.substring(cloneDiffStrNor.indexOf(","));
					String oldFeatureWithoutLabelNor = featureStr.substring(0, featureStr.lastIndexOf(","));
					String labelNor = featureStr.substring(featureStr.lastIndexOf(","));
					
					String norFeatureStr = oldFeatureWithoutLabelNor + cloneDiffNor + labelNor;
					pwnor.println(norFeatureStr);
				}
			}
			pw.close();
			pwnor.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
