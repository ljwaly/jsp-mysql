package com.ljw.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;



/**
 * ip网段检测
 * @author PC
 *
 */
public class IpCheck {

	
	/**
	 * 检查ip是否在cidr范围内
	 * 
	 * @param ip 待检查的ip
	 * @param cidr 待检查的ip子网 
	 * @return
	 */
	public static boolean isInRange(String ip, String cidr) throws Exception{

		// 待检测ip：ip段二进制位移后求和处理
		String[] ips = ip.split("\\.");
		int ipAddr = (Integer.parseInt(ips[0]) << 24) | (Integer.parseInt(ips[1]) << 16)
				| (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);

		// 待匹配子网：ip段二进制位移后求和处理
		String cidrIp = cidr.replaceAll("/.*", "");
		String[] cidrIps = cidrIp.split("\\.");
		int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24) | (Integer.parseInt(cidrIps[1]) << 16)
				| (Integer.parseInt(cidrIps[2]) << 8) | Integer.parseInt(cidrIps[3]);

		// 掩码处理
		int type = Integer.parseInt(cidr.replaceAll(".*/", ""));
		int mask = 0xFFFFFFFF << (32 - type);

		return (ipAddr & mask) == (cidrIpAddr & mask);
	}
	
	
	public static void main(String[] args) {
		
		int needCheckIp = changeIpToInt("223.210.063.250");
		
		String ipFilePath = "jiangsuip.txt";
		
		List<IpArea> ipAreaList =  new ArrayList<IpArea>();
		
		init(ipAreaList, ipFilePath);
		
		for (int i = 0; i < ipAreaList.size(); i++) {
			
			String ipStart1 = ipAreaList.get(i).getIpStart();
			String ipEnd1 = ipAreaList.get(i).getIpEnd();
			
			if (i+1<ipAreaList.size()) {
				String ipStart2 = ipAreaList.get(i+1).getIpStart();
				
				while(changeIpToInt(ipEnd1)+1==changeIpToInt(ipStart2)) {
					i++;
					ipEnd1 = ipAreaList.get(i).getIpEnd();
					if (i+1>=ipAreaList.size()) {
						break;
					}
					ipStart2 = ipAreaList.get(i+1).getIpStart();
				}
			}
			
			String[] ips = ipStart1.split("\\.");
			String temp ="";
			for (String string : ips) {
				temp +=(Integer.parseInt(string)+".");
			}
			ipStart1 =temp.substring(0, temp.length()-1);
			
			String[] ipsEnd = ipEnd1.split("\\.");
			String tempEnd ="";
			for (String string : ipsEnd) {
				tempEnd +=(Integer.parseInt(string)+".");
			}
			ipEnd1 =tempEnd.substring(0, tempEnd.length()-1);
			
			
//			int begin = changeIpToInt(ipStart1);
//			int end = changeIpToInt(ipEnd1);
			
			
			
			
//			if (begin <= needCheckIp && needCheckIp <= end) {
//				System.out.println("ok");
//			}
			//System.out.println("ip["+i+"]：[ipStart="+ipStart1+",ipEnd="+ipEnd1+"]");
			//System.out.println(result);
			System.out.println(ipStart1+"-"+ipEnd1);
		}
		
		
	}
	
	static void init(List<IpArea> ipAreaList, String filePath){
		
		String ipSource = getIpSource(filePath);
		String[] split = ipSource.split("\n");
		for (String string : split) {
			String[] ipBeginAndEnd = string.trim().split(";");
			ipAreaList.add(new IpArea(ipBeginAndEnd[0],ipBeginAndEnd[1]));
		}
		
//		ipAreaList.add(new IpArea("036.149.240.000","036.149.240.255"));//97
//		ipAreaList.add(new IpArea("036.149.241.000","036.149.241.255"));
//		ipAreaList.add(new IpArea("036.149.242.000","036.149.243.255"));
//		ipAreaList.add(new IpArea("036.149.244.000","036.149.244.255"));//100
//		ipAreaList.add(new IpArea("036.149.245.000","036.149.255.255"));//101
//		
//		ipAreaList.add(new IpArea("036.151.000.000","036.151.031.255"));//102
//		ipAreaList.add(new IpArea("036.151.032.000","036.151.047.255"));//103
//		ipAreaList.add(new IpArea("036.151.048.000","036.151.063.255"));//104
//		ipAreaList.add(new IpArea("036.151.064.000","036.151.095.255"));//105
//		ipAreaList.add(new IpArea("036.151.096.000","036.151.111.255"));//106
//		ipAreaList.add(new IpArea("036.151.112.000","036.151.119.255"));//107
//		ipAreaList.add(new IpArea("036.151.120.000","036.151.139.255"));//108
		
		
		
	}
	
	static int changeIpToInt(String ip){
		String[] ips = ip.split("\\.");
		int ipAddr = (Integer.parseInt(ips[0]) << 24) | (Integer.parseInt(ips[1]) << 16)
				| (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);
		return ipAddr;
	}
	
	public static String changeIntToIp(int value){
		if (value == 0) {
			return "";
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		StringBuilder sb =  new StringBuilder();
		
	    while (value>1)
	    {
	        int x=value%2;
	        value= value/2;
	        list.add(x);
	    }
	    list.add(value);
	    for (int i = (list.size()-1); i >= 0; i--) {
	    	sb.append(list.get(i));
		}
	    if ("0".equals(sb.toString())) {
			return "";
		}
	    
	    return sb.toString() ;
	}
	
	
	
//	/**
//	 * 是否是广东的ip
//	 * 
//	 * @param checkIp 需要检测的ip
//	 * @return true：是；false：不是
//	 * 		   
//	 */
//	public static boolean isGuangdongMobile(String checkIp) {
//		if (checkIp == null || "".equals(checkIp)) {
//			return false;
//		}
//		
//		String desFilePath = "ip/200.txt";//广东的ip文件地址
//		String resource = getIpSource(desFilePath);
//		if (resource == null || "".equals(resource)) {
//			return false;
//		}
//		
//		String[] ipArray = resource.split("\n");
//		for (String ip : ipArray) {
//			if (isInRange(checkIp, ip)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
	/**
	 * 读取资源文件
	 * 
	 * @param path
	 * @return
	 */
	private static String getIpSource(String path) {
		
		String classpath = ClassLoader.getSystemResource("").getPath();
		String filePath = classpath + path;
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {

			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String buffer = br.readLine();
			while (buffer != null) {
				sb.append(buffer.trim() + "\n");
				buffer = br.readLine();
			}
			return sb.toString();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
	}
//	
//	
//
//	
//	
//	// 测试
//	public static void main(String[] args) {
//		System.out.println(isInRange("221.177.63.255", "221.177.64.0/19"));
//		System.out.println(isInRange("221.177.64.1", "221.177.64.0/19"));
//		System.out.println(isInRange("221.177.95.254", "221.177.64.0/19"));
//		System.out.println(isInRange("221.177.96.0", "221.177.64.0/19"));
//		
//		System.out.println("************************文件读取测试***************************");
//
//		long begin = System.currentTimeMillis();
//		String ip = "112.60.0.0";
//		boolean result = isGuangdongMobile(ip);
//		long end = System.currentTimeMillis();
//		System.out.println("ip="+ip+", result="+result +", ["+ (end-begin) +"ms]");
//	}
}
