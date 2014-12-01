package crawler;

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
		
		List<String> listAuthors = Arrays.asList("Rihanna","Bruno Mars","John Legend","The xx","Christina Perri","Lorde","Passenger","Mike Will Made It ft. Miley Cyrus, Wiz Khalifa & Juicy J","Lil Wayne ft. Bruno Mars","Pharrel Williams","Katy Perry","Paulo Gonzo","Papas da L�ngua","Sam Smith","Dulce Pontes","The Gift","Magic!","Hardwell ft. Matthew Koma","Five For Fighting","Ricardo Montaner","John Martin","Lana Del Rey","Foxes","Christina Aguilera","Christina Aguilera ft. Blake Shelton","Birdy","Demi Lovato","Demi Lovato","Lady Gaga","Selena Gomez","Jennifer Lopez ft. Pitbul","Katy Perry","The Black Eyed Peas","Yuridia","Elton John","Elton John","Elton John","Red Hot Chili Peppers","Sandy e Junior","Sia","Corona","Skank","Aqua","Justin Bieber","Colbie Caillat","Chromeo","Scorpions","Flogging Molly","Gipsy Kings","Las Ketchup","Linkin Park","James Morrison ft. Nelly Furtado","Kelly Clarkson","Limp Bizkit","Nirvana","Metallica","R.E.M.","No Doubt","The Cranberries","The Police","Sting","Robbie Williams","CCR","Gloria Gaynor","Elton John","Aerosmith","Jason Mraz","Cascada","Rihanna","Alexandra Burke","R. Kelly","Elton John","Eminem","Eminem ft. Sia","The Script","Jason Derulo ft. Snoop Dog","Meghan Trainor","George Ezra","Professor Green & Tori Kelly","Tove Lo","Elisa","Ariana Grande","Sam Smith","Kiesza","Ed Sheeran","Nico & Vinz","Justin Timberlake","The Cure","Pharrel Williams","Beyonce","Whitney Houston","Rick Astley","Taylor Swift","Taylor Swift","MGMT","The Pussycat Dolls","Green Day","Blink-182","Coldplay","Mariah Carey");
		List<String> listMusic = Arrays.asList("Stay","When I was your man","All Of Me","Reunion","A Thousand Years","Royals","Let Her Go","23","Mirror","Happy","Roar","Jardins Proib�dos","Eu sei","Stay With Me","Can��o do Mar","F�cil de Entender","Rude","Dare You","Superman","Me Va A Extra�ar","Anywhere For You","Summertime Sadness","Holding Onto Heaven","Your Body","Just A Fool","Skinny Love","Skyscraper","This Is Me","Poker Face","Hit The Lights","On The Floor","E.T.","The Time","Ya Te Olvide","Crocodile Rock","Your Song","Sacrifice","Californication","A lenda","Big Girls Cry","The Rhythm Of The Night","Vou Deixar","Barbie Girl","U Smile","Try","Jealous (I Ain't With It)","Holiday","Worst Day Since Yesterday","Hotel California","Asereje","Castle Of Glass","Broken Strings","Because Of You","My Generation","Smells Like Teen Spirit","Nothing Else Matters","Losing My Religion","Don't Speak","Zombie","Every Breath You Take","Englishman In New York","Feel","Have You Ever Seen The Rain","I Will Survive","Sorry Seems To Be The Hardest Word","I Don't Want To Miss A Thing","I'm Yours","Everytime We Touch","Unfaithful","Hallelujah","I Believe I Can Fly","Can You Feel The Love Tonight","When I'm Gone","Guts Over Fear","Superheroes","Wiggle","All About That Bass","Budapest","Lullaby","Habits","The Waves","Problem","Latch","Hideaway","Sing","Am I Wrong","Not A Bad Thing","Boys Don't Cry","Marilyn Monroe","If I Were A Boy","I Will Always Love You","Never Gonna Give You Up","Love Story","Shake It Off","Kids","I Hate This Part","Wake Me Up When September Ends","I Miss You","The Scientist","All I Want For Christmas Is You");
		
		int count = 0;
//		List<Integer> array = new ArrayList<Integer>();
		for (int i = 0; i < listAuthors.size(); i++) {
//			String result = azlyric.downloadLyric(listAuthors.get(i), listMusic.get(i), 1);
			String result = lyricsmode.downloadLyric(listAuthors.get(i), listMusic.get(i), 1);
//			array.add(result);
//			count += result;
			if(result!=null) count++;
		}
		
		System.out.println(count);
		
		System.out.println("DEBUG");
//		for (int i = 0; i < array.size(); i++) {
//			azlyric.downloadLyric(listAuthors.get(i), listMusic.get(i), 1);
//			
//		}
	}
}
