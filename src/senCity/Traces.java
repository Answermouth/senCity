package senCity;

import java.util.Collection;
import java.util.Iterator;


public abstract class Traces extends AbstractTraces implements Iterable<Trace> {

	protected Collection<Trace> elmts;
	
	//public abstract void initialiser();
	
	public void ajouter(Trace trace) {
		elmts.add(trace);
	}
	
	public int taille() {
		return elmts.size();
	}
	
	public String toString() {
		String txt = "Timestamp, SSID, Signal \n";
		for (Trace i : elmts) {
			txt += i.toString() + "\n";
		}
		
		return txt;
	}
	
	public Iterator<Trace> iterator() {
		return elmts.iterator();
	}
}