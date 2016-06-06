package senCity;

import java.util.Iterator;
import java.util.Map;


public abstract class MapTraces extends AbstractTraces implements Iterable<Map.Entry<String,Trace>> {

	protected Map<String, Trace> elmts;
	
	//public abstract void initialiser();
	
	public void ajouter(Trace trace) {
		elmts.put(trace.getSsid(), trace);
	}
	
	public int taille() {
		return elmts.size();
	}
	
	public String toString() {
		String txt = "Timestamp, SSID, Signal \n";
		for (Map.Entry<String, Trace> i : this) {
			txt += i.getValue().toString() + "\n";
		}
		
		return txt;
	}
	
	public Iterator<Map.Entry<String,Trace>> iterator() {
		return elmts.entrySet().iterator();
	}
}