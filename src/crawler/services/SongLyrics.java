package crawler.services;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.Utilities;
import edu.dei.gp.jpa.Song;

public class SongLyrics implements LyricSite {

	// Url de pesquisa
	private static String URL = "http://www.songlyrics.com/index.php?section=search&searchW=";
	// Localizacao dos resultados de pesquisa
	private static String resultsDiv = "div.serpresult";
	// Localizacao da letra da musica
	private static String lyricDiv = "p#songLyricsDiv.songLyricsV14.iComment-text";
	// Localização do nome do artista e titulo da musica
	private static String infoDiv = "div.pagetitle";

	/**
	 * @param song 
	 * Recebe objeto song que contem o titulo recolhido do youtube
	 * @param debug
	 * Recebe true para ver prints de teste 
	 */
	public Song downloadLyric(Song song, boolean debug) {
		String urlSearch, urlOfLyric;
		String bandName, songTitle, lyrica;
		double count = 0;
		Elements lyrics;
		Document doc, lyric;
		String[] words;
		String music;

		music = cleanString(song.getTitle());

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

				if (debug)
					System.out.println(urlOfLyric);

				// Conta o nr de palavras em comum entre o titulo e o url da pesquisa
				for (String str : words)
					if (urlOfLyric.replace("-lyrics", "").contains(str))
						count++;

				// Verifica se existem pelo menos 70% das palavras em comum
				if ((count / words.length) > 0.70) {

					lyric = Utilities.getDoc(urlOfLyric);
					Utilities.sleep();

					bandName = lyric.select(infoDiv).select("h1").text();
					
					// Seleciona o titulo da musica
					songTitle = (bandName.split(" - ")[1]).replace(" Lyrics", "");
					
					// Seleciona o nome do artista
					bandName = bandName.split(" - ")[0];

					// Seleciona a letra da musica
					lyrica = (Utilities.br2nl(lyric.select(lyricDiv).toString())).replace("\n ", "\n");

					if (debug) {
						System.out.println("Banda: " + bandName);
						System.out.println("Titulo: " + songTitle);
						System.out.println(lyrica);
						System.out.println("OK");
					}

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

	/**
	 * @param music
	 *            Recebe o titulo da musica e efetua o clean ao mesmo limpando
	 *            informacao e carateres irrelevantes
	 * 
	 * */
	public static String cleanString(String music) {

		// lista de possiveis sequencias que tem de ser removidas
		HashMap<String, String> regexReplaceList = new HashMap<String, String>();

		regexReplaceList.put("[\\]\\[(){},.;!?<>%+~<>*\"_-]", " ");
		regexReplaceList.put("ft\\.", "feat");

		regexReplaceList.put("with lyric[s]*", "");
		regexReplaceList.put("lyric[s]*", "");
		regexReplaceList.put("instrumental", "");
		regexReplaceList.put("karaoke", "");
		regexReplaceList.put("version", "");
		regexReplaceList.put("w\\/", "");
		regexReplaceList.put("official", "");
		regexReplaceList.put("video", "");
		regexReplaceList.put("download", "");
		regexReplaceList.put("youtube", "");
		regexReplaceList.put("in the style of", "");
		regexReplaceList.put("hd", " ");
		regexReplaceList.put("hq", " ");
		regexReplaceList.put("on screen", "");

		regexReplaceList.put("[ ]+", " ");

		// Remocao da acentuacao
		music = Utilities.unAccent(music).toLowerCase();

		// Efetua a limpeza das sequencias irrelevantes segundo o regexReplaceList
		for (Map.Entry<String, String> element : regexReplaceList.entrySet())
			music = music.replaceAll(element.getKey(), element.getValue());

		//Limpa a existencia de espacos no inicio ou no fim do titulo a usar na pesquisa
		music = music.trim();
		
		return music;
	}
}