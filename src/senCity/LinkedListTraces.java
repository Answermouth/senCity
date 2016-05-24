package senCity;

import java.util.LinkedList;

public class LinkedListTraces extends Traces{
	
	public LinkedListTraces() {
		elmts = new LinkedList<>();
	}
	
	public LinkedListTraces extract(String ssid) {
		long a = System.currentTimeMillis();
		
		LinkedListTraces select = new LinkedListTraces();
		for (Trace trace: elmts) {
			if (trace.getSsid().equals(ssid))
				select.ajouter(trace);
		}
		
		a = System.currentTimeMillis() - a;
		println("duree extract : " + a + "ms");
		
		return select;
	}
	
	public static void main(String[] args) {
		LinkedListTraces traces = new LinkedListTraces();
		try {
			traces.load("src/senCity/capture_wifi_2.csv", "src/senCity/capture_gps_2.csv", 76);
			//System.out.println(Runtime.getRuntime().freeMemory());
			traces.save("src/senCity/output.txt");
		} catch(Exception e) {
			System.out.println(e);
		}
		
		traces.extract("eduroam");
		
	}
}
