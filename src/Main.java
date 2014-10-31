import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
	
	public static boolean DEBUG = true;

	public static void main(String args[]) {
//		Elimina ficheiros .part
		File folder = new File(".");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].toString().endsWith(".part"))
			{
				listOfFiles[i].delete();
			}
		}
		
//		String youtubeUrl = args[0];
//		String youtubeUrl = Read.readString("Introduza o link do YouTube: ");
//		String youtubeUrl = new String("https://www.youtube.com/watch?v=Iv-sXmv2yjjQ");
//		String youtubeUrl = new String("http://www.youtube.com/watch?v=jK88pRSakms&list=PL7FDC95FD05B2EDAF");

		String url1 = new String("https://www.youtube.com/watch?v=1YWDLjvfEs4");
		String url2 = new String("http://youtu.be/jJT0Suanqhg?t=37s");
		String url3 = new String("http://www.youtube.com/watch?v=Iv-Xmv2yjjQ");
		String url4 = new String("http://www.youtube.com/watch?v=jK88pRSakms&list=RDjK88pRSakms#t=168");
		String url5 = new String("http://youtu.be/j1V33b2ZEIo?list=RDj1V33b2ZEIo");
		String url6 = new String("https://www.youtube.com/watch?v=ujQ0WOUD3H0&index=5&list=RDj1V33b2ZEIo");
		String url7 = new String("https://www.youtube.com/watch?list=RDj1V33b2ZEIo&v=Ug_OM0WzVmA&index=8");
		String url8 = new String("https://www.youtube.com/watch?v=zQqAxB5DjgQ");
		String url9 = new String("http://www.youtube.com/watch?v=bgpU6nWUgGY");


//		doWork(url1);
//		doWork(url2);
//		doWork(url3);
//		doWork(url4);
//		doWork(url5);
//		doWork(url6);
//		doWork(url7);
//		doWork(url8);
		doWork(url9);
	}
	
	public static void doWork(String youtubeUrl)
	{
		debug(youtubeUrl);
		String id = new String();
//		Verifica se o URL e valido
		if (youtubeUrl.startsWith("https://www.youtube.com/watch?v=") 
				|| youtubeUrl.startsWith("http://www.youtube.com/watch?v=") 
				|| youtubeUrl.startsWith("www.youtube.com/watch?v=") 
				|| youtubeUrl.startsWith("youtube.com/watch?v=")
				|| youtubeUrl.startsWith("http://youtube.com/watch?v=")
				|| youtubeUrl.startsWith("https://youtube.com/watch?v=") 
							
				|| youtubeUrl.startsWith("https://www.youtube.com/v/")
				|| youtubeUrl.startsWith("http://www.youtube.com/v/")
				|| youtubeUrl.startsWith("www.youtube.com/v/") 
				|| youtubeUrl.startsWith("youtube.com/v/") 
				|| youtubeUrl.startsWith("http://youtube.com/v/") 
				|| youtubeUrl.startsWith("https://youtube.com/v/")

				|| youtubeUrl.startsWith("https://youtu.be/") 
				|| youtubeUrl.startsWith("http://youtu.be/") 
				|| youtubeUrl.startsWith("youtu.be/"))
		{
			
			
//			Verifica se e uma lista e retira so o id do primeiro
			debug("Verifica se e lista");
			if (youtubeUrl.contains("&list="))
			{
				System.out.println("Erro: link de uma lista, so vai ser transferido o video atual!");
				youtubeUrl = new String(youtubeUrl.substring(0, youtubeUrl.indexOf("&list=")));
			}
//			Verifica se o id tem 11 caracteres
			debug("Verifica se tem 11 caracteres");
			if (!youtubeUrl.contains("youtu.be/") && youtubeUrl.contains("="))
			{
				id = new String(youtubeUrl.substring(youtubeUrl.indexOf("="), youtubeUrl.length()-1));
				if (id.length() != 11) 
				{
					System.out.println("Erro: id invalido!");
					return;
				}
			}
			else if (youtubeUrl.contains("youtu.be/") && youtubeUrl.contains("?")) {
				id = new String(youtubeUrl.substring(youtubeUrl.indexOf("be/")+3, youtubeUrl.indexOf("?")));
//				System.out.println(id);
				if (id.length() != 11) 
				{
					System.out.println("Erro: id invalido!");
					return;
				}
			}
			
			ArrayList<String> result = new ArrayList<String>();
			result = downloadYoutubeVideo(youtubeUrl, "");
			
//			Se o comando n�o for bem executado
			if (result.size()-1 > 0 && result.get(result.size()-1).compareTo("1") == 0)
			{
				System.out.println("URL invalido!");
			}
//			Verifica se o download ja foi feito anteriormente
			else if (result.size()-2 > 0 && result.get(result.size()-2).contains("has already been downloaded"))
			{
				System.out.println("["+id+"] " + "Ja foi feito o download!");
			}
//			Se o comando for bem executado
			else if (result.size()-1 > 0 && result.get(result.size()-1).compareTo("0") == 0) {
				System.out.println("["+id+"] " + "Transferencia executada com sucesso.");
			}
//			TODO Verificar outros erros
			else {
				System.out.println("\\TODO Outros erros, quais?");
			}
		}

//		String format = new String("");
//		getAllFormats(youtubeUrl);
		System.out.println();
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
		int exitValue = 0;
		String s;
		Process p;
		try {
			p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((s = br.readLine()) != null) {
				debug(s);
				result.add(s);
			}
			p.waitFor();
			exitValue = p.exitValue();
			debug("[exit] " + exitValue);
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
