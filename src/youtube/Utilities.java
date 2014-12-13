package youtube;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class para efetuar o download dos videos 
 * @author Celso Rafael Clara Mendes 2009109378
 * @author Goncalo Silva Pereira 2009111643
 */
public class Utilities {

	public static boolean DEBUG = true;
	public static String DIRECTORY = "resources";

	/**
	 * Executa as chamadas ao youtube-dl
	 * @param cmd Comando a executar
	 * @param id Id do video
	 * @return ArrayList<String> result
	 */
	public static ArrayList<String> runCmd(String cmd, String id) {
		ArrayList<String> result = new ArrayList<String>();
		if (DEBUG)
			debug("[cmd] " + cmd);
		int exitValue = 0;
		String s;
		Process p;
		try {
			new File(DIRECTORY+"\\"+id).mkdirs();
			p = Runtime.getRuntime().exec(cmd, null, new File(DIRECTORY+"\\"+id));
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((s = br.readLine()) != null) {
				if (DEBUG) debug("[" + id + "] " + s);
				result.add(s);
			}
			p.waitFor();
			exitValue = p.exitValue();
			if (DEBUG) debug("[" + id + "] [exit] " + exitValue);
			p.destroy();
			result.add(Integer.toString(exitValue));
		} catch (IOException e) {
			System.out.println("[ERROR] Verifique os requisitos minimos do sistema.\n[ERROR] Necessario instalar youtube-dl e python\n");
		} catch (Exception e) {
			System.out.println("Excepcao");
		}
		return result;
	}

	public static void debug(String msg) {
		System.out.println("[DEBUG] " + msg);
	}

	/**
	 * Elimina os ficheiros .extension 
	 * @param extension extensao do ficheiro a eliminar
	 */
	public static void deleteFilesByExtension(String extension) {
		File folder = new File(".");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].toString().endsWith(extension))
				listOfFiles[i].delete();

		}
	}

}
