package beadando;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@code T0D04PP}: a fo konzolos vezerloosztalyt implementalo kod.<br>
 * @author Katona adam <br>
 * @version 1.0 <br>
 * 
 * Hasznalata:<br><br>
 * 
 * Ez a forma az elfogadott: 'EEEE.HH.NN. OO.PP. FELADAT HELY SZEMELY'<br>
 * A tobbszavas kifejezeseket es neveket '_' jellel kosse ossze! Kis/nagybetu nem szamit!<br>
 * Hely es szemely megadasa nem kotelezo ezt egy '.' -al jelolje!<br>
 * Enterrel uj sor beviheto, a CTRL+Z kombinacioval 'EOF'-ot szimulalhatunk, <br>
 * ami a program sikeres leallasat eredmenyezi!<br>
 * 
 */
public class T0D04PPConsole{
	/*	Pelda bemenet
	 * 
2014.05.11 11.42 Tanulas . Gergo
1990.08.23 00.30 Szuletes Korhaz .
2014.05.11 11.42 Tanulas . .
1990.08.23 00.30 Szuletes Korhaz Anya
2014.05.11 11.42 Tanulas Gergoek Gergo
1990.08.23 00.30 Szuletes . .
2014.05.11 11.42 Tanulas Gergoek_haza Gergo_es_a_mokusok
1990.08.23 00.30 Szuletes . . 

	 */
	
	/**
	 * Loggolashoz kell.
	 */
	private static Logger logger = LoggerFactory.getLogger(T0D04PPConsole.class);
	
	/**
	 * Az {@code adatok} adattag a konzolrol beolvasott ertekeket tarolja. <br>
	 * {@code Tarolasi_struktura}: eeee.HH.NN. oo.PP. FELADAT HELY SZEMeLY
	 * */
	public static ArrayList<String[]> adatok = new ArrayList<String[]>();
	/**
	 * A {@code datum} int tomb tipusu adattagba kerul a szokozok szerint szetvagott bemenet elso resze.
	 */
	public static int[] datum = new int[3];
	/**
	 * Az {@code ido} int tomb tipusu adattagba kerul a szokozok szerint szetvagott bemenet masodik resze.
	 */
	public static int[] ido = new int[2];
	/**
	 * A letarolando esemeny neve kerul letarolasra a {@code feladat} adattagban.
	 */
	public static String feladat = "";
	/**
	 * A letarolando esemeny helyszine kerul letarolasra a {@code hely} adattagban.
	 */
	public static String hely = "";
	/**
	 * A letarolando esemennyel kapcsolatos szemely(ek) neve kerul letarolasra a {@code szemely} adattagban.
	 */
	public static String szemely = "";

	/**
	 * Az {@link Init()} metodus valositja meg a konzolrol kapott informaciok feldolgozasat. <br>
	 * @throws NumberFormatException kivetel keletkezhet es keletkezik is, amennyiben nem a <br>
	 * tarolasi struktura szerint kerul megadasra a letarolando informacio. <br>
	 * @throws ArrayIndexOutOfBoundsException kivetel kerul elfogasra, mivel az en megvalositasomban tombokbe <br>
	 * kerulnek beolvasasra az adatok amik pontos hasznalatot es elerest kovetelnek meg, ezert nem ajanlott elterni <br>
	 * a {@code Tarolasi strukturatol}, bar amennyire lehet hibaturore keszitettem, peldaul le van kezelve, <br>
	 * ha konzolon ures sort adunk meg. <br>
	 * @throws FileNotFoundException amennyiben az irasra megadott fajl nem letezik/nem elerheto <br>
	 * bar olvasaskor hasznosabb lenne ez a kivetel, az IDE kenyszeritesere ehhez is belekerult. <br>
	 * @throws IOException kivetel valtodik ki, amennyiben egy I/O hiba merul fel.
	 */
	public static void Init() throws NumberFormatException, IOException, ArrayIndexOutOfBoundsException, FileNotFoundException{
		//Az eroforras megszerzese konzolrol
			Scanner sc = new Scanner(System.in);
			while(sc.hasNextLine() ){
				
				String line = sc.nextLine();
				if(line.equals("")){continue;}
				ujSor(line);
				logger.info("Uj sor bekerese.");
			}
			sc.close();

		//Az adatok ertelmezese utan kiiras konzolra es kiiras fajlba
		//ezzel elkeszul az allomany, amivel mukodes kozben lehet dolgozni
		//mentes("src/main/resources/Dict.txt");
		mentes("target/classes/Dict.txt");
		}	
		/**
		 * 
		 * @param args parancssori argumentum. <br>
		 * @throws IOException kivetel valtodik ki, amennyiben egy I/O hiba merul fel. <br>
		 * Itt csupan a program inditasakor kinalja a program a felhasznalonak a hasznalati <br>
		 * utasitasokat amennyiben konzolrol futtatja.
		 */
		public static void main(String[] args) throws IOException {
			
			System.out.println("Kerem add meg a bemenetet!");
			System.out.println("Ez a forma az elfogadott: 'EEEE.HH.NN. OO.PP FELADAT HELY SZEMELY'");
			System.out.println("A tobbszavas kifejezeseket '_' jellel kosse ossze! Kis/nagybetu nem szamit!");
			System.out.println("Hely es szemely megadasa nem kotelezo ezt egy '.' -al jelolje!");
			System.out.println("Enterrel uj sor beviheto, a CTRL+Z kombinacioval 'EOF'-ot szimulalhatunk, ");
			System.out.println("ami a program sikeres leallasat eredmenyezi!");
			
			Init();
			logger.info("Init meghivasa.");
		}

