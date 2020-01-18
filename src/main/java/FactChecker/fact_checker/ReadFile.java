package FactChecker.fact_checker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	
	List<String> lines = new ArrayList<String>();
	List<String> fact = new ArrayList<String>();
	
	public void file_reader(List<String> fact, List<String> lines, String fname) {
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fname));
			String line;
			while((line = br.readLine()) != null) {
				if(!(line.contains("Fact_Statement") && line.contains("FactID"))) {
					String[] a = line.split("\t");
					lines.add(a[1]);
					fact.add(a[0]);
				}
				
				
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

}
