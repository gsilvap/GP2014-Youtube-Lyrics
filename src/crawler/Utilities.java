package crawler;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

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

	public static String changeStringToURL(String msg)
	{
		return msg.toLowerCase().replace("the ", "").replace(" ", "");
	}
	
	public static String changeStringToURL(String msg, String character)
	{
		return msg.toLowerCase().replace("'", "").replace("& ", "").replace(" ", character);
	}
	
	public static  String changeStringToSearch(String msg)
	{
		return msg.replace(" ", "+");
	}
	
	public static  Document getDoc(String url)
	{
		try {
			Response response = Jsoup.connect(url)
						.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
						.referrer("http://www.google.com")
						.timeout(0)
						.execute();
			return response.parse();
		} catch (Exception e) {
			System.out.println("Excepcao");
			return null;
		}
	}
}
