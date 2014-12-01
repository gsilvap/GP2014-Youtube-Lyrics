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
		
		
		music = Utilities.unAccent(music).toLowerCase();

		for (Map.Entry<String, String> element : regexReplaceList.entrySet()) {
			music = music.replaceAll(element.getKey(), element.getValue());
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
				} else if(debug == 1)
					System.out.println("Values:" + count + "+" + words.length + "+" + count / words.length);
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
