package FactChecker.fact_checker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WriteFile {

	public void writeResults(List<String> fact, List<String> result) {
		File file = new File("FactResult.ttl");

		if(!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter print = new PrintWriter(file);
				for (int i = 0; i < fact.size(); i++) {

					String fileLine = "<"+"http://swc2017.aksw.org/task2/dataset/"+fact.get(i)+">"+"\n\t\t "+"<"+"http://swc2017.aksw.org/hasTruthValue"+">"+"\n\t\t\t\t"+"\""+result.get(i)+"\""+"^^<http://www.w3.org/2001/XMLSchema#double>"+" "+".";					
					print.println(fileLine);
					print.println("");
				}

				print.close();
			} 




			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	} 

}
