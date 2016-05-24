package senCity;

import java.util.Scanner;

public class QuizzCar {
	
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);

		System.out.println("Veuiller entrer le chemin du fichier source Wifi");
		String inputWifi = in.nextLine();

		System.out.println("Veuiller entrer le chemin du fichier source GPS");
		String inputGps = in.nextLine();
		
		System.out.println("Veuiller entrer la tolerance acceptee (en pourcent)");
		int tolerance = Integer.parseInt(in.nextLine());
		
		String s = "";
		
		TreeTraces traces = new TreeTraces();
		try {
			traces.load(inputWifi, inputGps, tolerance);
			traces.save("src/senCity/output.txt");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		while (!s.equals("Q")) {
			System.out.println("Veuillez entrer un SSID a  extraire (Q pour quitter)");
			s = in.nextLine();
			System.out.print(traces.extract(s).toString());
		}
		
		in.close();
		
	}
}
