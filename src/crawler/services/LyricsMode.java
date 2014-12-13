package crawler.services;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.Utilities;
import edu.dei.gp.jpa.Song;

public class LyricsMode implements LyricSite {

	// Url de pesquisa
	private static String URL = "http://www.lyricsmode.com/search.php?search=";
	// Localizacao dos resultados de pesquisa
	private static String resultsDiv = "a.b.search_highlight";
	// Localizacao da letra da musica
	private static String lyricDiv = "p#lyrics_text.ui-annotatable";
	// Localizacao do nome do artista
	private static String bandDiv = ".header-band-name";
	// Localizacao do titulo da musica
	private static String songDiv = ".header-song-name";

	/**
	 * @param song
	 * Recebe objeto song que contem o titulo recolhido do youtube
	 * @param debug
	 * Recebe true para ver prints de debug
	 */
	public Song downloadLyric(Song song, boolean debug) {
		String urlSearch, urlOfLyric;
		String bandName, songTitle, lyrica;
		double count = 0;
		Elements lyrics;
		Document doc, lyric;
		String[] words;
		String music;

		music = Utilities.cleanString(song.getTitle());

		// Criacao do url de pesquisa
		urlSearch = URL + Utilities.changeStringToSearch(music);

		if (debug)
			System.out.println(urlSearch);

		doc = Utilities.getDoc(urlSearch);

		// Remocao da preposicao "the" que se torna irrelevante para a procura nos resultados
		music = music.toLowerCase().replace("the ", "");

		words = music.split(" ");

		if (doc != null) {
			lyrics = doc.select(resultsDiv);

			// Faz a selecao do possibel resultado para a pesquisa
			for (Element element : lyrics) {
				count = 0;
				urlOfLyric = element.select("a").attr("href");
				urlOfLyric = "http://www.lyricsmode.com" + urlOfLyric;
				
				if (debug)
					System.out.println(urlOfLyric);

				// Conta o nr de palavras em comum entre o titulo e o url da pesquisa
				for (String str : words)
					if (urlOfLyric.contains(str))
						count++;

				// Verifica se existem pelo menos 70% das palavras em comum
				if ((count / words.length) > 0.70) {

					lyric = Utilities.getDoc(urlOfLyric);
					Utilities.sleep();

					// Seleciona o nome do artista
					bandName = lyric.select(bandDiv).text();

					// Seleciona o titulo da musica
					songTitle = lyric.select(songDiv).text().replace(" lyrics", "");

					// Seleciona a letra da musica
					lyrica = (Utilities.br2nl(lyric.select(lyricDiv).toString())).replace("\n ", "\n");

					if (debug) {
						System.out.println("Banda: " + bandName);
						System.out.println("Titulo: " + songTitle);
						System.out.println(lyrica);
						System.out.println("OK");
					}

					// Guarda a informacao recolhida no objeto song
					song.setTitle(songTitle);
					song.setArtistName(bandName);
					song.setLyric(lyrica);

					return song;
				} else if (debug)
					System.out.println("Values:" + count + "+" + words.length + "+" + count / words.length);
			}
		}

		// Caso nao encontre a letra da musica retorna null
		if (debug)
			System.out.println("ERROR");
		return null;
	}
}
