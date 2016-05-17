package senCity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public abstract class ListTraces extends AbstractTraces{
	
	Collection<Trace> elmts;
	
	public void ajouter(Trace trace) {
		elmts.add(trace);
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
