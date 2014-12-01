package crawler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import crawler.services.AZLyrics;
import crawler.services.LyricsMode;

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
		AZLyrics azlyric = new AZLyrics();
		LyricsMode lyricsmode = new LyricsMode();

//		Imprime todos os outputs para ficheiro
//		PrintStream out;
//		try {
//			out = new PrintStream(new FileOutputStream("output.txt"));
//			System.setOut(out);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		List<String> listAuthors = Arrays.asList("Rihanna","Bruno Mars","John Legend","The xx","Christina Perri","Lorde","Passenger","Mike Will Made It ft. Miley Cyrus, Wiz Khalifa & Juicy J","Lil Wayne ft. Bruno Mars","Pharrell Williams","Katy Perry","Paulo Gonzo","Papas da Língua","Sam Smith","Dulce Pontes","The Gift","Magic!","Hardwell ft. Matthew Koma","Five For Fighting","Ricardo Montaner","John Martin","Lana Del Rey","Foxes","Christina Aguilera","Christina Aguilera ft. Blake Shelton","Birdy","Demi Lovato","Demi Lovato","Lady Gaga","Selena Gomez","Jennifer Lopez ft. Pitbul","Katy Perry","The Black Eyed Peas","Yuridia","Elton John","Elton John","Elton John","Red Hot Chili Peppers","Sandy e Junior","Sia","Corona","Skank","Aqua","Justin Bieber","Colbie Caillat","Chromeo","Scorpions","Flogging Molly","Gipsy Kings","Las Ketchup","Linkin Park","James Morrison ft. Nelly Furtado","Kelly Clarkson","Limp Bizkit","Nirvana","Metallica","R.E.M.","No Doubt","The Cranberries","The Police","Sting","Robbie Williams","CCR","Gloria Gaynor","Elton John","Aerosmith","Jason Mraz","Cascada","Rihanna","Alexandra Burke","R. Kelly","Elton John","Eminem","Eminem ft. Sia","The Script","Jason Derulo ft. Snoop Dog","Meghan Trainor","George Ezra","Professor Green & Tori Kelly","Tove Lo","Elisa","Ariana Grande","Sam Smith","Kiesza","Ed Sheeran","Nico & Vinz","Justin Timberlake","The Cure","Pharrell Williams","Beyonce","Whitney Houston","Rick Astley","Taylor Swift","Taylor Swift","MGMT","The Pussycat Dolls","Green Day","Blink-182","Coldplay","Mariah Carey");
		List<String> listMusic = Arrays.asList("Stay","When I was your man","All Of Me","Reunion","A Thousand Years","Royals","Let Her Go","23","Mirror","Happy","Roar","Jardins Proíbidos","Eu sei","Stay With Me","canção do mar","Fácil de Entender","Rude","Dare You","Superman","me va a extrañar","Anywhere For You","Summertime Sadness","Holding Onto Heaven","Your Body","Just A Fool","Skinny Love","Skyscraper","This Is Me","Poker Face","Hit The Lights","On The Floor","E.T.","The Time","Ya Te Olvide","Crocodile Rock","Your Song","Sacrifice","Californication","A lenda","Big Girls Cry","The Rhythm Of The Night","Vou Deixar","Barbie Girl","U Smile","Try","Jealous (I Ain't With It)","Holiday","Worst Day Since Yesterday","Hotel California","Asereje","Castle Of Glass","Broken Strings","Because Of You","My Generation","Smells Like Teen Spirit","Nothing Else Matters","Losing My Religion","Don't Speak","Zombie","Every Breath You Take","Englishman In New York","Feel","Have You Ever Seen The Rain","I Will Survive","Sorry Seems To Be The Hardest Word","I Don't Want To Miss A Thing","I'm Yours","Everytime We Touch","Unfaithful","Hallelujah","I Believe I Can Fly","Can You Feel The Love Tonight","When I'm Gone","Guts Over Fear","Superheroes","Wiggle","All About That Bass","Budapest","Lullaby","Habits","The Waves","Problem","Latch","Hideaway","Sing","Am I Wrong","Not A Bad Thing","Boys Don't Cry","Marilyn Monroe","If I Were A Boy","I Will Always Love You","Never Gonna Give You Up","Love Story","Shake It Off","Kids","I Hate This Part","Wake Me Up When September Ends","I Miss You","The Scientist","All I Want For Christmas");
		
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
		
		int count = 0;
//		String result = lyricsmode.downloadLyric("", "", 1);
//		List<Integer> array = new ArrayList<Integer>();
		System.out.println(musics.size());
		for (int i = 0; i < musics.size(); i++) {
//			String result = azlyric.downloadLyric(listAuthors.get(i), listMusic.get(i), 1);
			String result = lyricsmode.downloadLyric(musics.get(i), 1);
//			array.add(result);
//			count += result;
			if(result!=null) count++;
		}
		
		System.out.println(count+"/"+musics.size());
		
		System.out.println("DEBUG");
//		for (int i = 0; i < array.size(); i++) {
//			azlyric.downloadLyric(listAuthors.get(i), listMusic.get(i), 1);
//			
//		}
	}
}
