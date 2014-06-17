package beadando;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@code T0D04PP}: a fo Swing-es vezerloosztalyt implementalo kod.
 * @author Katona Adam <br>
 * @version 1.0 <br>
 * 
 */
public class T0D04PP {

	/**
	 * Loggolashoz kell.
	 */
	private static Logger logger = LoggerFactory.getLogger(T0D04PP.class);
	
	
	/**
	 * A {@code datum} int tomb tipusu adattagba kerul a szokozok szerint szetvagott bemenet elso resze.
	 */
	public int[] datum = new int[3];
	/**
	 * Az {@code ido} int tomb tipusu adattagba kerulnek a szokozok szerint szetvagott bemenet elso resze.
	 */
	public int[] ido = new int[2];
	/**
	 * A letarolando esemeny neve kerul letarolasra a {@code feladat} adattagba.
	 */
	public String feladat = "";
	/**
	 * A letarolando esemeny helyszine kerul letarolasra a {@code hely} adattagba.
	 */
	public String hely = "";
	/**
	 * A letarolando esemennyel kapcsolatos szemely(ek) neve kerul letarolasra a {@code szemely} adattagban.
	 */
	public String szemely = "";
        
    /**
     * Alap konstruktor.
     */
    public T0D04PP() { }

    /**
     * Konstruktor egy {@code T0D04PP} objektum letrehozasara. <br>
     * @param datum int tomb tartalmazza a 0., 1. es 2. indexen az ev, honap, nap informaciokat. <br>
     * @param ido int tomb tartalmazza a 0. es 1. indexen az orat es a percet. <br>
     * @param feladat String valtozo tartalmazza a tarolando esemeny szoveget. <br>
     * @param hely String valtozo tartalmazza a tarolando esemeny helyszinet. <br>
     * @param szemely String valtozo tartalmazza a tarolando esemennyel kapcsolatos szemelyek neveit.
     */
    public T0D04PP(int[] datum, int[] ido, String feladat, String hely, String szemely) {
        this.datum=datum;
        this.feladat=feladat;
        this.ido=ido;
        this.hely=hely;
        this.szemely=szemely;
   }
    /**
     * 
     * @return Az int {@code datum} tomb ertekevel ter vissza.
     */
    public int[] getDatum() {
        return datum;
    }
    /**
     * 
     * @param datum tomb erteket allitja be. 
     */
    public void setDatum(int[] datum) {
        this.datum = datum;
    }
    /**
     * 
     * @return Az int {@code ido} tomb ertekevel ter vissza.
     */
    public int[] getIdo() {
        return ido;
    }
    /**
     * 
     * @param ido tomb erteket allitja be. 
     */
    public void setIdo(int[] ido) {
        this.ido = ido;
    }
    /**
     * 
     * @return A String {@code feladat} valtozo tartalmaval ter vissza.
     */
    public String getFeladat() {
        return feladat;
    }
    /**
     * 
     * @param feladat String valtozo erteket allitja be.
     */
    public void setFeladat(String feladat) {
        this.feladat = feladat;
    }
    /**
     * 
     * @return A String {@code hely} valtozo tartalmaval ter vissza.
     */
    public String getHely() {
        return hely;
    }
    /**
     * 
     * @param hely String valtozo erteket allitja be.
     */
    public void setHely(String hely) {
        this.hely = hely;
    }
    /**
     * 
     * @return A String {@code szemely} valtozo tartalmaval ter vissza.
     */
    public String getSzemely() {
        return szemely;
    }
    /**
     * 
     * @param szemely String valtozo erteket allitja be.
     */
    public void setSzemely(String szemely) {
        this.szemely = szemely;
    }
  	/**
	 * Az {@link Init()} metodus valositja meg Swing ablakbol kapott informaciok feldolgozasat. <br>
     * @throws IOException kivetel valtodik ki, amennyiben egy I/O hiba merul fel. <br>
	 * bar olvasaskor hasznosabb lenne ez a kivetel, az IDE kenyszeritesere ehhez is belekerult.
	 */
	public void Init() throws IOException{
            String ora1= ido[0]<10 ? "0"+ido[0] : ido[0]+"" ;
            logger.info("Az ido[] megkapta az orat a swing osztalybol, atalakitasra kerul");
            
            String perc1 = ido[1]<10 ? "0"+ido[1] : ido[1]+"" ;
            logger.info("Az ido[] megkapta a percet a swing osztalybol, atalakitasra kerul");
            
            hely = hely.isEmpty() ? "nincs megadva helyszin" : hely;
            logger.info("A hely megkapta az adatot a swing osztalybol");
            
            szemely = szemely.isEmpty() ? "nincs megadva szemely" : szemely;
            logger.info("A szemely megkapta az adatot a swing osztalybol");
            
            String temp = datum[0] + "." + datum[1] + "." +datum[2] + " " + ora1  + ":" + perc1 + " [" + feladat + "] [" + hely + "] [" + szemely + "]";
            logger.info("Az adatok beformazasa a kivant formara, faljba/konzolra irasra keszen all.");
            System.out.println(temp);
        try {
        	URL url = getClass().getClassLoader().getResource("Dict.txt");
        	String utvonal=url.toExternalForm();
        	String utvonal2 = utvonal.replace("file:", "");
        	logger.info("Utvonal meghatarozva.");
        	Writer swing = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(utvonal2, true), "UTF8"));
            logger.info("Forras fajl beolvaso peldanyositva.");
            
            swing.append(temp);            
            logger.info("Forrasba iras.");
            swing.flush();
            swing.close();
            logger.info("Forras lezarasa. no.1.");
            
            PrintWriter swingujsor = new PrintWriter(new BufferedWriter(new FileWriter(utvonal2, true))); 
            //csak egy új sort dob a fájl végére, mert az outputstream nem képes rá :D
            swingujsor.println();
            swingujsor.flush();
            swingujsor.close();
            logger.info("Forras lezarasa. no.2.");
        } catch (FileNotFoundException e) { e.printStackTrace(); }
		
	}
	
	/**
	 * Itt nem tortenik semmi lenyeges.<br>
	 * @param args parancssori argumentum.<br>
	 * @throws IOException kivetel valtodik ki, amennyiben egy I/O hiba merul fel.
	 *
	 */
	public static void main(String[] args) throws IOException {
		
	}
	
	/**
	 * Equals metodus az objektumok osszehasonlitasara.<br>
	 * @return true-val ter vissza, ha a ket objektum egyezik.<br>
	 * @param o az az objektum, amit az equals metodus megkap, hogy hasonlitsa ossze <br>
	 * azzal az objektummal, amire meg lett hivva az equals. 
	 */
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		if (o == null || !(o instanceof T0D04PP)){
			return false;
		}
		T0D04PP obj = (T0D04PP) o;
		return (getHely().equals(obj.getHely()) && getFeladat().equals(obj.getFeladat()));
	}

}