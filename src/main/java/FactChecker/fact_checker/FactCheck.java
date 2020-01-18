package FactChecker.fact_checker;

import java.util.ArrayList;
import java.util.List;

import FactChecker.fact_checker.ReadFile;
import FactChecker.fact_checker.WriteFile;;

public class FactCheck 
{




	public static void main(String[] args) {

		List<String> fact = new ArrayList<String>();
		List<String> lines = new ArrayList<String>();
		ReadFile fr = new ReadFile();
		LineProcessing line = new LineProcessing();
		List<String> res = new ArrayList<String>();
		WriteFile fw = new WriteFile();
		String filename = "SNLP2019_test.tsv";
		//"SNLP2019_training.tsv"
		fr.file_reader(fact,lines,filename);
		res = line.lineprocessor(lines);
		fw.writeResults(fact, res);





	}

}
