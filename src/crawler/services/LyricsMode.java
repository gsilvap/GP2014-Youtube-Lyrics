package crawler.services;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.Utilities;

public class LyricsMode implements LyricSite {

	private static String URL = "http://www.lyricsmode.com/search.php?search=";
	private static String resultsDiv = "a.b.search_highlight";
	private static String lyricDiv = "p#lyrics_text.ui-annotatable";
	private static String bandDiv = ".header-band-name";
	private static String songDiv = ".header-song-name";
	
	
	public String downloadLyric(String music, int debug) {
		HashMap<String, String> replaceList = new HashMap<String, String>();
		replaceList.put("ft.", "feat");
		replaceList.put("-", " ");
		replaceList.put("instrumental", "");
		replaceList.put("karaoke", "");
		replaceList.put("version", "");
		replaceList.put("w/", "");
		replaceList.put("official", "");
		replaceList.put("video", "");
		replaceList.put("download", "");
		replaceList.put("youtube", "");
		replaceList.put("in the style of", "");
		replaceList.put("hd", " ");
		replaceList.put("hq", " ");
		replaceList.put("with lyrics", "");
		replaceList.put("lyrics", "");
		replaceList.put("on screen", "");
		replaceList.put("   ", " ");
		replaceList.put("  ", " ");
		
		music = Utilities.unAccent(music).toLowerCase();

		music = music.replaceAll("[\\]\\[(){},.;!?<>%+~<>*\"_]", "");
		
		for (Map.Entry<String, String> element : replaceList.entrySet()) {
			music = music.replace(element.getKey(), element.getValue());
		}

		String prevMusic = music;
		while (!(music = music.replace("  ", " ")).equals(prevMusic)) {
			prevMusic = music;
			music = music.replace("  ", " ");
		}

		if (music.endsWith(" ")) {
			music = music.substring(0, music.length() - 1);
		}

		if (music.startsWith(" ")) {
			music = music.substring(1, music.length());
		}

		String urlSearch = URL + Utilities.changeStringToSearch(music);

		music = music.toLowerCase().replace("the ", "");

		if (debug == 1)
			System.out.println(urlSearch);

		Document doc = Utilities.getDoc(urlSearch);
		String[] words = music.split(" ");

		if (doc != null) {
			Elements lyrics = doc.select(resultsDiv);
			for (Element element : lyrics) {
				double count = 0;
				String urlOfLyric = element.select("a").attr("href");

				urlOfLyric = "http://www.lyricsmode.com" + urlOfLyric;

				if (debug == 1)
					System.out.println(urlOfLyric);

				for (String str : words) {
					if (urlOfLyric.contains(changeStringToURL(str))) {
						count++;
					}
				}

				if ((count / words.length) > 0.70) {
					Document lyric = Utilities.getDoc(urlOfLyric);
					Utilities.sleep();
					
					String bandName = lyric.select(bandDiv).text();
					String songTitle = lyric.select(songDiv).text().replace(" lyrics", "");
					String lyrica = lyric.select(lyricDiv).text();
					
					System.out.println("Banda: "+bandName);
					System.out.println("Titulo: "+songTitle);
					System.out.println("OK");
					return lyrica;
				} else {

					System.out.println("Values:" + count + "+" + words.length + "+" + count / words.length);

				}
			}
		}

		System.out.println("ERROR");
		return null;
	}

	public static String changeStringToURL(String msg) {
		return msg.toLowerCase().replace("'", "").replace("& ", "").replace(" ", "_").replace("-", "_").replace(".", "");
	}

	@Override
	public String downloadLyric(String nameAuthor, String nameMusic, int debug) {
		// TODO Auto-generated method stub
		return null;
	}
}
