package senCity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TreeTraces extends AbstractTraces{
	
	private Node elmts;
	
	public TreeTraces() {
		elmts = new Node();
	}
	
	public void ajouter(Trace trace) {
		String ssid = trace.getSsid();
		
		elmts.ajouter(ssid, 0, trace);
	}
	
	public Node getNodes() {
		return elmts;
	}
	
	public void setNodes(Node root) {
		elmts = root;
	}
	
	public TreeTraces extract(String ssid) {
		long a = System.currentTimeMillis();

		
		TreeTraces select = new TreeTraces();

		ArrayList<Trace> tracesSelect = new ArrayList<Trace>();
		
		tracesSelect = this.elmts.find(ssid, 0).getElements(); 
		
		for (Trace t: tracesSelect) {
			select.ajouter(t);
		}
		
		a = System.currentTimeMillis() - a;
		println("duree extract : " + a + "ms");
		
		return select;
	}
	
	public TreeTraces extractAll(String prefixe) {
		TreeTraces select = new TreeTraces(); 
		Node node = new Node();
		
		node =  this.elmts.find(prefixe, 0);
		
		select.setNodes(node);
		
		return select;
	}
	
	private void saveRecurs(Node node) throws IOException {
		try {
			if (node.getElements() != null) {
				for (Trace t: node.getElements()) {
					writer.write(t.toString());
					writer.newLine();
				}
			}
			
			if (node.getBrother() != null)
				saveRecurs(node.getBrother());
			
			if (node.getChild() != null)
				saveRecurs(node.getChild());
			
		} catch(Exception e) {
			throw e;
		}
	}

	
	public void save(String nomFichier) throws IOException {		
		File file = new File(nomFichier);

		/*
		if (file.exists())
			throw new IOException("le fichier existe deja");
		*/
			
		
		try {
			writer = new BufferedWriter(new FileWriter(file));	
				
			saveRecurs(elmts);
			
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String toString() {
		return elmts.toString();
	}
	
	
	public static void main(String[] args) {
		TreeTraces traces = new TreeTraces();
		try {
			traces.load("src/senCity/capture_wifi_2.csv", "src/senCity/capture_gps_2.csv", 76);
			//System.out.println(Runtime.getRuntime().freeMemory());
			traces.save("src/senCity/output.txt");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		traces.extract("eduroam");
		traces.extract("VirginBox_91D8");
		
	}
	
}
