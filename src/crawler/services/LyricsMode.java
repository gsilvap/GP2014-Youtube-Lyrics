package crawler.services;

import java.util.ArrayList;

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
		String urlSearch = URL + Utilities.changeStringToSearch(nameAuthor) + "+" + Utilities.changeStringToSearch(nameMusic);

		ArrayList<String> links = new ArrayList<String>();
		String aux = "";
		if (debug == 1)
			System.out.println(urlSearch);
		aux = nameAuthor + "\n" + nameMusic + "\n" + urlSearch;
		Document doc = Utilities.getDoc(urlSearch);
		if (doc != null) {
			Elements lyrics = doc.select(resultsDiv);
			for (Element element : lyrics) {
				String urlOfLyric = element.select("a").attr("href");

				urlOfLyric = "http://www.lyricsmode.com" + urlOfLyric;
				if (debug == 1)
					System.out.println(urlOfLyric);
				aux += "\n" + urlOfLyric;
				if (urlOfLyric.contains(changeStringToURL(nameMusic)) && urlOfLyric.contains(changeStringToURL(nameAuthor))) {
					Document lyric = Utilities.getDoc(urlOfLyric);
					Utilities.sleep();

					String lyrica = lyric.select(lyricDiv).text();
					System.out.println(lyrica);
					aux += "\n" + lyrica;
					links.add(aux);
					return lyrica;
				}
			}
			links.add(aux);
			//FIXME:WRITE LIST TO FILE
		}
		return null;
	}

	public static String changeStringToURL(String msg) {
		return msg.toLowerCase().replace("'", "").replace("& ", "").replace(" ", "_");
	}
}
