package crawler.services;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.Utilities;

public class LyricsMode implements LyricSite {

	private static String URL = "http://www.lyricsmode.com/search.php?search=";
	private static String resultsDiv = "a.b.search_highlight";
	private static String lyricDiv = "p#lyrics_text.ui-annotatable";

	//	TODO Cleaning
	//	TODO remove prints and return lyrica
	public String downloadLyric(String nameAuthor, String nameMusic, int debug) {
		nameAuthor = Utilities.unAccent(nameAuthor).toLowerCase().replace("ft.", "feat");
		nameMusic = Utilities.unAccent(nameMusic);
		nameAuthor = nameAuthor.replaceAll("[\\]\\[(){},.;!?<>%]", "");
//		nameMusic = nameMusic.replaceAll("[\\]\\[(){},.;!?<>%]", "");
//		
		String urlSearch = URL + Utilities.changeStringToSearch(nameAuthor) + "+" + Utilities.changeStringToSearch(nameMusic);
		
		nameAuthor = nameAuthor.toLowerCase().replace("the ", "");
		
		if (debug == 1)
			System.out.println(urlSearch);
		Document doc = Utilities.getDoc(urlSearch);
		if (doc != null) {
			Elements lyrics = doc.select(resultsDiv);
			for (Element element : lyrics) {
				String urlOfLyric = element.select("a").attr("href");

				urlOfLyric = "http://www.lyricsmode.com" + urlOfLyric;
				if (debug == 1)
					System.out.println(urlOfLyric);
				if (urlOfLyric.contains(changeStringToURL(nameMusic)) && urlOfLyric.contains(changeStringToURL(nameAuthor))) {
					Document lyric = Utilities.getDoc(urlOfLyric);
					Utilities.sleep();

					String lyrica = lyric.select(lyricDiv).text();
//					System.out.println(lyrica);
					System.out.println("OK");
					return lyrica;
				}
			}
		}
		System.out.println("ERROR");
		return null;
	}

	public static String changeStringToURL(String msg) {
		return msg.toLowerCase().replace("'", "").replace("& ", "").replace(" ", "_").replace("-", "_").replace(".", "");
	}
}
