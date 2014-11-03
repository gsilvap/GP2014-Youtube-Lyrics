package youtube;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utilities {
	
	public static boolean DEBUG = false;
	
	public static ArrayList<String> runCmd(String cmd, String id) {
		ArrayList<String> result = new ArrayList<String>();
		if (DEBUG) debug("[cmd] " + cmd);
		int exitValue = 0;
		String s;
		Process p;
		try {
			p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((s = br.readLine()) != null) {
				if (DEBUG) debug("["+id+"] "+s);
				result.add(s);
			}
			p.waitFor();
			exitValue = p.exitValue();
			if (DEBUG) debug("["+id+"] [exit] " + exitValue);
			p.destroy();
			result.add(Integer.toString(exitValue));
		} catch (Exception e) {
			System.out.println("Excepcao");
		}
		return result;
	}
	
	public static void debug(String msg)
	{
		System.out.println("[DEBUG] " + msg);
	}
	
	public static void deleteFilesByExtension(String extension) {
//		Elimina ficheiros .extension
		File folder = new File(".");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].toString().endsWith(extension))
			{
				listOfFiles[i].delete();
			}
		}
	}
}
