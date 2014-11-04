package youtube;
import java.io.IOException;
import java.util.ArrayList;

import readAndValidateData.TextFile;

public class Main {	
	
	private final int THREADS = 5;
	
	private int threadControl;
	private ArrayList<Thread> threadsList;
	
	public static void main(String args[]) {
		System.out.println("asd");
		Main program = new Main();		
		program.threadsList = new ArrayList<Thread>();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				program.threadsList.forEach(element -> {
					if(element.isAlive())
						element.interrupt();
				});
			}
		});
		
		program.run();
		
	}
	
	private void run() {		
		threadControl = 0;
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
		Utilities.deleteFilesByExtension(".mp4");
		ArrayList<Video> links = populate();
		
		
		for (Video video : links) {
			video.validate(regex, pattern);
			if (video.isValid())
			{	
				// Download de varios links na máximo 5 em simultaneo
				while(threadControl>=THREADS){
					Utilities.debug("[BUZY] Todos os slots de download estao ocupados (proxima tentativa 5s)");
					this.timer(5);
				}
				
				Thread t = new Thread(() -> {
					threadControl++;
					Utilities.debug("[DOWNLOADING] ["+video.getId()+"] Metadata");
					video.downloadFilename();
					if (video.getState()==Video.State.VALID){
						video.downloadTitle();
						Utilities.debug("[DOWNLOADING] ["+video.getId()+"] "+video.getTitle());
						if (video.getState()==Video.State.VALID){
							video.downloadVideo();		
							Utilities.debug("[TERMINATED ] ["+video.getId()+"] "+video.getTitle());
						}else{
							System.out.println("[ERRO] ["+video.getId()+"]  Link inacessivel");
						}
					}else{
						System.out.println("[ERRO] ["+video.getId()+"]  Link inacessivel");
					}
					threadControl--;
				});
				threadsList.add(t);
				t.start();
			}
			else
				System.out.println("Video invalido");
			
//			Utilities.debug("[STATE] ["+video.getId()+"] "+video.getState());
		}
	}
	
	private ArrayList<Video> populate() {
		ArrayList<Video> links = new ArrayList<Video>();
		//Link 404
		links.add(new Video("https://www.youtube.com/watch?v=i-GFalTRHDA"));
		
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
	
	private void timer(double seconds) {
		try {
			Thread.sleep((int) (1000 * seconds));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
