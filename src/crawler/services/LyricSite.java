package crawler.services;

import edu.dei.gp.jpa.Song;

public interface LyricSite {
	public Song downloadLyric(Song song, boolean debug);
}
