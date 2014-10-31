import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
	
	public static boolean DEBUG = true;

	public static void main(String args[]) {

		// String youtubeUrl = Read.readString("Introduza o link do YouTube: ");
//		String youtubeUrl = new String("https://www.youtube.com/watch?v=Iv-sXmv2yjjQ");
		String youtubeUrl = new String("http://www.youtube.com/watch?v=jK88pRSakms&list=PL7FDC95FD05B2EDAF");

//		Verifica se o URL e valido
		if (youtubeUrl.startsWith("https://www.youtube.com/watch?v=") || 
			youtubeUrl.startsWith("http://www.youtube.com/watch?v=") || 
			youtubeUrl.startsWith("www.youtube.com/watch?v=") || 
			youtubeUrl.startsWith("https://www.youtube.com/v/") ||
			youtubeUrl.startsWith("http://www.youtube.com/v/") ||
			youtubeUrl.startsWith("www.youtube.com/v/"))
		{
//			Verifica se e uma lista e retira so o id
			if (youtubeUrl.contains("&list="))
			{
				System.out.println("Erro: O link e de uma lista, so vai ser transferido o video atual!");
				youtubeUrl = new String(youtubeUrl.substring(0, youtubeUrl.indexOf("&list=")));
			}
			ArrayList<String> result = new ArrayList<String>();
			result = downloadYoutubeVideo(youtubeUrl, "");
			
//			Se o comando não for bem executado
			if (result.size()-1 > 0 && result.get(result.size()-1).compareTo("1") == 0)
			{
				System.out.println("URL invalido!");
			}
			else if (result.size()-2 > 0 && result.get(result.size()-2).contains("has already been downloaded"))
			{
				System.out.println("Ja foi feito o download");
			}
//			Se o comando for bem executado
			else {
				System.out.println("Transferencia executada com sucesso.");
			}
		}

//		String format = new String("");
//		getAllFormats(youtubeUrl);
	}

	public static ArrayList<String> downloadYoutubeVideo(String youtubeUrl, String format) {
		if (format.compareTo("") != 0) {
			return runCmd("youtube-dl " + "-f " + format + " " + youtubeUrl);
		} else
			return runCmd("youtube-dl " + youtubeUrl);
	}

	public static ArrayList<String> runCmd(String cmd) {
		ArrayList<String> result = new ArrayList<String>();
		debug("[cmd] " + cmd);
//		if (DEBUG) System.out.println("[DEBUG] [cmd] " + cmd);
		int exitValue = 0;
		String s;
		Process p;
		try {
			p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((s = br.readLine()) != null) {
				debug(s);
//				if (DEBUG) System.out.println("[DEBUG] "+s);
				result.add(s);
			}
			p.waitFor();
			exitValue = p.exitValue();
			debug("[exit] " + exitValue);
//			if (DEBUG) System.out.println("[DEBUG] [exit] " + exitValue);
			p.destroy();
			result.add(Integer.toString(exitValue));
		} catch (Exception e) {
			System.out.println("Excepcao");
		}
		return result;
	}

	public static ArrayList<String> getAllFormats(String youtubeUrl) {
		return runCmd(("youtube-dl " + "-F " + youtubeUrl));
	}
	
	public static void debug(String msg)
	{
		if (DEBUG) System.out.println("[DEBUG] " + msg);
	}
}
