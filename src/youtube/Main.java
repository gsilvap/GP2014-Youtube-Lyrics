package youtube;
import java.util.ArrayList;

public class Main {	
	public static void main(String args[]) {
		Main program = new Main();
		program.run();
	}
	
	private void run() {
		// TODO Auto-generated method stub
		Utilities.deleteFilesByExtension(".part");
		ArrayList<Video> links = populate();
//		ArrayList<Video> ids = validateAndReturnIds(links);
		
//		System.out.println(links.size());
//		System.out.println(ids.size());
//		doWork(url);
		for (Video video : links) {
			video.validate();
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
			break;
		}
	}
	
	private ArrayList<Video> populate() {
		// TODO Auto-generated method stub
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
	
//	private String validate(String element) {
//		String regex = "";
////		TODO ler regex de ficheiro
//		regex = "(https?://)?"
//				+ "(www\\.)?"
//				+ "(youtu\\.be/|youtube\\.com/)?"
//				+ "("
//					+ "(.+/)?"
//					+ "(watch"
//						+ "(\\?v=|.+&v=)"
//					+ ")?"
//					+ "(v=)?"
//				+ ")"
//				+ "([\\w_-]{11})"
//				+ "(&.+)?"
//				+ "(\\?list=([\\w_-]{13}))?"
//				+ "(\\?t=[0-9]*s)?";
//		
//		if (!element.matches(regex)){
//			System.out.println("[URL invalido] "+element);
//			
//		}
////		TODO ler pattern de ficheiro
//		String pattern = "(be/|v=|/v/|/watch/)([\\w_-]{11})";
//	    Pattern compiledPattern = Pattern.compile(pattern);
//	    Matcher matcher = compiledPattern.matcher(element);
//
//	    if(matcher.find()){
//	    	String id = matcher.group();
//	        return id.substring(id.length()-11, id.length());
//	    }
//	    return "";
//	}

//	private ArrayList<String> validateAndReturnIds(ArrayList<String> links) {
//		ArrayList<String> ids = new ArrayList<String>();
//		for (int i = 0; i < links.size(); i++) {
//			String result = validate(links.get(i));
//			if (result.compareTo("") != 0 && result.length() == 11)
//			{
//				ids.add(result);
//			}
//		}
//		return ids;
//	}

//	public static void doWork(String youtubeUrl)
//	{
//		debug(youtubeUrl);
//		String id = new String();
////		Verifica se o URL e valido
//		if (youtubeUrl.startsWith("https://www.youtube.com/watch?v=") 
//				|| youtubeUrl.startsWith("http://www.youtube.com/watch?v=") 
//				|| youtubeUrl.startsWith("www.youtube.com/watch?v=") 
//				|| youtubeUrl.startsWith("youtube.com/watch?v=")
//				|| youtubeUrl.startsWith("http://youtube.com/watch?v=")
//				|| youtubeUrl.startsWith("https://youtube.com/watch?v=") 
//							
//				|| youtubeUrl.startsWith("https://www.youtube.com/v/")
//				|| youtubeUrl.startsWith("http://www.youtube.com/v/")
//				|| youtubeUrl.startsWith("www.youtube.com/v/") 
//				|| youtubeUrl.startsWith("youtube.com/v/") 
//				|| youtubeUrl.startsWith("http://youtube.com/v/") 
//				|| youtubeUrl.startsWith("https://youtube.com/v/")
//
//				|| youtubeUrl.startsWith("https://youtu.be/") 
//				|| youtubeUrl.startsWith("http://youtu.be/") 
//				|| youtubeUrl.startsWith("youtu.be/"))
//		{
//			
//			
////			Verifica se e uma lista e retira so o id do primeiro
//			debug("Verifica se e lista");
//			if (youtubeUrl.contains("&list="))
//			{
//				System.out.println("Erro: link de uma lista, so vai ser transferido o video atual!");
//				youtubeUrl = new String(youtubeUrl.substring(0, youtubeUrl.indexOf("&list=")));
//			}
//			
//			ArrayList<String> result = new ArrayList<String>();
//			result = downloadYoutubeVideo(youtubeUrl, "");
//			
////			Se o comando não for bem executado
//			if (result.size()-1 > 0 && result.get(result.size()-1).compareTo("1") == 0)
//			{
//				System.out.println("URL invalido!");
//			}
////			Verifica se o download ja foi feito anteriormente
//			else if (result.size()-2 > 0 && result.get(result.size()-2).contains("has already been downloaded"))
//			{
//				System.out.println("["+id+"] " + "Ja foi feito o download!");
//			}
////			Se o comando for bem executado
//			else if (result.size()-1 > 0 && result.get(result.size()-1).compareTo("0") == 0) {
//				System.out.println("["+id+"] " + "Transferencia executada com sucesso.");
//			}
////			TODO Verificar outros erros
//			else {
//				System.out.println("\\TODO Outros erros, quais?");
//			}
//		}
//
////		String format = new String("");
////		getAllFormats(youtubeUrl);
//		System.out.println();
//	}
}
