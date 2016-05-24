package senCity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public abstract class Traces extends AbstractTraces implements Iterable<Trace> {

	protected Collection<Trace> elmts;
	
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
	
	public void save(String nomFichier) throws IOException {
		BufferedWriter writer;
		
		File file = new File(nomFichier);

		/*
		if (file.exists())
			throw new IOException("le fichier existe deja");
		*/
			
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
			
			for (Trace i: elmts) {
				writer.write(i.toString());
				writer.newLine();
			}
			
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
}