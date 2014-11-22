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
		String urlSearch = URL + Utilities.changeStringToSearch(nameAuthor) + "+" + Utilities.changeStringToSearch(nameMusic);

		if (debug == 1)
			System.out.println(urlSearch);

		Document doc = Utilities.getDoc(urlSearch);
		if (doc != null) {
			Elements lyrics = doc.select(resultsDiv);
			for (Element element : lyrics) {
				String urlOfLyric = element.select("a").attr("href");

				//				try {
				//					FileUtils.copyURLToFile(new URL(urlOfLyric), new File(nameAuthor+" "+nameMusic+".lyric"));
				//				} catch (IOException e) {
				//					// TODO Auto-generated catch block
				//					e.printStackTrace();
				//				}

				urlOfLyric = "http://www.lyricsmode.com" + urlOfLyric;
				if (debug == 1)
					System.out.println(urlOfLyric);

				if (urlOfLyric.contains(Utilities.changeStringToURL(nameMusic, "_")) && urlOfLyric.contains(Utilities.changeStringToURL(nameAuthor, "_"))) {
					Document lyric = Utilities.getDoc(urlOfLyric);
					Utilities.sleep();

					String lyrica = lyric.select(lyricDiv).text();
					System.out.println(lyrica);
					return lyrica;
				}
			}
		}
		return null;
	}
}
