package crawler;

import java.text.Normalizer;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class Utilities {
	public static void sleep() {
		sleep(2000);
	}

	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String changeStringToSearch(String msg) {
		return msg.replace(" ", "+");
	}

	public static String unAccent(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

	public static String br2nl(String html) {
		if (html == null)
			return html;
		Document document = Jsoup.parse(html);
		//makes html() preserve linebreaks and spacing
		document.outputSettings(new Document.OutputSettings().prettyPrint(false));
		document.select("br").append("\\n");
		document.select("p").prepend("\\n\\n");
		String s = document.html().replaceAll("\\\\n", "\n");
		return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
	}
	
	public static Document getDoc(String url) {
		try {
			Response response = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").referrer("http://www.google.com").timeout(0).execute();
			return response.parse();
		} catch (Exception e) {
			System.out.println("Excepcao");
			return null;
		}
	}
}
