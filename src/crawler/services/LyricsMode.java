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
	public String downloadLyric(String music, int debug) {
//		String music = "James Arthur - Impossible (Lyrics)";

		music = Utilities.unAccent(music).toLowerCase().replace("ft.", "feat");
		
//		nameAuthor = Utilities.unAccent(nameAuthor).toLowerCase().replace("ft.", "feat");
//		nameMusic = Utilities.unAccent(nameMusic);
//		nameAuthor = nameAuthor.replaceAll("[\\]\\[(){},.;!?<>%]", "");
		
		music = music.replaceAll("[\\]\\[(){},.;!?<>%+~<>*\"]", "");
		music = music.replace("_", "").replace("-", " ").replace("  ", " ").replace("instrumental","").replace("version","")
				.replace("w/","").replace("official","").replace("video", "").replace("download", "").replace("youtube","")
				.replace("in the style of","").replace(" hd "," ").replace(" hq ", " ");
		music = music.replace("with lyrics","").replace("lyrics", "").replace("on screen","").replace("karaoke", "").replace("   ", " ").replace("  ", " ");

		//		String urlSearch = URL + Utilities.changeStringToSearch(nameAuthor) + "+" + Utilities.changeStringToSearch(nameMusic);
		
		if (music.endsWith(" ")){
			music = music.substring(0, music.length()-1);
		}
		
		String urlSearch = URL + Utilities.changeStringToSearch(music);
		
//		System.out.println(urlSearch);
		
		music = music.toLowerCase().replace("the ", "");
//		nameAuthor = nameAuthor.toLowerCase().replace("the ", "");
		
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
					if (urlOfLyric.contains(changeStringToURL(str))){
						count++;
					}
				}
				
				
				if ((count/words.length)>0.70) {
//				if (urlOfLyric.contains(changeStringToURL(nameMusic)) && urlOfLyric.contains(changeStringToURL(nameAuthor))) {
					Document lyric = Utilities.getDoc(urlOfLyric);
					Utilities.sleep();

					String lyrica = lyric.select(lyricDiv).text();
//					System.out.println(lyrica);
					System.out.println("OK");
					return lyrica;
				}else{
					
					System.out.println("Values:" + count + "+" + words.length + "+" + count/words.length);
				
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
