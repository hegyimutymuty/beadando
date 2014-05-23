package beadantoTest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import beadando.T0D04PPConsole;

public class T0D04PPConsoleTest {

	@Test
	public void test1() {
		T0D04PPConsole.ujSor("2014.05.11 11.42 Tanulas . Gergo");
		T0D04PPConsole.mentes("target/test-classes/Dict.txt");
		
		String sor = null;
		try {
			Scanner sc = new Scanner(new File("target/test-classes/Dict.txt"));
			while (sc.hasNextLine()){
				sor = sc.nextLine();
			}
		} catch (Exception e){
			
	    }
		assertTrue(sor.equals("2014.5.11 11:42 [Tanulas] [.] [Gergo]"));
	}
	
	@Test
	public void test2() {
		T0D04PPConsole.ujSor("2014.05.11 11.42 Tanulas Gergoek Gergo");
		T0D04PPConsole.mentes("target/test-classes/Dict.txt");
		
		String sor = null;
		try {
			Scanner sc = new Scanner(new File("target/test-classes/Dict.txt"));
			while (sc.hasNextLine()){
				sor = sc.nextLine();
			}
		} catch (Exception e){
			
	    }
		assertTrue(sor.equals("2014.5.11 11:42 [Tanulas] [Gergoek] [Gergo]"));
	}
	
	@Test
	public void test3() {
		T0D04PPConsole.ujSor("2014.05.11 11.42 Tanulas Gergoek_haza Gergo");
		T0D04PPConsole.mentes("target/test-classes/Dict.txt");
		
		String sor = null;
		try {
			Scanner sc = new Scanner(new File("target/test-classes/Dict.txt"));
			while (sc.hasNextLine()){
				sor = sc.nextLine();
			}
		} catch (Exception e){
			
	    }
		assertTrue(sor.equals("2014.5.11 11:42 [Tanulas] [Gergoek_haza] [Gergo]"));
	}
	
	@Before
	public void before() {
		File file = new File("target/test-classes/Dict.txt");
		file.delete();
	}

}