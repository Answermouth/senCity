package senCity;



public class TreeTraces extends AbstractTraces{
	
	private Node<String, Trace> elmts;
	
	public TreeTraces() {
		elmts = new Node<String, Trace>();
	}
	
	public void ajouter(Trace trace) {
		String ssid = trace.getSsid();
		int n = ssid.length();
		
		for (int i=0; i<n; i++) {
			while 
		}
		
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
