package FactChecker.fact_checker;

import java.io.IOException;

//app

import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Wiki {

	public String wikiSearch(String key, List<String> object) {

		try {
			String link = "https://en.wikipedia.org/wiki/"+key;
			Document doc = Jsoup.connect(link).get();
			Element contentdiv = doc.select("div[id=content]").first();
			int bool = 0;


			for(String obj : object) {

				if(contentdiv.toString().contains(obj)) {
					bool = 1;
					continue;

				}
				else break;

			}

			if(bool == 1)
				return"1.0";

		} catch (IOException e) {

			e.printStackTrace();
		}

		return "0.0";
	}

}
