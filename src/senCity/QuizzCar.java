package senCity;

import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class QuizzCar {
	
	public static double distance(Trace T1, Trace T2) {
		int R = 6371;
		
		double lon1 = T1.getGps().getLongitude();
		double lat1 = T1.getGps().getLatitude();
		
		double lon2 = T2.getGps().getLongitude();
		double lat2 = T2.getGps().getLatitude();
		
		double phi1 = Math.toDegrees(lat1);
		double phi2 = Math.toDegrees(lat2);
		
		double dphi = Math.toDegrees(lat2 - lat1);
		double dlambda = Math.toDegrees(lon2- lon1);
		
		double a = Math.sin(dphi/2) * Math.sin(dphi/2) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(dlambda/2) * Math.sin(dlambda/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		double d = R * c; 
		
		return d;
	}
	
	public static ArrayList<Trace> getUniques(TreeTraces traces) {
		if (traces.getNodes() != null) {
			System.out.println("getUniques");
			
			ArrayList<Trace> multiples = new ArrayList<Trace>();
			ArrayList<Trace> uniques = new ArrayList<Trace>();
			
			multiples = traces.getNodes().getElements();
			
			boolean exists;
			
			for (Trace i : multiples) {
				if (uniques.isEmpty()) 
					uniques.add(i);
				
				exists = false;
				for (Trace j: uniques) {
					if (i.getSsid().equals(j.getSsid()) && i.getGps().getLatitude() == j.getGps().getLatitude() && i.getGps().getLongitude() == j.getGps().getLongitude()) {
						exists = true;
						if (i.getSignal() > j.getSignal())
							j.setSignal(i.getSignal());
					}
				}
				
				if (!exists) {
					System.out.println(i.toString());
					uniques.add(i);
				}
			}
	
			return uniques;
		} else 
			return null;
	}
	
	
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);

		/*
		System.out.println("Veuiller entrer le chemin du fichier source Wifi");
		String inputWifi = in.nextLine();

		System.out.println("Veuiller entrer le chemin du fichier source GPS");
		String inputGps = in.nextLine();
		
		System.out.println("Veuiller entrer la tolerance acceptee (en pourcent)");
		int tolerance = Integer.parseInt(in.nextLine());
		*/
		
		String s = "";
		
		TreeTraces traces = new TreeTraces();
		try {
			traces.load("src/senCity/capture_wifi_2.csv", "src/senCity/capture_gps_2.csv", 76);
			//traces.load(inputWifi, inputGps, tolerance);
			traces.save("src/senCity/output.txt");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		while (!s.equals("Q")) {
			System.out.println("Veuillez entrer un SSID a  extraire (Q pour quitter)");
			s = in.nextLine();
			
			if (!s.equals("Q")) {
				//System.out.print(traces.extractAll(s).toString());
				getUniques(traces.extractAll(s));
				try {
					traces.extractAll(s).save("src/senCity/output2.txt");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
				
		in.close();
		
	}
}
