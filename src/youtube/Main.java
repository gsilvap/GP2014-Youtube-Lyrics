package youtube;

import java.io.IOException;
import java.util.ArrayList;

import readAndValidateData.TextFile;

/**
 * Class Main - classe principal
 * 
 * @author Celso Rafael Clara Mendes 2009109378
 * @author Goncalo Silva Pereira 2009111643
 */
public class Main {

	private String regex = "";
	private String pattern = "";
	
	private final int THREADS = 5;

	private int threadControl;

	private ArrayList<Thread> threadsList;

	/**
	 * Funcao principal, cria e executa o programa adiciona
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Main program = new Main();

		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		
		// TODO Integrar com ficheiro e startup

		program.threadsList = new ArrayList<Thread>();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				program.threadsList.forEach(element ->
				{
					if (element.isAlive())
						element.interrupt();
				});
			}
		});

		program.run(args);
	}

	/**
	 * Incrementa a variavel ao criar uma Thread
	 */
	public synchronized void addThread() {
		threadControl++;
	}

	/**
	 * Decrementa a variavel ao terminar uma thread
	 */
	public synchronized void closeThread() {
		threadControl--;
	}

	/**
	 * executa o programa
	 */
	private void run(String args[]) {
		threadControl = 0;
		TextFile regexFile = new TextFile();
		
		try {
			regexFile.openTextFileToRead("regex");
			regex = regexFile.readLine();
			pattern = regexFile.readLine();
			regexFile.closeRead();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			regex = "";
			pattern = "";
		}
//		String regexa = "(https?://)?" + "(www\\.)?" + "(youtu\\.be/|youtube\\.com/)?" + "(" + "(.+/)?" + "(watch" + "(\\?v=|.+&v=)" + ")?" + "(v=)?" + ")" + "([\\w_-]{11})" + "(&.+)?" + "(\\?list=([\\w_-]{13}))?" + "(\\?t=[0-9]*s)?" + "(\\\\?.+)?";
//		System.out.println(regex);
//		System.out.println(regexa);
//		
//		String	patterna = "(be/|v=|/v/|/embed/|/watch/)([\\w_-]{11})";
//		System.out.println(pattern);
//		System.out.println(patterna);
//		
//		 System.out.println("'"+regex+"'");
//		 System.out.println("'"+pattern+"'");

		Utilities.deleteFilesByExtension(".part");
		Utilities.deleteFilesByExtension(".jpg");
		Utilities.deleteFilesByExtension(".mp4");
		
		ArrayList<Video> links = new ArrayList<Video>();
		
		if (args != null && args.length != 0)
		{
			for (int i = 0; i < args.length; i++) {
				links.add(new Video(args[i]));
//				System.out.println(args[i]);
			}
			
//			links
		}
		else
		{
			links = populate();
		}
		
		for (Video video : links) {
			downloadInformation(video);
//			downloadInformation(links.get(0));
		}
	}
	
	private void downloadInformation(Video video)
	{
//		TODO Clean
//		System.out.println(Utilities.returnId(video.getUrl(), ""));
		video.validate(regex, pattern);
//		video.validate("", "");
//		System.out.println("Validade: "+video.isValid()+" "+video.getUrl());
		if (video.isValid()) {
			// System.out.println("[N THREADS] --> " + threadControl);
			// Download de varios links na máximo 5 em simultaneo
			while (threadControl >= THREADS) {
//				Utilities.debug("[BUZY] Todos os slots de download estao ocupados (proxima tentativa 5s)");
				this.timer(5);
			}

			addThread();
			Thread t = new Thread(() ->
			{
//				Utilities.debug("[DOWNLOADING] [" + video.getId() + "] Metadata");
//				video.downloadVideo();
				video.downloadFilename();
				if (video.getState() == Video.State.VALID) {
					video.downloadTitle();
//					Utilities.debug("[DOWNLOADING] [" + video.getId() + "] " + video.getTitle());
					if (video.getState() == Video.State.VALID) {
						video.downloadVideo();
						Utilities.debug("[TERMINATED ] [" + video.getId() + "] " + video.getTitle());
					} else {
						System.out.println("[ERROR] [" + video.getId() + "]  Link inacessivel Title");
					}
				} else {
					System.out.println("[ERROR] [" + video.getId() + "]  Link inacessivel Filename");
				}
				closeThread();
			});
			threadsList.add(t);
			t.start();
		} 
		else
		{
			System.out.println("Video invalido");
			// Utilities.debug("[STATE] ["+video.getId()+"] "+video.getState());
		}
	}

