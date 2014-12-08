package crawler.services;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//import org.apache.commons.io.FileUtils;

import crawler.Utilities;

public class AZLyrics implements LyricSite{
	
	private static String URL = "http://search.azlyrics.com/search.php?q=";
	private static String resultsDiv = "div.sen";
	private static String lyricDiv = "div#main div[style=\"margin-left:10px;margin-right:10px;\"]";
	
//	TODO Cleaning
//	TODO remove prints and return lyrica
	public String downloadLyric(String nameAuthor, String nameMusic, int debug) {
		String urlSearch = URL + Utilities.changeStringToSearch(nameAuthor)+"+"+Utilities.changeStringToSearch(nameMusic);
		if (debug == 1)System.out.println(urlSearch);
		Document doc = Utilities.getDoc(urlSearch);
		if (doc != null)
		{
			Elements lyrics = doc.select(resultsDiv);
			for (Element element : lyrics) {
				String urlOfLyric = element.select("a").attr("href");
				
				/*try {
					FileUtils.copyURLToFile(new URL(urlOfLyric), new File(nameAuthor+" "+nameMusic+".html"));
					Utilities.sleep(2000);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				if (debug == 1)System.out.println(urlOfLyric);
				
				if (urlOfLyric.contains(changeStringToURL(nameMusic)) && urlOfLyric.contains(changeStringToURL(nameAuthor)))
				{
//					System.out.println("Correspondencia a 100%");
					Document lyric = Utilities.getDoc(urlSearch);
					Utilities.sleep();
//					Elements aux = lyric.select("div");
					String lyrica = lyric.select(lyricDiv).text();
					
					System.out.println(lyrica);
					return lyrica;
//					System.out.println(lyric.select("div#main [margin-left:10px;margin-right:10px;]"));
				}
			}
		}
		return null;
	}

	/**
	 * @param music 
	 * Recebe titulo da musica recolhido do youtube
	 * @param debug
	 * Recebe 1 para ver prints de teste 
	 */
	public String downloadLyric(String music, boolean debug) {
		String urlSearch, urlOfLyric;
		String bandName, songTitle, lyrica;
		double count = 0;
		Elements lyrics;
		Document doc, lyric;
		String[] words;
		
		music = cleanString(music);
		
		//Criacao do url de pesquisa
		urlSearch = URL + Utilities.changeStringToSearch(music);

		if (debug)
			System.out.println(urlSearch);

		doc = Utilities.getDoc(urlSearch);
		
//		Remocao da preposicao "the" que se torna irrelevante para a procura nos resultados
		music = music.toLowerCase().replace("the ", "");

		words = music.split(" ");

		if (doc != null) {
			lyrics = doc.select(resultsDiv);
		
			//Faz a selecao do possibel resultado para a pesquisa
			for (Element element : lyrics) {
				count = 0;
				urlOfLyric = element.select("a").attr("href");
				urlOfLyric = "http://www.lyricsmode.com" + urlOfLyric;
				//if (debug)
				//	System.out.println(urlOfLyric);

				//Conta o nr de palavras em comum entre o titulo e o url da pesquisa
				
				
				for (String str : words)
					System.out.println(urlOfLyric+" vs "+ changeStringToURL(str));
//					if (urlOfLyric.contains(changeStringToURL(str)))
//						count++;

				//Verifica se existem pelo menos 70% das palavras em comum
//				if ((count / words.length) > 0.70) {
//
//					lyric = Utilities.getDoc(urlOfLyric);
//					Utilities.sleep();
//
//					//Seleciona o nome do artista
//					bandName = lyric.select(bandDiv).text();
//					
//					//Seleciona o titulo da musica
//					songTitle = lyric.select(songDiv).text().replace(" lyrics", "");
//					
//					//Seleciona a letra da musica
//					lyrica = (Utilities.br2nl(lyric.select(lyricDiv).toString())).replace("\n ", "\n");
//
//					System.out.println("Banda: " + bandName);
//					System.out.println("Titulo: " + songTitle);
//					//System.out.println(lyrica);
//					System.out.println("OK");
//
//					return lyrica;
//				} else if (debug)
//				{
//					System.out.println("Values:" + count + "+" + words.length + "+" + count / words.length);
//				}
			}
		}
		System.out.println("count = "+count);
		//Caso nao encontre a letra da musica retorna null
//		if (debug)
//			System.out.println("ERROR");
		return null;
	}
	
	public static String changeStringToURL(String msg) {
		return msg.toLowerCase().replace("the ", "").replace(" ", "");
	}

	/**
	 * @param msg
	 * texto para converter para o estilo do url do site
	 * retorna o texto convertido
	 * 
	 * */
//	public static String changeStringToURL(String msg) {
//		return msg.toLowerCase().replace("'", "").replace("& ", "").replace(" ", "_").replace("-", "_").replace(".", "");
//	}

	/**
	 * @param music
	 * Recebe o titulo da musica e efetua o clean ao mesmo
	 * limpando informacao e carateres irrelevantes 
	 * 
	 * */
	public static String cleanString(String music) {
		
		//lista de possiveis sequencias que tem de ser removidas
		HashMap<String, String> regexReplaceList = new HashMap<String, String>();

		regexReplaceList.put("[\\]\\[(){},.;!?<>%+~<>*\"_-]", " ");
		regexReplaceList.put("ft\\.", "feat");

		regexReplaceList.put("with lyric[s]*", "");
		regexReplaceList.put("lyric[s]*", "");
		regexReplaceList.put("instrumental", "");
		regexReplaceList.put("karaoke", "");
		regexReplaceList.put("version", "");
		regexReplaceList.put("w\\/", "");
		regexReplaceList.put("official", "");
		regexReplaceList.put("video", "");
		regexReplaceList.put("download", "");
		regexReplaceList.put("youtube", "");
		regexReplaceList.put("in the style of", "");
		regexReplaceList.put("hd", " ");
		regexReplaceList.put("hq", " ");
		regexReplaceList.put("on screen", "");

		regexReplaceList.put("[ ]+", " ");

		//Remocao da acentuacao
		music = Utilities.unAccent(music).toLowerCase();

		//Efetua a limpeza das seuencias irrelevantes segundo o regexReplaceList
		for (Map.Entry<String, String> element : regexReplaceList.entrySet())
			music = music.replaceAll(element.getKey(), element.getValue());

		//Limpa a existencia de espacos no fim do titulo a usar na pesquisa
		if (music.endsWith(" "))
			music = music.substring(0, music.length() - 1);

		//Limpa a existencia de espacos no inicio do titulo a usar na pesquisa
		if (music.startsWith(" "))
			music = music.substring(1, music.length());

		return music;
	}
}
