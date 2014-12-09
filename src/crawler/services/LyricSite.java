package crawler.services;

import edu.dei.gp.jpa.Song;

public interface LyricSite {
	// FIXME: REMOVER
	public String downloadLyric(String music, boolean debug);
	
	public Song downloadLyric(Song song, boolean debug);
}
