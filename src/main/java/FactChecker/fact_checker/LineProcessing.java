package FactChecker.fact_checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class LineProcessing {

	List<String> result = new ArrayList<String>();
	List<HashMap<String, String>> namedEntities = new ArrayList<HashMap<String, String>>();
	List<HashMap<String, String>> Tokens = new ArrayList<HashMap<String, String>>();
	String key;


	public List<String> lineprocessor(List<String> lines) {




		NamedEntities(lines);

		for(int i = 0; i<namedEntities.size();i++) {

			List<String> searchKeyword = new ArrayList<>();
			HashMap<String, String> Entities = new HashMap<String,String>();
			HashMap<String, String> token = new HashMap<String,String>();
			Entities = namedEntities.get(i);
			token = Tokens.get(i);
			for(String entity : Entities.keySet()) {
				if(((Entities.get(entity).equals("PERSON")) || (Entities.get(entity).equals("ORGANIZATION")) || (Entities.get(entity).equals("COUNTRY"))))
				{
					key = entity;
					if(entity.contains(" "))
						key = key.replaceAll(" ", "_");            		
					break;
				}
			}
			searchKeyword.addAll(Entities.keySet());
			String tok_resolve = "";
			for(String tok : token.keySet()) {    		   
				if((token.get(tok).equals("NN"))) {
					String c = tok_resolve + tok + "_";
					tok_resolve = c;

				}
			}
			if(!tok_resolve.isEmpty()) {
				String substr = tok_resolve.substring(0,tok_resolve.length()-1);
				searchKeyword.add(substr);}

			for(String tok : token.keySet()) {
				int flag = 0;
				if((token.get(tok).equals("NN"))) 
					continue;
				else if( token.get(tok).equals("NNP")) {
					for(String k : searchKeyword) {
						if(k.contains(tok)) {
							flag = 1;
							break;}
						else
							continue;
					}

					if(flag==0)
						searchKeyword.add(tok);
				}
			}

			Wiki w = new Wiki();
			searchKeyword.remove(key.replace("_", " "));
			String value = w.wikiSearch(key, searchKeyword);

			result.add(value);

		}

		return result;


	}


	public void NamedEntities(List<String> lines)
	{

		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);                
		for(String line : lines ) {
			CoreDocument doc = new CoreDocument(line);
			pipeline.annotate(doc);
			HashMap<String, String> entityMap = new HashMap<String, String>();
			HashMap<String, String> tokenMap = new HashMap<String, String>();
			for (CoreEntityMention em : doc.entityMentions()) {
				entityMap.put(em.text(), em.entityType());
			}

			namedEntities.add(entityMap);

			Annotation annotation = new Annotation(line);
			pipeline.annotate(annotation);
			List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
			for (CoreMap sentence : sentences) {
				for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
					String word = token.get(CoreAnnotations.TextAnnotation.class);
					// this is the POS tag of the token
					String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
					tokenMap.put(word, pos);   
				}
			}

			Tokens.add(tokenMap);


		}   

	}

}
