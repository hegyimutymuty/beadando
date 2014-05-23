package beadantoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import beadando.T0D04PP;

public class T0D04PPTest {

	@Test
	public void test() {
		int[] datumTest = {2014,1,1};
		int[] idoTest = {0,0};
		String feladatTest = "ejfel";
		String helyTest = "otthon";
		String szemelyTest = "en";
		
		T0D04PP a = new T0D04PP(datumTest, idoTest, feladatTest, helyTest, szemelyTest);
		T0D04PP b = new T0D04PP(datumTest, idoTest, feladatTest, helyTest, szemelyTest);
		
		assertTrue(a.equals(b));
	}

}
