import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;

import readAndValidateData.Read;

public class Main {

	public static void main(String args[]) {

		// String youtubeUrl = Read.readString("Introduza o link do YouTube: ");
		String youtubeUrl = new String("https://www.youtube.com/watch?v=Iv-Xmv2yjjQ");

		String format = new String("");
//		downloadYoutubeVideo(youtubeUrl, format);
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
		System.out.println("cmd: " + cmd);
		int exitValue = 0;
		String s;
		Process p;
		try {
			p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((s = br.readLine()) != null) {

				System.out.println(s);
				result.add(s);
			}
			p.waitFor();
			exitValue = p.exitValue();
			System.out.println("exit: " + exitValue);
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
}
