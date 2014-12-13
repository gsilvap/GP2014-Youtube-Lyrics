package crawler;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class Utilities {
	
	/**
	 * Faz um sleep de 2 segundos
	 * 
	 * */
	public static void sleep() {
		sleep(2000);
	}

	/**
	 * @param sleep
	 * Recebe em milisegundos o tempo que a thread vai fazer sleep
	 * 
	 * */
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param msg
	 * Recebe uma string e substitui os espacos por '+' para que possa ser inserida num url
	 * 
	 * */
	public static String changeStringToSearch(String msg) {
		return msg.replace(" ", "+");
	}

	/**
	 * @param str
	 * Recebe uma string
	 * Retorna a string recebida sem acentuacao e carateres especiais 
	 * 
	 * */
	public static String unAccent(String str) {
		String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}
	
	/**
	 * @param html
	 * Recebe uma string html
	 * Retorna uma string sem as tags html preservando as mudancas de linha  
	 * 
	 * */
	public static String br2nl(String html) {
		if (html == null)
			return html;
		Document document = Jsoup.parse(html);
		document.outputSettings(new Document.OutputSettings().prettyPrint(false));
		document.select("br").append("\\n");
		document.select("p").prepend("\\n\\n");
		String s = document.html().replaceAll("\\\\n", "\n");
		return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
	}
	
	/**
	 * @param url
	 * Recebe um url de uma pagina
	 * Retorna a resposta obtida do pedido JSOUP 
	 * 
	 * */
	public static Document getDoc(String url) {
		try {
			Response response = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").referrer("http://www.google.com").timeout(0).execute();
			return response.parse();
		} catch (Exception e) {
			System.out.println("Excepcao");
			return null;
		}
	}
	
	/**
	 * @param music
	 * Recebe o titulo da musica e efetua o clean ao mesmo
	 * limpando informacao e carateres irrelevantes 
	 * 
	 * */
	public static String cleanString(String music) {

		//lista de possiveis sequencias que tem de ser removidas
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

		//Remocao da acentuacao
		music = Utilities.unAccent(music).toLowerCase();

		//Efetua a limpeza das sequencias irrelevantes segundo o regexReplaceList
		for (Map.Entry<String, String> element : regexReplaceList.entrySet())
			music = music.replaceAll(element.getKey(), element.getValue());

		//Limpa a existencia de espacos no inicio ou no fim do titulo a usar na pesquisa
		music = music.trim();

		return music;
	}
}
