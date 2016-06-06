package tests.senCity;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import main.senCity.*;

import org.junit.Test;

public class HashMapTracesTest {

	@Test
	public void testHashMapTraces() {
		HashMapTraces traces = new HashMapTraces();
		
		assertTrue(traces.getMap().isEmpty());
	}

	@Test
	public void testExtractString() {
		HashMapTraces traces = new HashMapTraces();
		Trace t1 = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		Trace t2 = new Trace("timestamp", "asid", 20, new GPS(20, 20));

		traces.ajouter(t1);
		traces.ajouter(t2);

		HashMapTraces extracted = traces.extract("ssid");
		
		assertTrue(extracted.getTrace("ssid") == t1);
		assertTrue(extracted.getTrace("asid") == null);
	}

	@Test
	public void testSave() {
		File tmp;
		BufferedReader reader;
		
		HashMapTraces TT = new HashMapTraces();
		Trace t1 = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		Trace t2 = new Trace("timestamp", "said", 20, new GPS(20, 20));
		
		TT.ajouter(t1);
		TT.ajouter(t2);
		
		try {
			TT.save("src/main/senCity/tmp");
			
			tmp = new File("src/main/senCity/tmp");
			reader = new BufferedReader(new FileReader(tmp));
			
			String result = reader.readLine();
			String expected = t2.toString();
			assertEquals(expected, result);
			
			result = reader.readLine();
			expected = t1.toString();
			assertEquals(expected, result);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testAjouter() {
		HashMapTraces traces = new HashMapTraces();
		Trace t1 = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		Trace t2 = new Trace("timestamp", "said", 20, new GPS(20, 20));
		
		traces.ajouter(t1);
		traces.ajouter(t2);
		
		assertTrue(traces.getTrace("ssid") == t1);
		assertTrue(traces.getTrace("said") == t2);
	}

	@Test
	public void testTaille() {
		HashMapTraces traces = new HashMapTraces();
		Trace t1 = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		Trace t2 = new Trace("timestamp", "said", 20, new GPS(20, 20));
	
		assertTrue(traces.taille() == 0);
		traces.ajouter(t1);
		assertTrue(traces.taille() == 1);
		traces.ajouter(t2);
		assertTrue(traces.taille() == 2);
	}

	@Test
	public void testToString() {
		HashMapTraces traces = new HashMapTraces();
		Trace t1 = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		Trace t2 = new Trace("timestamp", "said", 20, new GPS(20, 20));
		
		String expected = t1.toString() + "\n";
		
		traces.ajouter(t1);
		assertEquals(expected, traces.toString());
		
		expected += t2.toString() + "\n";
		traces.ajouter(t2);
		assertEquals(expected, traces.toString());
	}
}