	/**
	 * Popular o sistema com links de teste
	 * 
	 * @return links Lista de Videos a verificar
	 */
	private ArrayList<Video> populate() {
		ArrayList<Video> links = new ArrayList<Video>();
		links.add(new Video("https://www.youtube.com/watch?v=xlEQnZdlaeE"));
		links.add(new Video("http://www.youtube.com/v/i_GFalTRHDA"));
		links.add(new Video("https://www.youtube.com/watch?v=i-GFalTRHDA&feature=related"));
		links.add(new Video("http://www.youtube.com/embed/v=iwGFalTRHDA"));
		links.add(new Video("http://www.youtube.com/attribution_link?u=/watch?v=aGmiw_rrNxk&feature=share&a=9QlmP1yvjcllp0h3l0NwuA"));
		links.add(new Video("http://www.youtube.com/attribution_link?a=fF1CWYwxCQ4&u=/watch?v=qYr8opTPSaQ&feature=em-uploademail"));
		links.add(new Video("http://www.youtube.com/attribution_link?a=fF1CWYwxCQ4&feature=em-uploademail&u=/watch?v=qYr8opTPSaQ"));
		links.add(new Video("https://www.youtube.com/watch?v=UIwCZmb8G90&feature=youtu.be"));
		links.add(new Video("https://www.youtube.com/watch?v=ujQ0WOUD3H0&index=5&list=RDj1V33b2ZEIo"));
		links.add(new Video("https://www.youtube.com/watch?v=81_2Xb0XB_E&feature=relmfu"));
		links.add(new Video("https://www.youtube.com/watch?v=9dgng_ekbV0&feature=player_embedded"));
		links.add(new Video("https://www.youtube.com/watch?v=bbPBPZUN92U&feature=player_embedded"));
		links.add(new Video("https://www.youtube.com/watch?v=bbPBPZUN92U8&feature=player_embedded"));
		links.add(new Video("https://www.youtube.com/watch?v=C5UKGqfFkBs&wadsworth=1"));
		links.add(new Video("https://www.youtube.com/watch?v=-JbdeP0DwOA&list=RD-JbdeP0DwOA#t=2"));
		links.add(new Video("https://www.youtube.com/watch?v=oEwETSWCUSg&feature=related"));
		links.add(new Video("https://www.youtube.com/watch?v=VGued3buX0I&hd=1"));
		links.add(new Video("https://www.youtube.com/watch?v=Y3CjJ8iQmPk"));
		links.add(new Video("https://www.youtube.com/watch?v=MvYJcJ7w8F4"));
		links.add(new Video("https://www.youtube.com/watch?feature=player_embedded&v=ESbqltvXKqk#!"));
		links.add(new Video("https://www.youtube.com/watch?v=siu1f5bVf2I"));
		links.add(new Video("https://www.youtube.com/watch?v=ESbqltvXKqk"));
		links.add(new Video("https://www.youtube.com/watch?v=4C7Fmg3_mkw"));
		links.add(new Video("https://www.youtube.com/watch?v=Ug_OM0WzVmA"));
		links.add(new Video("https://www.youtube.com/watch?v=Iv-Xmv2yjjQ"));
		links.add(new Video("https://www.youtube.com/v/Hnmf3j9DS5Q%26hl=en%26fs=1"));
		links.add(new Video("https://www.youtube.com/watch?v=JdUxQEnAYRU"));
		links.add(new Video("https://www.youtube.com/v/0rHEpRZeh-4?version=3&autohide=1"));
		links.add(new Video("https://www.youtube.com/watch?v=Hnmf3j9DS5Q"));
		links.add(new Video("https://www.youtube.com/watch?v=0rHEpRZeh-4"));
		links.add(new Video("https://www.youtube.com/embed/zvsf01QtLkY"));
		links.add(new Video("https://www.youtube.com/embed/K-Rs6YEZAt8"));
		links.add(new Video("https://www.youtube.com/embed/vI_Zc6Ngmgg?modestbranding=1&rel=0"));
		links.add(new Video("https://www.youtube.com/embed/3Uic_AS3MPE?rel=0"));
		links.add(new Video("https://www.youtube.com/embed/cZrsZC7fD3I?autoplay=1"));
		links.add(new Video("http://youtu.be/x88fJP0YCCg"));
		links.add(new Video("http://youtu.be/jJT0Suanqhg?t=37s"));
		links.add(new Video("http://www.youtube.com/watch?v=jK88pRSakms&list=RDjK88pRSakms#t=168"));
		links.add(new Video("http://youtu.be/j1V33b2ZEIo?list=RDj1V33b2ZEIo"));
		links.add(new Video("http://www.youtube.com/watch?v=1YWDLjvfEs4"));
		links.add(new Video("http://www.youtube.com/watch?v=bgpU6nWUgGY"));
		links.add(new Video("http://youtu.be/jJT0Suanqhg"));
		links.add(new Video("https://www.youtube.com/watch?v=1YWDLjvfEs4"));
		links.add(new Video("https://www.youtube.com/watch?v=1YXew37w1Hs"));
		links.add(new Video("https://www.youtube.com/watch?v=2MJj2A1Kx20"));
		links.add(new Video("https://www.youtube.com/watch?v=3jL_V_hXLM0"));
		links.add(new Video("https://www.youtube.com/watch?v=3Q7KZrFJbNs"));
		links.add(new Video("https://www.youtube.com/watch?v=4lV4Q0WG6co"));
		links.add(new Video("https://www.youtube.com/watch?v=6KhN5D757YM"));
		links.add(new Video("https://www.youtube.com/watch?v=6TDgsAYz6KY"));
		links.add(new Video("https://www.youtube.com/watch?v=6zdToMz-Qfk"));
		links.add(new Video("https://www.youtube.com/watch?v=7C8034DlmIM"));
		links.add(new Video("https://www.youtube.com/watch?v=7LD4e35nmGY"));
		links.add(new Video("https://www.youtube.com/watch?v=7mlRcf4AiOc"));
		links.add(new Video("https://www.youtube.com/watch?v=8vDeevvJANI"));
		links.add(new Video("https://www.youtube.com/watch?v=A-1cAVQlFlQ"));
		links.add(new Video("https://www.youtube.com/watch?v=Am19-Y27eTs"));
		links.add(new Video("https://www.youtube.com/watch?v=aXXC8q1U0rk"));
		links.add(new Video("https://www.youtube.com/watch?v=AZGAo43nQ5Q"));
		links.add(new Video("https://www.youtube.com/watch?v=b6e7eG6tV5E"));
		links.add(new Video("https://www.youtube.com/watch?v=bbPBPZUN92U"));
		links.add(new Video("https://www.youtube.com/watch?v=Bhha9VnJL0g"));
		links.add(new Video("https://www.youtube.com/watch?v=bSmZEonw3Ts"));
		links.add(new Video("https://www.youtube.com/watch?v=Ck0DmsTprCc"));
		links.add(new Video("https://www.youtube.com/watch?v=CTOnzUdPtrM"));
		links.add(new Video("https://www.youtube.com/watch?v=dskW-C0aScE"));
		links.add(new Video("https://www.youtube.com/watch?v=DWJfTzngmCk"));
		links.add(new Video("https://www.youtube.com/watch?v=EKmFwZZ3rF8"));
		links.add(new Video("https://www.youtube.com/watch?v=ElV069p2X0k"));
		links.add(new Video("https://www.youtube.com/watch?v=EO2AkzVXJ4c"));
		links.add(new Video("https://www.youtube.com/watch?v=EXnQRZv4nXg"));
		links.add(new Video("https://www.youtube.com/watch?v=FQ995_f7JNI"));
		links.add(new Video("https://www.youtube.com/watch?v=GNHX6wcKwII"));
		links.add(new Video("https://www.youtube.com/watch?v=H7nAs3F7Iqo"));
		links.add(new Video("https://www.youtube.com/watch?v=H7R_zgqvE8M"));
		links.add(new Video("https://www.youtube.com/watch?v=hmGlFy1KoAw"));
		links.add(new Video("https://www.youtube.com/watch?v=hsZhzwtnQsM"));
		links.add(new Video("https://www.youtube.com/watch?v=-i5MU14JLdw"));
		links.add(new Video("https://www.youtube.com/watch?v=IiAb-PWz3Gg"));
		links.add(new Video("https://www.youtube.com/watch?v=Ijgirho02bI"));
		links.add(new Video("https://www.youtube.com/watch?v=iwGFalTRHDA"));
		links.add(new Video("https://www.youtube.com/watch?v=kIlA62TvrjU"));
		links.add(new Video("https://www.youtube.com/watch?v=KoTQj3X3NtM"));
		links.add(new Video("https://www.youtube.com/watch?v=K_n-0GSIyCk"));
		links.add(new Video("https://www.youtube.com/watch?v=kQeAKFcANHk"));
		links.add(new Video("https://www.youtube.com/watch?v=L7EzS8wd__g"));
		links.add(new Video("https://www.youtube.com/watch?v=l8aYaOkFFV4"));
		links.add(new Video("https://www.youtube.com/watch?v=LaVnmra-yOU"));
		links.add(new Video("https://www.youtube.com/watch?v=LJPiUtE_4rs"));
		links.add(new Video("https://www.youtube.com/watch?v=LJwgepECBNU"));
		links.add(new Video("https://www.youtube.com/watch?v=mWskYLmuh0Q"));
		links.add(new Video("https://www.youtube.com/watch?v=MZxvu2sXzug"));
		links.add(new Video("https://www.youtube.com/watch?v=n5FHmOI670s"));
		links.add(new Video("https://www.youtube.com/watch?v=NDYAn0qPaNs"));
		links.add(new Video("https://www.youtube.com/watch?v=Nw08S2IIURA"));
		links.add(new Video("https://www.youtube.com/watch?v=onoBaLzYla4"));
		links.add(new Video("https://www.youtube.com/watch?v=oWuO_9-KNEQ"));
		links.add(new Video("https://www.youtube.com/watch?v=P3GLfRchowk"));
		links.add(new Video("https://www.youtube.com/watch?v=Pomrav8tMyk"));
		links.add(new Video("https://www.youtube.com/watch?v=qaxkpihAGsE"));
		links.add(new Video("https://www.youtube.com/watch?v=QQre5RbiyGM"));
		links.add(new Video("https://www.youtube.com/watch?v=-qQxqmMpITI"));
		links.add(new Video("https://www.youtube.com/watch?v=qSX-_Z5aQvY"));
		links.add(new Video("https://www.youtube.com/watch?v=qwVpiO3bTP0"));
		links.add(new Video("https://www.youtube.com/watch?v=s-L9BotSduA"));
		links.add(new Video("https://www.youtube.com/watch?v=T9ZQSGF4GLs"));
		links.add(new Video("https://www.youtube.com/watch?v=tpnsHbI8bLc"));
		links.add(new Video("https://www.youtube.com/watch?v=tsBBJ_AQsS0"));
		links.add(new Video("https://www.youtube.com/watch?v=ue8MKTBxVRI"));
		links.add(new Video("https://www.youtube.com/watch?v=Ul1gDkNLHUU"));
		links.add(new Video("https://www.youtube.com/watch?v=v1fLdvLaWV0"));
		links.add(new Video("https://www.youtube.com/watch?v=va3DMykBtPM"));
		links.add(new Video("https://www.youtube.com/watch?v=wMmTvKRat_k"));
		links.add(new Video("https://www.youtube.com/watch?v=xoYTljQiv0s"));
		links.add(new Video("https://www.youtube.com/watch?v=xVR6kJSqHtw"));
		links.add(new Video("https://www.youtube.com/watch?v=Yvo3tAb08bw"));
		links.add(new Video("https://www.youtube.com/watch?v=z4z45djLLn0"));
		links.add(new Video("https://www.youtube.com/watch?v=i-GFalTRHDA"));
		links.add(new Video("https://www.youtube.com/watch?v=j1D0-vzd-QY"));
		links.add(new Video("https://www.youtube.com/watch?v=lV1goHbQQzI"));
		links.add(new Video("https://www.youtube.com/watch?v=VgZMZK_0FTc"));
		links.add(new Video("https://www.youtube.com/watch?v=zQqAxB5DjgQ"));

		links.add(new Video("https://www.youtube.com/v/Hnmf3j9DS5Q%26hl=en%26fs=1"));
		links.add(new Video("https://www.youtube.com/v/0rHEpRZeh-4?version=3&autohide=1"));
		links.add(new Video("https://www.youtube.com/embed/vI_Zc6Ngmgg?modestbranding=1&rel=0"));
		links.add(new Video("https://www.youtube.com/embed/3Uic_AS3MPE?rel=0"));
		links.add(new Video("https://www.youtube.com/embed/cZrsZC7fD3I?autoplay=1 "));
		links.add(new Video("https://www.youtube.com/v/MvYJcJ7w8F4%26ap=%2526fmt=22"));
		links.add(new Video("https://www.youtube.com/v/siu1f5bVf2I?loop=1"));
		links.add(new Video("https://www.youtube.com/watch?v=bbPBPZUN92U8&feature=player_embedded"));
//		links.add(new Video("https://www.youtube.com/watch?feature=player_embedded&v=ESbqltvXKqk#!"));
		
		return links;
	}

	/**
	 * Funcao de sleep de x segundos
	 * 
	 * @param seconds
	 */
	private void timer(double seconds) {
		try {
			Thread.sleep((int) (1000 * seconds));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
