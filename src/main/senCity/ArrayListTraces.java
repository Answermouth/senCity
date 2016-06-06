package senCity;

import java.util.ArrayList;

public class ArrayListTraces extends Traces{
		
	public ArrayListTraces() {
		//elmts = new ArrayList<>(13443);
		elmts = new ArrayList<>(1000);
	}
	
	public ArrayListTraces extract(String ssid) {
		long a = System.currentTimeMillis();

		
		ArrayListTraces select = new ArrayListTraces();
		for (Trace trace: elmts) {
			if (trace.getSsid().equals(ssid))
				select.ajouter(trace);
		}
		
		a = System.currentTimeMillis() - a;
		println("duree extract : " + a + "ms");
		
		return select;
	}
	
	public static void main(String[] args) {
		ArrayListTraces traces = new ArrayListTraces();
		try {
			traces.load("src/senCity/capture_wifi_2.csv", "src/senCity/capture_gps_2.csv", 10);
			//System.out.println(Runtime.getRuntime().freeMemory());
			traces.save("src/senCity/output.txt");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		traces.extract("eduroam");		
	}
}
