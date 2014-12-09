package crawler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import crawler.services.LyricsMania;
import crawler.services.LyricsMode;
import crawler.services.SongLyrics;
import edu.dei.gp.jpa.Song;

public class Crawler {
	/**
	 * Funcao que executa o programa principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Crawler project = new Crawler();
		project.run();
	}

	private void run() {		
		List<String> musics = Arrays.asList("James Arthur - Impossible (Lyrics)", "Bruno Mars -When I was your man (lyrics)", "Jason Mraz - I won't give up [lyrics]", "Rude Magic Lyrics", 
				"A Thousand Years - Christina Perri Lyrics", "Boom Boom Pow - Black Eyed Peas (Lyrics)", "Oasis-Wonderwall lyrics", "Apologize - One republic (lyrics on screen)", 
				"Green Day - Wake Me Up When September Ends (with lyrics on screen)", "The Fray - You Found Me (with lyrics) + HQ", "Fall For You Lyrics [ Secondhand Serenade]", 
				"Invisible ~ Hunter Hayes ~ Lyrics", "OneRepublic-Counting Stars HD Lyrics Native!", "Taylor Swift - Blank Space AUDIO WORKING! (Lyrics + Download)", 
				"Taylor Swift - Blank Space (Karaoke Version)", "Jessie J - Bang Bang ft. Nicki Minaj and Ariana Grande - Lyrics", "Beyonce Love On Top Lyrics HQ YouTube", 
				"Rihanna - Stay feat. Mikky Ekko ~OFFICIAL LYRICS VIDEO~", "Thinking Out Loud by Ed Sheeran (LYRICS) Album Version", "Beyonce-Irreplaceable+lyrics", 
				"Beyonce - Countdown (Lyrics Video) HD", "Nicki Minaj - Anaconda (Official - Lyrics Video)", "Emeli Sandé - Read All About It (pt III) [Lyrics On Screen]", 
				"Hurt - Christina Aguilera + lyrics", "Nicki Minaj-Super Bass(lyrics) 2011(NEW)", "John Legend - All Of Me (Karaoke)", "Christina Perri - A Thousand Years (Karaoke Version)", 
				"Dulce Pontes - Canção do Mar (Karaokê HQ)", "Eu Sei - Papas da Língua (KARAOKE COMPLETO)", "Rihanna - Stay [Karaoke/MCV]", "Bruno Mars - When I Was Your Man (Karaoke Ballad)", 
				"Titanium - David Guetta ft. Sia (Karaoke/Instrumental)", "Hallelujah - Karaoke HD (In the style of Alexandra Burke)", "Keane - Everybody's Changing Karaoke.mp4", 
				"Idina Menzel - Let It Go (from 'Frozen') (Karaoke Version)", "Ariana Grande Santa Tell Me Karaoke Lyrics", "Karaoke Rafiki Video Games - Lana Del Rey", 
				"Clean Bandit and Jess Glynne - Rather Be (Karaoke Version)", "Hey Jude in the Style of \"The Beatles\" karaoke video with lyrics (no lead vocal)", "Beyonce Listen karaoke.wmv", 
				"Katy Perry - Roar (Karaoke Version) HD", "Angie - The Rolling Stones Karaoke", "\"Robbie Williams\" (She's The One) - Karaoke", "Karaoke My Immortal - Evanescence *", 
				"KARAOKE Decisiones Ruben Blades KARAOKE", "Echosmith - Cool Kids (Karaoke Version)", "Miley Cyrus - Wrecking Ball [ Karaoke / Instrumental ]", 
				"Les Miserables - I Dreamed a Dream (Karaoke Instrumental) w/ Lyrics", "Tom Odell - Another Love (Karaoke Version)", "Karaoke - oasis stop crying your heart out", 
				"Lana Del Rey - Born to Die Lyrics Video", "Lana Del Rey Blue Jeans lyrics", "Lana Del Rey - Young & Beautiful [Lyric Video]", "Demons - Imagine Dragons", 
				"Radioactive-Imagine Dragons (Lyrics)", "Hall Of Fame - The Script feat. will.i.am (Lyrics)", "Pink -Try (Lyrics)", "A Thousand Years - Christina Perri Lyrics", 
				"Bruno Mars When i was your man lyrics", "Justin Timberlake - Mirrors (Lyrics)", "Justin Bieber - Nothing Like Us {Lyrics}", "Kelly Clarkson - Because of You (lyrics)", 
				"Hurt - Christina Aguilera + lyrics", "Beyoncé - Halo [with lyrics]", "Rihanna - Te Amo Lyrics", "Rihanna - Rehab [ Lyrics ]", "Beyoncé - Sweet Dreams Lyrics", 
				"Beyonce - Single ladies - lyric video", "Telephone Lady GaGa lyrics", "Jessie J - Domino (Lyrics)", "Train - Drive By Lyrics", "Owl City - Fireflies (lyrics)", 
				"Green Day - Boulevard of Broken Dreams Lyrics", "Limp Bizkit - Behind Blue Eyes lyrics", "Metallica - Nothing else matter lyrics", "Avril Lavigne-Hot (Karaoke)", 
				"Innocence - Avril Lavigne karaoke", "Karaoke Take A Bow-Rihanna", "Fergie - Big Girls Don't Cry - Karaoke", "Goodbye my lover- james blunt (karaoke)", "Hero Karaoke - Enrique Iglesias", 
				"Snow Patrol - Chasing Cars Karaoke", "Kings Of Leon - Use somebody (Karaoke)", "Mad World - (Karaoke Instrumental) - Gary Jules", "Karaoke Every Breath You Take - The Police *", 
				"Karaoke Zombie - The Cranberries *", "\"4 Non Blondes\" (What's Up) - Karaoke", "AC/DC - Highway to Hell (Karaoke)", "Bon Jovi -Livin' On A Prayer- Karaoke", 
				"You give love a bad name - Bon Jovi (Karaoke)", "Bryan Adams Heaven karaoke", "Scorpions - Still loving You Karaoke", "Europe - Carrie - Karaoke", 
				"Karaoke - The Cure - Boys don't cry", "The BloodHound Gang - The Bad Touch Lyrics", "The Offspring - Pretty Fly for a White Guy Lyrics", 
				"Foxtrot Uniform Charlie Kilo - Bloodhound gang lyrics", "Blink-182-I Miss You (Karaoke)", "Coldplay - The Scientist Karaoke", "Linkin Park - Castle of Glass karaoke");
		
//		LyricsMode lyricsmode = new LyricsMode();
//		SongLyrics songlyrics = new SongLyrics();
		LyricsMania lyricsMania = new LyricsMania();
		int count = 0;
		Song result;
		Song song = new Song();
		
//		Imprime todos os outputs para ficheiro
		PrintStream out;
		try {
			out = new PrintStream(new FileOutputStream("output.txt"));
			System.setOut(out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(musics.size());
		for (int i = 0; i < musics.size(); i++) {
			song.setTitle(musics.get(i));
//			result = lyricsmode.downloadLyric(song, true);
//			result = songlyrics.downloadLyric(song, true);
			result = lyricsMania.downloadLyric(song, true);
			if(result!=null) count++;
		}
		
		System.out.println(count+"/"+musics.size());
		System.out.println("DEBUG");
	}
}
