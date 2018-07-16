package waterloo.Experiment;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import edu.pku.sei.codeclone.predictor.MyCloneClass;
import edu.pku.sei.codeclone.predictor.MyFragment;
import edu.pku.sei.codeclone.predictor.RefactoredInstance;

import waterloo.AddedFeature;
import waterloo.FeatureExtractor;
import waterloo.GetMonth;
import waterloo.SortByVersion;
import waterloo.CloneDiff.CloneDiffFeatureGenerator;
import waterloo.History.HistoryFeatureGenerator;
import waterloo.History.NewRefactorCommitLocator;
import waterloo.Util.GlobalSettings;

public class ManuExpInvoker {
	
	public static String basePath;
	public static String logBasePath;
	public static String refactorInsBasePath;
	
	public static void main(String[] args) {
		if (args.length != 2)
			System.out.println("Arguments Wrong!");
		else {
			basePath = args[0];
			String projectName = args[1];
			logBasePath = basePath + "commits/";
			refactorInsBasePath = basePath + "NewFolder-after6.26/refactorInstancesInPaper/";
			
			String filterLogFilePath = null, repoFolderPath = null, cloneDataPath = null;
			String projectPath = null, totalLogFilePath = null;
		
			String arffFilePath = null;
			
			FeatureExtractor extractor = new FeatureExtractor();
			CloneDiffFeatureGenerator diffGen = null;
			HistoryFeatureGenerator hisFeatureGen = null;
			AddedFeature added = null;
			
			filterLogFilePath = logBasePath + projectName + "/authorFiltered.txt";
			repoFolderPath = basePath + projectName + "Filter";
			cloneDataPath = refactorInsBasePath + projectName;
			projectPath = basePath + projectName;
			totalLogFilePath = logBasePath + projectName + "/authorCommit.txt";

			
			//GetMonth getMonth = new GetMonth();
			//getMonth.extractFeatures(filterLogFilePath, totalLogFilePath, repoFolderPath, cloneDataPath, projectPath);
			//arffFilePath = getMonth.getArffFilePath(cloneDataPath, true);
			
			//extractor.extractFeatures(filterLogFilePath, repoFolderPath, cloneDataPath, projectPath, filterLogFilePath,totalLogFilePath);
			//arffFilePath=extractor.getArffFilePath(cloneDataPath,true,false);			
			arffFilePath = "/home/sonia/NewExperiment/NewFolder-after6.26/refactorInstancesInPaper/0713/jruby/groupFeaturesNewloc.arff";
			added = new AddedFeature(repoFolderPath, cloneDataPath, arffFilePath, projectPath, filterLogFilePath,totalLogFilePath);
			//added.computeAddedFeature();
			added.combineFeature();
			arffFilePath=added.getArffFilePath();
							
			hisFeatureGen=new HistoryFeatureGenerator(cloneDataPath,arffFilePath,projectPath,filterLogFilePath,totalLogFilePath,repoFolderPath); 
			//hisFeatureGen.computeHistoryFeature();
			hisFeatureGen.combineHistoryFeature();
			arffFilePath=hisFeatureGen.getNormalizedNewArffFilePath();
			
			diffGen=new CloneDiffFeatureGenerator(cloneDataPath,arffFilePath, projectPath, filterLogFilePath,totalLogFilePath, repoFolderPath);
			//diffGen.computeCloneDiffFeature();
			diffGen.combineCloneDiff();
			System.out.println(diffGen.getNorArffFilePath());
			
		}
	}

}
