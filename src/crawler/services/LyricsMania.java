package crawler.services;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.Utilities;

public class LyricsMania implements LyricSite {

	//Url de pesquisa
	private static String URL = "http://www.lyricsmania.com/searchnew.php?k=";
	//Localizacao dos resultados de pesquisa
	private static String resultsDiv = ".col-left ul li a";
	//Localizacao da letra da musica
	private static String lyricDiv = "div.lyrics-body";
	//Localizacao do nome do artista
	private static String bandDiv = "h3";
	//Localizacao do titulo da musica
	private static String songDiv = "h2";

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
		urlSearch = URL + changeStringToSearch(music);

		if (debug)
			System.out.println(urlSearch);

		doc = Utilities.getDoc(urlSearch);
//		System.out.println(doc);
		
		//Remocao da preposicao "the" que se torna irrelevante para a procura nos resultados
		music = music.toLowerCase().replace("the ", "");

		words = music.split(" ");
		
//		System.out.println(doc);
		lyrics = doc.select(resultsDiv);
//		for (Element string : lyrics) {
//			System.out.println(string);
//		}
		
//		System.out.println(lyrics);
		
		if (doc != null) {
			lyrics = doc.select(resultsDiv);
//			System.out.println(lyrics);
			//Faz a selecao do possivel resultado para a pesquisa
			for (Element element : lyrics) {
				count = 0;
				String urlOfLyricShort = element.select("a").attr("href");
				urlOfLyric = "http://www.lyricsmania.com" + urlOfLyricShort;
//				if (debug)
//					System.out.println(" "+urlOfLyric);

//				//Conta o nr de palavras em comum entre o titulo e o url da pesquisa
				for (String str : words)
					if (urlOfLyricShort.contains(changeStringToURL(str)))
						count++;

//				//Verifica se existem pelo menos 70% das palavras em comum
				if ((count / words.length) > 0.70) {

					lyric = Utilities.getDoc(urlOfLyric);
//					System.out.println(lyric);
					System.out.println("-> "+urlOfLyric);
					Utilities.sleep();

//					//Seleciona o nome do artista
					bandName = lyric.select(bandDiv).text();
//					
//					//Seleciona o titulo da musica
					songTitle = lyric.select(songDiv).text().replace(" lyrics", "");
					
					String toReplace = lyric.select("div.lyrics-body strong").text();
					String toReplace2 = lyric.select("div.lyrics-body h5").text();
					
//					//Seleciona a letra da musica
					lyrica = (Utilities.br2nl(lyric.select(lyricDiv).toString().trim().replaceFirst(toReplace, "").replaceFirst(toReplace2, ""))).trim().replace("\n ", "\n");

					System.out.println("Banda: " + bandName);
					System.out.println("Titulo: " + songTitle);
					System.out.println(lyrica);
					System.out.println("OK");
//
					return lyrica;
//					return urlOfLyric;
				} else if (debug)
				{
//					System.out.println("Values:" + count + "+" + words.length + "+" + count / words.length);
				}
			}
		}
//		
//		//Caso nao encontre a letra da musica retorna null
//		if (debug)
//			System.out.println("ERROR");
		return null;
	}
	
	public static String changeStringToSearch(String msg) {
		return msg.replace(" ", "+");
	}

	/**
	 * @param msg
	 * texto para converter para o estilo do url do site
	 * retorna o texto convertido
	 * 
	 * */
	public static String changeStringToURL(String msg) {
		return msg.toLowerCase().replace("'", "").replace("& ", "").replace(" ", "_").replace("-", "_").replace(".", "");
	}

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

	//Metodo para remover
	@Override
	public String downloadLyric(String nameAuthor, String nameMusic, int debug) {
		// TODO Auto-generated method stub
		return null;
	}
}
