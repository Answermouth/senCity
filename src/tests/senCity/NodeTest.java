package tests.senCity;

import static org.junit.Assert.*;

import org.junit.Test;

import main.senCity.*;

public class NodeTest {

	@Test
	public void testNodeChar() {
		Node n  = new Node('a');
		assertTrue(n.getData() == 'a');
	}

	@Test
	public void testNode() {
		Node n = new Node();
		assertTrue(n.getData() == '\0');
		
	}

	@Test
	public void testSetData() {
		Node n = new Node();
		n.setData('a');
		assertTrue(n.getData() == 'a');
	}

	@Test
	public void testSetChild() {
		Node n = new Node();
		Node child = new Node();
		n.setChild(child);
		assertTrue(n.getChild() == child);
	}

	@Test
	public void testSetBrother() {
		Node n = new Node();
		Node brother = new Node();
		n.setBrother(brother);
		assertTrue(n.getBrother() == brother);
	}

	@Test
	public void testAddElement() {
		Node n = new Node();
		Trace elmt = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		n.addElement(elmt);
		assertTrue(n.getElements().get(0) == elmt);
	}

	@Test
	public void testAjouter() {
		Node n = new Node();
		Trace trace = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		
		n.ajouter("s", 0, trace);
		n.ajouter("ss", 0, trace);
		n.ajouter("ssi", 0, trace);
		n.ajouter("ssid", 0, trace);
		
		n.ajouter("a", 0, trace);
		n.ajouter("as", 0, trace);
		n.ajouter("asi", 0, trace);
		n.ajouter("asid", 0, trace);
		
		assertTrue(n.getElements().get(0) == trace);
		assertTrue(n.getChild().getElements().get(0) == trace);
		assertTrue(n.getChild().getChild().getElements().get(0) == trace);
		assertTrue(n.getChild().getChild().getChild().getElements().get(0) == trace);
		
		assertTrue(n.getBrother().getElements().get(0) == trace);
		assertTrue(n.getBrother().getChild().getElements().get(0) == trace);
		assertTrue(n.getBrother().getChild().getChild().getElements().get(0) == trace);
		assertTrue(n.getBrother().getChild().getChild().getChild().getElements().get(0) == trace);
	}

	@Test
	public void testFind() {
		Node n = new Node();
		Trace trace = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		
		n.ajouter("s", 0, trace);
		n.ajouter("ss", 0, trace);
		n.ajouter("ssi", 0, trace);
		n.ajouter("ssid", 0, trace);
		
		n.ajouter("a", 0, trace);
		n.ajouter("as", 0, trace);
		n.ajouter("asi", 0, trace);
		n.ajouter("asid", 0, trace);

		assertTrue(n.find("ssid", 0).getElements().get(0) == trace );
		assertTrue(n.find("asid", 0).getElements().get(0) == trace );
		assertTrue(n.find("asi", 0).getElements().get(0) == trace );
	}

	@Test
	public void testToString() {
		Node n = new Node();
		Trace trace = new Trace("timestamp", "ssid", 20, new GPS(20, 20));
		String expected;
		
		expected = "";
		assertEquals(expected, n.toString());
		
		n.ajouter("s", 0, trace);
		expected = trace.toString() + "\n";
		assertEquals(expected, n.toString());
		
		n.ajouter("ssid", 0, trace);
		expected += trace.toString() + "\n";
		assertEquals(expected, n.toString());
		
		n.ajouter("asid", 0, trace);
		expected += trace.toString() + "\n";
		assertEquals(expected, n.toString());
	}

}
