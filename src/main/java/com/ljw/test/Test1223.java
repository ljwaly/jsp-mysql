package com.ljw.test;

public class Test1223 {
	
	
	
	public static void main(String[] args) {
		String iparea="36.149.0.0-36.149.255.255";
		System.out.println(ipIsInNet(iparea,"36.149.45.255"));
	}
	
	/** 
     * 判断ip是否在指定网段中 
     * @author dh 
     * @param iparea 
     * @param ip 
     * @return boolean 
     */  
    public static boolean ipIsInNet(String iparea, String ip) {  
        if (iparea == null)  
            throw new NullPointerException("IP段不能为空！");  
        if (ip == null)  
            throw new NullPointerException("IP不能为空！");  
        iparea = iparea.trim();  
        ip = ip.trim();  
        final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";  
        final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;  
        if (!iparea.matches(REGX_IPB) || !ip.matches(REGX_IP))  {
        	return false;  
        }
        
        int idx = iparea.indexOf('-');  
        String[] sips = iparea.substring(0, idx).split("\\.");  
        String[] sipe = iparea.substring(idx + 1).split("\\.");  
        String[] sipt = ip.split("\\.");  
        long ips = 0L, ipe = 0L, ipt = 0L;  
        for (int i = 0; i < 4; ++i) {  
            ips = ips << 8 | Integer.parseInt(sips[i]);  
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);  
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);  
        }  
        if (ips > ipe) {  
            long t = ips;  
            ips = ipe;  
            ipe = t;  
        }  
        return ips <= ipt && ipt <= ipe;  
    }
}
