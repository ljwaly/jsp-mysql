package com.ljw.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test11 {
	public static void main(String[] args) {
		int douzai=0;
		int doubuzai=0;
		int cuowu =0;
		String ipFilePath = "jiangsuip.txt";
		String ipFilePathdeal = "12222.txt";
		List<IpArea> ipAreaListOrg =  new ArrayList<IpArea>();
		List<IpArea> ipAreaListDeal =  new ArrayList<IpArea>();
		init(ipAreaListOrg, ipFilePath);
		init(ipAreaListDeal, ipFilePathdeal);
		//long i = changeIpToInt("223.210.063.250");
		//for (long j = 0; j < 100000L; j++) {
			boolean org = false;
			boolean deal = false;
			
			Random random = new Random(System.currentTimeMillis());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 4; i++) {
				int nextInt = random.nextInt(256);
				sb.append(nextInt+".");
			}
			String ip=sb.substring(0, sb.length()-1);
			String ip1="223.181.101.37";
			
			int i = changeIpToInt(ip1);	
			Long t1 =System.currentTimeMillis();
			for (IpArea ipArea : ipAreaListOrg) {
				int begin = changeIpToInt(ipArea.getIpStart());
				int end = changeIpToInt(ipArea.getIpEnd());
				if (i>=begin && i<=end) {
					org =true;
					break;
				}
			}
			Long t2=System.currentTimeMillis();
			for (IpArea ipArea : ipAreaListDeal) {
				int begin = changeIpToInt(ipArea.getIpStart());
				int end = changeIpToInt(ipArea.getIpEnd());
				if (i>=begin && i<=end) {
					deal = true;
					break;
				}
			}
			Long t3=System.currentTimeMillis();
			if ((org && !deal) || (!org && deal)) {
				System.out.println("i="+i+",org="+org+",deal="+deal+",ip="+ip1);
				cuowu++;
			}else if(org && deal){
				System.out.println("都在i="+i+",ip="+ip1);
				douzai++;
			}else {
				System.out.println("都不在="+i+",ip="+ip1);
				doubuzai++;
			}
			System.out.println(t2-t1);
			System.out.println(t3-t2);
			
		//}
		System.out.println("cuowu="+cuowu);
		System.out.println("douzai="+douzai);
		System.out.println("doubuzai="+doubuzai);
		
	}
	
	static void init(List<IpArea> ipAreaList, String filePath){
		
		String ipSource = getIpSource(filePath);
		String[] split = ipSource.split("\n");
		for (String string : split) {
			String[] ipBeginAndEnd = string.trim().split(";");
			ipAreaList.add(new IpArea(ipBeginAndEnd[0],ipBeginAndEnd[1]));
		}
		

	}
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
	static int changeIpToInt(String ip){
		String[] ips = ip.split("\\.");
		int ipAddr = (Integer.parseInt(ips[0]) << 24) | (Integer.parseInt(ips[1]) << 16)
				| (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);
		return ipAddr;
	}
}
