package main.senCity;

import java.util.Iterator;
import java.util.Map;


public abstract class MapTraces extends AbstractTraces implements Iterable<Map.Entry<String,Trace>> {

	protected Map<String, Trace> elmts;
	
	//public abstract void initialiser();
	
	public void ajouter(Trace trace) {
		elmts.put(trace.getSsid(), trace);
	}
	
	public Trace getTrace(String ssid) {
		return elmts.get(ssid);
	}
	
	public Map<String, Trace> getMap() {
		return elmts;
	}
	
	public int taille() {
		return elmts.size();
	}
	
	public String toString() {
		String txt = "";
		for (Map.Entry<String, Trace> i : this) {
			txt += i.getValue().toString() + "\n";
		}
		
		return txt;
	}
	
	public Iterator<Map.Entry<String,Trace>> iterator() {
		return elmts.entrySet().iterator();
	}
}