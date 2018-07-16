package waterloo.Experiment;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

import waterloo.SortByVersion;
import waterloo.History.NewRefactorCommitLocator;
import waterloo.Util.GlobalSettings;
import edu.pku.sei.codeclone.predictor.MyCloneClass;
import edu.pku.sei.codeclone.predictor.MyFragment;
import edu.pku.sei.codeclone.predictor.RefactoredInstance;

public class TestCCFinder {

	static String[] projectNames = {"axis2-java", "eclipse.jdt.core", "jfreechart", "jruby", "lucene"};
	static ArrayList<String> filteredCommitList = new ArrayList<String>();
	static ArrayList<String> totalCommitList = new ArrayList<String>();
	static String tmpFolderPath = "/home/sonia/NewExperiment/NewFolder-after6.26/tmpVersions/";
	static String repoFolderPath = "/home/sonia/NewExperiment/";
	
	public static void main(String[] args) throws Exception {
		//tellmewhichiswrong();
		
		
		/*for (String projectName : projectNames) {
			//combine our
			String path1 = "/home/sonia/NewExperiment/NewFolder-after6.26/refactorInstancesInPaper/" + projectName + "/groupFeatures-our.arff";
			System.out.println(projectName);
			BufferedReader r = new BufferedReader(new FileReader(new File(path1)));
			String tmp = "";
			boolean flag = false;
			Vector<String> family = new Vector<String>();
			while ((tmp = r.readLine()) != null) {
				if (flag == true) {
					String[] opt = tmp.split(",");
					family.add(opt[2]);
				}
				if (tmp.equals("@data")) {
					flag = true;
				}
			}
			r.close();
			System.out.println(family.size());
			System.out.println(family);
			String path2 = "/home/sonia/NewExperiment/NewFolder-after6.26/refactorInstancesInPaper/" + projectName + "/groupFeatures-MonthNewloc-MonthAddNorhisClonediffNor.arff";
			r = new BufferedReader(new FileReader(new File(path2)));
			flag = false;
			Vector<String> header = new Vector<String>();
			Vector<String> features = new Vector<String>();
			int cnt = 0;
			while ((tmp = r.readLine()) != null) {
				if (flag == true) {
					String[] opt = tmp.split(",");
					String feature = "";
					for (int i = 0; i < opt.length - 1; i++) {
						if (i == 2) {
							feature += family.get(cnt) + ",";
						}
						else {
							if (i == 4) {
								feature += "0,";
							}
							else {
								feature += opt[i] + ",";
							}
						}
					}
					feature += opt[opt.length-1];
					features.add(feature);
					cnt ++;
				}
				else {
					header.add(tmp);
				}
				if (tmp.equals("@data")) {
					flag = true;
				}
			}
			System.out.println(header);
			String outpath = "/home/sonia/NewExperiment/NewFolder-after6.26/refactorInstancesInPaper/" + projectName + "/our+methodline.arff";
			BufferedWriter w = new BufferedWriter(new FileWriter(new File(outpath)));
			for (String str : header) {
				w.write(str + "\n");
			}
			for (String str : features) {
				w.write(str + "\n");
			}
			w.close();
			
			//combine wangwei + month
			String path1 = "/home/sonia/NewExperiment/NewFolder-after6.26/refactorInstancesInPaper/" + projectName + "/groupFeatures-Month.arff";
			System.out.println(projectName);
			BufferedReader r = new BufferedReader(new FileReader(new File(path1)));
			String tmp = "";
			boolean flag = false;
			Vector<String> months = new Vector<String>();
			while ((tmp = r.readLine()) != null) {
				if (flag == true) {
					String[] opt = tmp.split(",");
					months.add(opt[4]);
				}
				if (tmp.equals("@data")) {
					flag = true;
				}
			}
			r.close();
			System.out.println(months.size());
			System.out.println(months);
			String path2 = "/home/sonia/NewExperiment/NewFolder-after6.26/refactorInstancesInPaper/" + projectName + "/groupFeatures-wangwei.arff";
			r = new BufferedReader(new FileReader(new File(path2)));
			flag = false;
			Vector<String> header = new Vector<String>();
			Vector<String> features = new Vector<String>();
			int cnt = 0;
			while ((tmp = r.readLine()) != null) {
				if (flag == true) {
					String[] opt = tmp.split(",");
					String feature = "";
					for (int i = 0; i < opt.length - 1; i++) {
						if (i == 4) {
							feature += months.get(cnt) + ",";
						}
						else {
							feature += opt[i] + ",";
						}
					}
					feature += opt[opt.length-1];
					features.add(feature);
					cnt ++;
				}
				else {
					header.add(tmp);
				}
				if (tmp.equals("@data")) {
					flag = true;
				}
			}
			System.out.println(header);
			String outpath = "/home/sonia/NewExperiment/NewFolder-after6.26/refactorInstancesInPaper/" + projectName + "/wangwei.arff";
			BufferedWriter w = new BufferedWriter(new FileWriter(new File(outpath)));
			for (String str : header) {
				w.write(str + "\n");
			}
			for (String str : features) {
				w.write(str + "\n");
			}
			w.close();
		}*/
		
		
		
		for (String projectName : projectNames) {
			String cloneDataPath = "/home/sonia/NewExperiment/NewFolder-after6.26/unrefactorInstancesInPaper/" + projectName + "/";
			System.out.println(projectName);
			File cloneFileFolder = new File(cloneDataPath);
			ArrayList<File> refactorCloneFileList = new ArrayList<File>();
			for (File cloneFile : cloneFileFolder.listFiles()) {
				if(cloneFile.isDirectory())
					continue;
				String fileName = cloneFile.getName();
				if (fileName.contains("readable"))
					continue;
				if (fileName.contains("unrefactored")) {
					refactorCloneFileList.add(cloneFile);
				}
				else if (fileName.contains("refactored")){}
					//refactorCloneFileList.add(cloneFile);
			}
			//Collections.sort(refactorCloneFileList, new SortByVersion());
			System.out.println("RefactorList:" + refactorCloneFileList);
			
			List<MyCloneClass> refactoredInsList = new Vector<MyCloneClass>();
			for (File cloneFile : refactorCloneFileList) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cloneFile));
				List<MyCloneClass> refactoredInsListtmp = (List<MyCloneClass>) ois.readObject();
				ois.close();
				for (MyCloneClass ins : refactoredInsListtmp) {
					refactoredInsList.add(ins);
				}
			}
			System.out.println("Before filter:" + refactoredInsList.size());
			
			/*Vector<RefactoredInstance> refactoredInsList = new Vector<RefactoredInstance>();
			for (File cloneFile : refactorCloneFileList) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cloneFile));
				Vector<RefactoredInstance> refactoredInsListtmp = (Vector<RefactoredInstance>) ois.readObject();
				ois.close();
				for (RefactoredInstance ins : refactoredInsListtmp) {
					refactoredInsList.add(ins);
				}
			}
			System.out.println("Before filter:" + refactoredInsList.size());
			*/
			
			String outputBasePath = "/home/sonia/NewExperiment/NewFolder-after6.26/codes-un/" + projectName + "/";
			File file = new File(outputBasePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			int cnt = 0;
			for (MyCloneClass ins : refactoredInsList) {
				cnt ++;
				String path = outputBasePath + cnt;
				file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
				int num = 0;
				//System.out.println("Number " + cnt + ":");
				for (MyFragment frag : ins.getFragments()) {
					num ++;
					//System.out.println(frag.startLine + " " + frag.endLine);
					String filePath = path + "/cloneInstance" + num + ".java";
					file = new File(filePath);
					file.createNewFile();
					BufferedWriter w = new BufferedWriter(new FileWriter(file));
					BufferedReader r = new BufferedReader(new FileReader(frag.getFilePath()));
					String tmp = "";
					int linenum = 0;
					while ((tmp = r.readLine()) != null) {
						linenum ++;
						if (linenum >= frag.startLine && linenum <= frag.endLine) {
							w.write(tmp+"\n");
						}
						if (linenum >= frag.endLine)
							break;
					}
					r.close();
					w.close();
				}
			}
		}
		
	}
	
	static void tellmewhichiswrong() throws Exception {
		String[] projects = {"eclipse.jdt.core", "jfreechart", "lucene", "axis2-java", "jruby"};
		for (String projectName : projects) {
			String cloneDataPath = "/home/sonia/NewExperiment/NewFolder-after6.26/refactorInstancesInPaper/" + projectName + "/";
			System.out.println(projectName);
			File cloneFileFolder = new File(cloneDataPath);
			ArrayList<File> refactorCloneFileList = new ArrayList<File>();
			for (File cloneFile : cloneFileFolder.listFiles()) {
				if(cloneFile.isDirectory())
					continue;
				String fileName = cloneFile.getName();
				if (fileName.contains("readable"))
					continue;
				if (fileName.contains("unrefactored")) {}
				else if (fileName.contains("refactored"))
					refactorCloneFileList.add(cloneFile);
			}
			Collections.sort(refactorCloneFileList, new SortByVersion());
			System.out.println("RefactorList:" + refactorCloneFileList);
			Vector<RefactoredInstance> refactoredInsList = new Vector<RefactoredInstance>();
			for (File cloneFile : refactorCloneFileList) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cloneFile));
				Vector<RefactoredInstance> refactoredInsListtmp = (Vector<RefactoredInstance>) ois.readObject();
				ois.close();
				for (RefactoredInstance ins : refactoredInsListtmp) {
					refactoredInsList.add(ins);
				}
			}
			System.out.println("Before filter:" + refactoredInsList.size());
			
			tmpFolderPath = "/home/sonia/NewExperiment/NewFolder-after6.26/tmpVersions/" + projectName + "/";
			repoFolderPath = "/home/sonia/NewExperiment/" + projectName + "Filter";
			String projectPath = "/home/sonia/NewExperiment/" + projectName;
			String filteredLogFilePath = "/home/sonia/NewExperiment/commits/" + projectName + "/authorFiltered.txt";
			String totalLogFilePath = "/home/sonia/NewExperiment/commits/" + projectName + "/authorCommit.txt";
			filteredCommitList = new ArrayList<String>();
			totalCommitList = new ArrayList<String>();
			setCommitList(filteredLogFilePath, filteredCommitList);
			setCommitList(totalLogFilePath, totalCommitList);
			int cnt = 0;
			for (RefactoredInstance ins : refactoredInsList) {
				Vector<MyFragment> frags = ins.frags;
				String fragHash = null, methodHash = null;
				String fragBaseHash = getHashStrFromFilterLog(frags.get(0).getVersionRepoName());
				fragHash = fragBaseHash;
				NewRefactorCommitLocator locator = new NewRefactorCommitLocator(projectPath, filteredLogFilePath,
						totalLogFilePath, repoFolderPath);
				locator.setHashvalueList();
				if (ins != null) {
					methodHash = locator.getRealChangeID(ins, GlobalSettings.simi);
				}
				if (methodHash != null)
					fragHash = getPreHash(methodHash);
				//System.out.println("Frag Hash:" + fragHash + " " + fragBaseHash);
				Vector<MyFragment> newfrags = null;
				if (fragHash.equals(fragBaseHash)) {
					newfrags = frags;
				}
				else {
					newfrags = rebuildFrags(frags, fragHash);
					//System.out.println(versionRepoPath);
				}
				frags = newfrags;
				if (frags == null || frags.size() == 0)
					System.out.println("No." + cnt + " is null");
				cnt ++;
			}
			
		}
	}
	
	private static Vector<MyFragment> rebuildFrags(Vector<MyFragment> frags, String hash) {
		String originBasePath = repoFolderPath + "/00000/";
		String newBasePath = tmpFolderPath + hash + "/";
		Vector<MyFragment> ret = new Vector<MyFragment>();
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
				//System.out.println("predPath:" + predPath);
				//System.out.println("nextPath:" + nextPath);
				//System.out.println("predRange:" + predstart + " " + predend);
				//System.out.println("nextRange:" + nextStart + " " + nextEnd);
				continue;
			}
			//System.out.println("nextLineRange:" + nextStart + "-" + nextEnd);
			MyFragment newFrag = new MyFragment(nextStart, nextEnd, -1, -1, nextPath, -1, hash);
			ret.add(newFrag);
		}
		return ret;
	}
	
	public static int[] buildLineMap(String curPath, String predPath) {
        
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
	
	private static String getPreHash(String nowHash) {
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
	
	private static String getHashStrFromFilterLog(String repoFolderName) {
		int which = filteredCommitList.size() - Integer.parseInt(repoFolderName);
		String commitStr = filteredCommitList.get(which);
		String[] tmp = commitStr.split(",");
		if (tmp.length >= 5)
			return tmp[0];
		return null;
	}
	
	private static void setCommitList(String logFilePath, ArrayList<String> commitList) {
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
	
	static Vector<String> calcBetween(String repo1, String repo2, Vector<String> filter, Vector<String> commits) {
		Vector<String> ret = new Vector<String>();
		boolean flag = false;
		String versionOld = filter.elementAt(filter.size() - Integer.parseInt(repo1));
		String versionNew = filter.elementAt(filter.size() - Integer.parseInt(repo2));
		for (String str : commits) {
			if (str.equals(versionNew)) {
				flag = true;
			}
			if (flag) {
				ret.add(str);
			}
			if (str.equals(versionOld)) {
				break;
			}
		}
		return ret;
	}
	
	private static boolean isSingleGroupParserable(List<MyFragment> frags) {
		for (MyFragment frag : frags) {
			if (isParserable(frag))
				return true;
		}
		return false;
	}


	private static boolean isParserable(MyFragment frag) {
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
	
}
