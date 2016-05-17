package senCity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HashMapTraces extends MapTraces{
		
	public HashMapTraces() {
		elmts = new HashMap<String, Trace>(1000);
	}
	
	public HashMapTraces extract(String ssid) {
		long a = System.currentTimeMillis();

		
		HashMapTraces select = new HashMapTraces();
		for (Map.Entry<String, Trace> trace: this) {
			if (trace.getValue().getSsid().equals(ssid))
				select.ajouter(trace.getValue());
		}
		
		a = System.currentTimeMillis() - a;
		println("duree extract : " + a + "ms");
		
		return select;
	}
	
	public void save(String nomFichier) throws IOException {
		BufferedWriter writer;
		
		File file = new File(nomFichier);

		/*
		if (file.exists())
			throw new IOException("le fichier existe deja");
		*/
			
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
						
			for (String i: elmts.keySet()) {
				writer.write(elmts.get(i).toString());
				writer.newLine();
			}
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void main(String[] args) {
		HashMapTraces traces = new HashMapTraces();
		try {
			traces.load("src/senCity/capture_wifi_2.csv", "src/senCity/capture_gps_2.csv", 10);
			//System.out.println(Runtime.getRuntime().freeMemory());
			traces.save("src/senCity/output.txt");
		} catch(Exception e) {
			System.out.println(e);
		}
		
		traces.extract("eduroam");		
	}
}
