package com.ljw.test;



public class IpArea {
	private String ipStart;
	private String ipEnd;
	
	
	public IpArea() {
		super();
	}

	public IpArea(String ipStart, String ipEnd) {
		super();
		this.ipStart = ipStart;
		this.ipEnd = ipEnd;
	}

	@Override
	public String toString() {
		return "IpArea [ipStart=" + ipStart + ", ipEnd=" + ipEnd + "]";
	}
	
	public String getIpStart() {
		return ipStart;
	}
	public void setIpStart(String ipStart) {
		this.ipStart = ipStart;
	}
	public String getIpEnd() {
		return ipEnd;
	}
	public void setIpEnd(String ipEnd) {
		this.ipEnd = ipEnd;
	}
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(IpCheck.isInRange("223.104.212.7", "223.105.000.000/12"));
		
		
	}
}
