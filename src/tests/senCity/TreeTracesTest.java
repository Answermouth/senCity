package tests.senCity;

import static org.junit.Assert.*;

import org.junit.Test;

import main.senCity.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class TreeTracesTest {

	@Test
	public void testAjouter() {
		TreeTraces TT = new TreeTraces();
		Trace t1 = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		Trace t2 = new Trace("timestamp", "asid", 20, new GPS(20, 20));
		
		TT.ajouter(t1);
		TT.ajouter(t2);
		assertTrue(TT.getNodes().getChild().getChild().getChild().getElements().get(0) == t1);
		assertTrue(TT.getNodes().getBrother().getChild().getChild().getChild().getElements().get(0) == t2);
	}

	@Test
	public void testTreeTraces() {
		TreeTraces t = new TreeTraces();
		
		assertTrue(t.getNodes().getData() == '\0');
		assertTrue(t.getNodes().getBrother() == null);
		assertTrue(t.getNodes().getChild() == null);
		assertTrue(t.getNodes().getElements().isEmpty());
	}

	@Test
	public void testSetNodes() {
		Node n = new Node();
		TreeTraces TT = new TreeTraces();
		Trace t = new Trace("timestamp", "ssid", 20, new GPS(20, 20));

		TT.ajouter(t);
		
		TT.setNodes(n);
		
		assertTrue(TT.getNodes().getData() == '\0');
		assertTrue(TT.getNodes().getBrother() == null);
		assertTrue(TT.getNodes().getChild() == null);
		assertTrue(TT.getNodes().getElements().isEmpty());
	}

	@Test
	public void testExtractString() {
		TreeTraces TT = new TreeTraces();
		Trace t1 = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		Trace t2 = new Trace("timestamp", "said", 20, new GPS(20, 20));
		
		TT.ajouter(t1);
		TT.ajouter(t2);
		
		TreeTraces TT2 = TT.extract("ssid");
		
		assertFalse(TT2.getNodes().find("ssid", 0) == null);
		assertTrue(TT2.getNodes().find("said", 0) == null);
	}

	@Test
	public void testExtractAll() {
		TreeTraces TT = new TreeTraces();
		Trace t1 = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		Trace t2 = new Trace("timestamp", "said", 20, new GPS(20, 20));
		
		TT.ajouter(t1);
		TT.ajouter(t2);
		
		TreeTraces TT2 = TT.extractAll("s");
		
		assertFalse(TT2.getNodes().find("ssid", 0) == null);
		assertFalse(TT2.getNodes().find("said", 0) == null);
	}

	@Test
	public void testSave() {
		File tmp;
		BufferedReader reader;
		
		TreeTraces TT = new TreeTraces();
		Trace t1 = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		Trace t2 = new Trace("timestamp", "said", 20, new GPS(20, 20));
		
		TT.ajouter(t1);
		TT.ajouter(t2);
		
		try {
			TT.save("src/tests/senCity/tmp.txt");
			
			tmp = new File("src/tests/senCity/tmp.txt");
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
	public void testToString() {
		TreeTraces TT = new TreeTraces();
		Trace t1 = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		Trace t2 = new Trace("timestamp", "said", 20, new GPS(20, 20));
		
		String expected = "";
		
		assertEquals(expected, TT.toString());
		
		TT.ajouter(t1);
		TT.ajouter(t2);
		
		expected = TT.getNodes().toString();
		assertEquals(expected, TT.toString());
		
	}

}
