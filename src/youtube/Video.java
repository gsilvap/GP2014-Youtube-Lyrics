package youtube;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Video {
	
	public static boolean DEBUG = true;
	
	public static enum State {
		VALID, INVALID;
	}
	
	private String url;
	private String title;
	private String id;
	private String filename;
	private State state;
	
	public Video(String url) {
		super();
		this.url = url;
		this.state = State.VALID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public boolean isValid() {
		if (this.state == State.VALID)
			return true;
		return false;
	}


	public void validate(String regex, String pattern) {
		if (regex.compareTo("") == 0)
		{
			regex = "(https?://)?"
					+ "(www\\.)?"
					+ "(youtu\\.be/|youtube\\.com/)?"
					+ "("
					+ "(.+/)?"
					+ "(watch"
					+ "(\\?v=|.+&v=)"
					+ ")?"
					+ "(v=)?"
					+ ")"
					+ "([\\w_-]{11})"
					+ "(&.+)?"
					+ "(\\?list=([\\w_-]{13}))?"
					+ "(\\?t=[0-9]*s)?";
		}
		if (pattern.compareTo("") == 0)
		{
			pattern = "(be/|v=|/v/|/watch/)([\\w_-]{11})";
		}
		
		if (!url.matches(regex)){
			System.out.println("[URL invalido] "+url);
			
		}
	    Pattern compiledPattern = Pattern.compile(pattern);
	    Matcher matcher = compiledPattern.matcher(url);

	    if(matcher.find()){
	    	id = matcher.group();
//	    			.substring(id.length()-11, id.length());
	    	id = id.substring(id.length()-11, id.length());
	    	
	    	state = State.VALID;
	    }
	    else
	    	state = State.INVALID;
	}
	
	public ArrayList<String> downloadVideo() {
		return Utilities.runCmd("youtube-dl " + url);
	}

	public void downloadVideoByFormat(String format) {
		ArrayList<String> result = Utilities.runCmd("youtube-dl " + "-f " + format + " " + url);
		
//		Verifica se o download ja foi feito anteriormente
		if (result.size()-2 > 0 && result.get(result.size()-2).contains("has already been downloaded"))
		{
			System.out.println("["+id+"] " + "Ja foi feito o download!");
		}
//		Se o comando for bem executado
		else if (result.size()-1 > 0 && result.get(result.size()-1).compareTo("0") == 0) {
			System.out.println("["+id+"] " + "Transferencia executada com sucesso.");
		}
//		TODO Verificar outros erros
		else {
			System.out.println("\\TODO Outros erros, quais?");
		}
	}

	public void downloadTitle() {
		ArrayList<String> result = Utilities.runCmd("youtube-dl --get-filename -o \"%(title)s\" " + id);		
		if (result.size()-1 >= 0 && result.get(result.size()-1).compareTo("0") == 0)
		{
			title = result.get(0);
			state = State.VALID;
			return;
		}
		state = State.INVALID;
	}
	public void downloadFilename() {
		ArrayList<String> result = Utilities.runCmd("youtube-dl --get-filename -o \"%(title)s-%(id)s.%(ext)s\" " + id);		
		if (result.size()-1 >= 0 && result.get(result.size()-1).compareTo("0") == 0)
		{
			filename = result.get(0);
			state = State.VALID;
			return;
		}
		state = State.INVALID;
	}
}
