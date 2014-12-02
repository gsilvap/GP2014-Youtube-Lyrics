package crawler.services;

public interface LyricSite {
	public String downloadLyric(String nameAuthor, String nameMusic, int debug);
	public String downloadLyric(String music, boolean debug);
}
