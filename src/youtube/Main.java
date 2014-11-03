package youtube;
import java.io.IOException;
import java.util.ArrayList;

import readAndValidateData.TextFile;

public class Main {	
		
	public static void main(String args[]) {
		Main program = new Main();
		program.run();
	}
	
	private void run() {
		TextFile regexFile = new TextFile();
		String regex = "";
		String pattern = "";
		try {
			regexFile.openTextFileToRead("regex");
			regex = regexFile.readLine();
			pattern = regexFile.readLine();
			regexFile.closeRead();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} 
		
//		System.out.println("'"+regex+"'");
//		System.out.println(pattern);
		
//		System.exit(0);
		Utilities.deleteFilesByExtension(".part");
		ArrayList<Video> links = populate();
		
		for (Video video : links) {
			video.validate(regex, pattern);
			if (video.isValid())
			{
				video.downloadTitle();
				video.downloadFilename();
				video.downloadVideo();
			}
			else
				System.out.println("Video invalido");
			
			System.out.println(video.getTitle());
			System.out.println(video.getState());
			System.out.println(video.getFilename());
//			break;
		}
	}
	
	private ArrayList<Video> populate() {
		ArrayList<Video> links = new ArrayList<Video>();
		
		links.add(new Video("https://www.youtube.com/watch?v=1YWDLjvfEs4"));
		links.add(new Video("http://www.youtube.com/watch?v=1YWDLjvfEs4"));
		links.add(new Video("http://www.youtube.com/watch?v=Iv-Xmv2yjjQ"));
		links.add(new Video("http://www.youtube.com/watch?v=jK88pRSakms&list=RDjK88pRSakms#t=168"));
		links.add(new Video("https://www.youtube.com/watch?v=ujQ0WOUD3H0&index=5&list=RDj1V33b2ZEIo"));
		links.add(new Video("https://www.youtube.com/watch?list=RDj1V33b2ZEIo&v=Ug_OM0WzVmA&index=8"));
		links.add(new Video("https://www.youtube.com/v/JdUxQEnAYRU"));
		links.add(new Video("http://www.youtube.com/watch?v=iwGFalTRHDA"));
		links.add(new Video("https://www.youtube.com/watch?v=iwGFalTRHDA"));
		links.add(new Video("http://www.youtube.com/watch?v=iwGFalTRHDA&feature=related"));
		links.add(new Video("http://youtu.be/iwGFalTRHDA"));
		links.add(new Video("http://www.youtube.com/embed/watch?feature=player_embedded&v=iwGFalTRHDA"));
		links.add(new Video("http://www.youtube.com/embed/watch?v=iwGFalTRHDA"));
		links.add(new Video("http://www.youtube.com/embed/v=iwGFalTRHDA"));
		links.add(new Video("http://www.youtube.com/watch?feature=player_embedded&v=iwGFalTRHDA"));
		links.add(new Video("http://www.youtube.com/watch?v=iwGFalTRHDA"));
		links.add(new Video("www.youtube.com/watch?v=iwGFalTRHDA"));
		links.add(new Video("www.youtu.be/iwGFalTRHDA"));
		links.add(new Video("youtu.be/iwGFalTRHDA"));
		links.add(new Video("youtube.com/watch?v=iwGFalTRHDA"));
		links.add(new Video("http://www.youtube.com/watch/iwGFalTRHDA"));
		links.add(new Video("http://www.youtube.com/v/iwGFalTRHDA"));
		links.add(new Video("http://www.youtube.com/v/i_GFalTRHDA"));
		links.add(new Video("http://www.youtube.com/watch?v=i-GFalTRHDA&feature=related"));
		links.add(new Video("http://www.youtube.com/attribution_link?u=/watch?v=aGmiw_rrNxk&feature=share&a=9QlmP1yvjcllp0h3l0NwuA"));
		links.add(new Video("http://www.youtube.com/attribution_link?a=fF1CWYwxCQ4&u=/watch?v=qYr8opTPSaQ&feature=em-uploademail"));
		links.add(new Video("http://www.youtube.com/attribution_link?a=fF1CWYwxCQ4&feature=em-uploademail&u=/watch?v=qYr8opTPSaQ"));
		links.add(new Video("https://www.youtube.com/watch?v=bbPBPZUN92U&feature=player_embedded"));
		links.add(new Video("https://www.youtube.com/watch?v=bbPBPZUN92U"));
		links.add(new Video("http://youtu.be/jJT0Suanqhg?t=37s"));
		links.add(new Video("http://youtu.be/jJT0Suanqhg"));
		links.add(new Video("http://youtu.be/j1V33b2ZEIo?list=RDj1V33b2ZEIo"));
		
		return links;
	}
}
