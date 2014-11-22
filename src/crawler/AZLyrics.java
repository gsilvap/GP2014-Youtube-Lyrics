package crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AZLyrics {
	
	private static String URL = "http://search.azlyrics.com/search.php?q=";
	private static String resultsDiv = "div.sen";
	private static String lyricDiv = "div#main div[style=\"margin-left:10px;margin-right:10px;\"]";
	
//	TODO Cleaning
//	TODO remove prints and return lyrica
	public static int downloadLyric(String nameAuthor, String nameMusic, int debug) {
		String urlSearch = URL + Utilities.changeStringToSearch(nameAuthor)+"+"+Utilities.changeStringToSearch(nameMusic);
		if (debug == 1)System.out.println(urlSearch);
		Document doc = Utilities.getDoc(urlSearch);
		if (doc != null)
		{
			Elements lyrics = doc.select(resultsDiv);
			for (Element element : lyrics) {
				String urlOfLyric = element.select("a").attr("href");
				if (debug == 1)System.out.println(urlOfLyric);
				
				if (urlOfLyric.contains(Utilities.changeStringToURL(nameMusic)) && urlOfLyric.contains(Utilities.changeStringToURL(nameAuthor)))
				{
//					System.out.println("Correspondencia a 100%");
					Document lyric = Utilities.getDoc(urlSearch);
					Utilities.sleep();
//					Elements aux = lyric.select("div");
					String lyrica = lyric.select(lyricDiv).text();
					
					System.out.println(lyrica);
					return 1;
//					System.out.println(lyric.select("div#main [margin-left:10px;margin-right:10px;]"));
				}
			}
		}
		return 0;
	}
}
