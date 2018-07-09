package com.ljw.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Taobao {
	//org.jsoup.nodes
	public static void main(String[] args) throws IOException {
//		Document doc = Jsoup.connect( "https://detailskip.taobao.com/service/getData/1/p1/item/detail/sib.htm?"
//				+ "itemId=535806460401&sellerId=2842428475"
//				+ "&modules=dynStock,qrcode,viewer,price,duty,xmpPromotion,delivery,activity,fqg,zjys,couponActivity,soldQuantity,originalPrice,tradeContract"
//				+ "&callback=onSibRequestSuccess").get();
//
//		
//		//		String title = doc.title();
//		Element content = doc.getElementById("content");
//		
//		String baseUri = doc.baseUri();
//		System.out.println(content);
//		System.out.println(baseUri);
//		System.out.println(title);
		
		parse12();
		
	}
	
	public static String parse12() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		String url = "https://item.taobao.com/item.htm?spm=a230r.1.14.230.74f7f385WBwunU&id=535806460401&ns=1&abbucket=13#detail";
		WebClient webClient = new WebClient();
//		webClient.getOptions().setJavaScriptEnabled(true);
//		webClient.getOptions().setCssEnabled(false);
//		webClient.getOptions().setThrowExceptionOnScriptError(false);
//		webClient.getOptions().setTimeout(20000);
//		HtmlPage page = webClient.getPage(url);
		
		
		Document document = Jsoup.parse(new URL(url).openStream(), "gbk", url );
		System.out.println(document);
	
		return null;
	}
	
	
	
	
	public static String parseJS() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		String url = "https://item.taobao.com/item.htm?spm=a230r.1.14.230.74f7f385WBwunU&id=535806460401&ns=1&abbucket=13#detail";
		WebClient webClient = new WebClient();
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setTimeout(20000);
		
		//代理访问
//		webClient.getOptions().getProxyConfig().setProxyHost("");//IP
//		webClient.getOptions().getProxyConfig().setProxyPort(8080);
		
		
		HtmlPage page = webClient.getPage(url);
		
		
		String pageXml = page.asXml();
		System.out.println(pageXml);
		Document document = Jsoup.parse(pageXml, "https://item.taobao.com");
		Elements elements = document.select("div.content>a");
		String text = elements.text();
		
		String href = elements.attr("href");
		
		System.out.println("text="+text);
		System.out.println("href="+href);
		
		
		return null;
	}
	
	
	
	public String parseXML() throws IOException{
		File input = new File("C:/Users/PC/Desktop/test.html");
		Document doc = Jsoup.parse(input, "UTF-8","");
		Element content =doc.getElementById("content");
		System.out.println(content);
//		Elements links = content.getElementsByTag("a");
//		for (Element link : links) {
//			String linkHref =link.attr("href");
//			String linkText =link.text();
//		}
		return null;
	}
}
