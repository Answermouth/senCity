package senCity;

public class Trace {
	private String ssid, ts;
	private int signal;
	private GPS gps;

	public Trace(String timestamp, String ssid, int signalStrength, GPS gps) {
		ts = timestamp;
		this.ssid = ssid;
		signal = signalStrength;
		this.gps = gps;
	}
	
	public GPS getGps() {
		return gps;
	}
	
	public void setGps(GPS gps) {
		this.gps = gps;
	}
	
	public String getSsid() {
		return ssid;
	}
	
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	
	public int getSignal() {
		return signal;
	}
	
	public void setSignal(int signal) {
		this.signal = signal;
	}
	
	public String getTimestamp() {
		return ts;
	}
	
	public void setTimestamp(String ts) {
		this.ts = ts;
	}
	
	public String toString() {
		return ts + "	" + ssid + "	" + signal + "  " + gps.toString();
	}
}