		/**
		 * 
		 * @param line String tipusu parameterrel kapja meg az ujSor metodus az uj sorat a konzol bemenetnek.
		 */
		public static void ujSor(String line){
			String[] tagok = line.split(" ");
			
			String[] Datum = tagok[0].split("[*.*]");
			String[] Ido = tagok[1].split("[*.*]");
			if(tagok[2].isEmpty()||tagok[2].equalsIgnoreCase(".")){ 
				System.out.println("Hibas bemenet, feladat megadasa kotelezo");
				System.exit(0);}
			else{
			datum = new int[]{Integer.parseInt(Datum[0]), Integer.parseInt(Datum[1]), Integer.parseInt(Datum[2])};
			ido = new int[]{Integer.parseInt(Ido[0]), Integer.parseInt(Ido[1])};
			feladat = new String(tagok[2]);
			hely = new String(tagok[3]== "." ? tagok[3]= null : tagok[3]);
			szemely = new String(tagok[4]== "." ? tagok[3]=null : tagok[4]);
			
			adatok.add(tagok);
			logger.info("Adatok feltoltese.");
			}
		}
		
		/**
		 * 
		 * @param fajlNev a fajl eleresi utvonalat es nevet kapja.
		 */
		public static void mentes(String fajlNev){
			try {		
				PrintWriter console = new PrintWriter(new BufferedWriter(new FileWriter(fajlNev, true)));
				logger.info("Mentest kiszolgalo objektum peldanyositasa.");
				for(String[] a : adatok){
					for(int i = 0; i < a.length; i++){
						if(i==0){
							String[] temp = a[i].split("[*.*]");
							int ev = Integer.parseInt(temp[0]);
							int honap = Integer.parseInt(temp[1]);
							int nap = Integer.parseInt(temp[2]);
							
							System.out.print(ev + "." + honap + "." + nap);
							console.print(ev + "." + honap + "." + nap);
						}
						else if(i==1){
							String[] temp = a[i].split("[*.*]");
							int ora2 = Integer.parseInt(temp[0]);
							int perc2 = Integer.parseInt(temp[1]);
							String ora1= ora2<10 ? "0"+ora2 : ora2+"" ;
				            String perc1 = perc2<10 ? "0"+perc2 : perc2+"" ;
				            
							System.out.print( " " + ora1  + ":" + perc1);
							console.print(" " + ora1  + ":" + perc1);
						}
						else{
							System.out.print(" [" + a[i] + "]");
							console.print(" [" + a[i] + "]");
						}
					
					
					}
					System.out.println();
					console.println();
					logger.info("Forrasba iras.");
				}
				console.close();
				logger.info("Forrasba iro objektum lezarasa.");
			} catch (Exception e){		}
	}
}