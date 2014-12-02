package crawler.services;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.Utilities;

public class LyricsMode implements LyricSite {

	//Url de pesquisa
	private static String URL = "http://www.lyricsmode.com/search.php?search=";
	//Localizacao dos resultados de pesquisa
	private static String resultsDiv = "a.b.search_highlight";
	//Localizacao da letra da musica
	private static String lyricDiv = "p#lyrics_text.ui-annotatable";
	//Localização do nome do artista
	private static String bandDiv = ".header-band-name";
	//Localização do titulo da musica
	private static String songDiv = ".header-song-name";

	public String downloadLyric(String music, int debug) {
		String urlSearch, urlOfLyric;
		String bandName, songTitle,lyrica;
		double count = 0;
		Elements lyrics;
		Document doc, lyric;
		String[] words;
		
		music = Utilities.unAccent(music).toLowerCase();

		music = cleanString(music);

		urlSearch = URL + Utilities.changeStringToSearch(music);

		music = music.toLowerCase().replace("the ", "");

		if (debug == 1)
			System.out.println(urlSearch);

		doc = Utilities.getDoc(urlSearch);
		words = music.split(" ");

		if (doc != null) {
			lyrics = doc.select(resultsDiv);
			for (Element element : lyrics) {
				count = 0;
				urlOfLyric = element.select("a").attr("href");

				urlOfLyric = "http://www.lyricsmode.com" + urlOfLyric;

//				if (debug == 1)
//					System.out.println(urlOfLyric);

				for (String str : words) {
					if (urlOfLyric.contains(changeStringToURL(str))) {
						count++;
					}
				}

				if ((count / words.length) > 0.70) {
					lyric = Utilities.getDoc(urlOfLyric);
					Utilities.sleep();

					bandName = lyric.select(bandDiv).text();
					songTitle = lyric.select(songDiv).text().replace(" lyrics", "");

					lyrica = (Utilities.br2nl(lyric.select(lyricDiv).toString())).replace("\n ", "\n");

					System.out.println("Banda: " + bandName);
					System.out.println("Titulo: " + songTitle);
//					System.out.println(lyrica);
					System.out.println("OK");

					return lyrica;
				} else if (debug == 1)
					System.out.println("Values:" + count + "+" + words.length + "+" + count / words.length);
			}
		}

		System.out.println("ERROR");
		return null;
	}

	public static String changeStringToURL(String msg) {
		return msg.toLowerCase().replace("'", "").replace("& ", "").replace(" ", "_").replace("-", "_").replace(".", "");
	}

	public static String cleanString(String music) {
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

		for (Map.Entry<String, String> element : regexReplaceList.entrySet())
			music = music.replaceAll(element.getKey(), element.getValue());

		if (music.endsWith(" "))
			music = music.substring(0, music.length() - 1);

		if (music.startsWith(" "))
			music = music.substring(1, music.length());

		return music;
	}

	@Override
	public String downloadLyric(String nameAuthor, String nameMusic, int debug) {
		// TODO Auto-generated method stub
		return null;
	}
}
