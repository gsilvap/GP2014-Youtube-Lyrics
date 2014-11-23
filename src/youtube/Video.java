package youtube;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class para efetuar o download dos videos 
 * @author Celso Rafael Clara Mendes 2009109378
 * @author Goncalo Silva Pereira 2009111643
 */
public class Video {

	public static boolean DEBUG = false;

	public static enum State {
		VALID, INVALID;
	}

	private String url;
	private String title;
	private String id;
	private String url_simple;
	private String filename;
	private State state;

	/**
	 * 
	 * @param url
	 */
	public Video(String url) {
		super();
		this.url = url;
		this.state = State.VALID;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * verifica se o video e valido
	 * @return
	 */
	public boolean isValid() {
		if (this.state == State.VALID)
			return true;
		return false;
	}

	/**
	 * Validacao do link e extracao do id
	 * @param regex validacao do link
	 * @param pattern validacao do id
	 */
	public void validate(String regex, String pattern) {
		if (regex.compareTo("") == 0) {
			regex = "(https?://)?" + "(www\\.)?" + "(youtu\\.be/|youtube\\.com/)?" + "(" + "(.+/)?" + "(watch" + "(\\?v=|.+&v=)" + ")?" + "(v=)?" + ")" + "([\\w_-]{11})" + "(&.+)?" + "(\\?list=([\\w_-]{13}))?" + "(\\?t=[0-9]*s)?" + "(\\\\?.+)?";
		}
		if (pattern.compareTo("") == 0) {
			pattern = "(be/|v=|/v/|/embed/|/watch/)([\\w_-]{11})";
		}

		if (!url.matches(regex)) {
			System.out.println("[URL REGEX FAIL] " + url);
		}
		
		Pattern compiledPattern = Pattern.compile(pattern);
		Matcher matcher = compiledPattern.matcher(url);

		if (matcher.find()) {
			id = matcher.group();
			//	    			.substring(id.length()-11, id.length());
			id = id.substring(id.length() - 11, id.length());
			url_simple = "https://www.youtube.com/watch?v=" + id;
			state = State.VALID;
		} else
			state = State.INVALID;
	}

	/**
	 * Transferencia do video
	 * @return
	 */
	public ArrayList<String> downloadVideo() {
		return Utilities.runCmd("youtube-dl  --write-thumbnail " + url_simple, id);
	}

	/**
	 * Transferencia do video num dado formato
	 * @param format formato de saida
	 */
	public void downloadVideoByFormat(String format) {
		ArrayList<String> result = Utilities.runCmd("youtube-dl " + "-f " + format + " " + id, id);

		//		Verifica se o download ja foi feito anteriormente
		if (result.size() - 2 > 0 && result.get(result.size() - 2).contains("has already been downloaded")) {
			System.out.println("[" + id + "] " + "Ja foi feito o download!");
		}
		//		Se o comando for bem executado
		else if (result.size() - 1 > 0 && result.get(result.size() - 1).compareTo("0") == 0) {
			System.out.println("[" + id + "] " + "Transferencia executada com sucesso.");
		}
		//		TODO Verificar outros erros
		else {
			System.out.println("\\TODO Outros erros, quais?");
			result.forEach(element -> System.err.println(element));
		}
	}

	/**
	 * Transferir titulo do video
	 */
	public void downloadTitle() {
		ArrayList<String> result = Utilities.runCmd("youtube-dl --get-filename -o \"%(title)s\" " + url_simple, id);
		if (result.size() - 1 >= 0 && result.get(result.size() - 1).compareTo("0") == 0) {
			title = result.get(0);
			state = State.VALID;
			return;
		}
		state = State.INVALID;
	}

	/**
	 * Atribuir nome do ficheiro
	 */
	public void downloadFilename() {
		ArrayList<String> result = Utilities.runCmd("youtube-dl --get-filename -o \"%(title)s-%(id)s.%(ext)s\" " + url_simple, id);
		
//		result.forEach(System.out::println);
//		System.out.println(result);
		
		if (result.size() - 1 >= 0 && result.get(result.size() - 1).compareTo("0") == 0) {
			filename = result.get(0);
			state = State.VALID;
			return;
		}
		state = State.INVALID;
	}
}
