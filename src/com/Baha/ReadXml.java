package com.Baha;

//import pack
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ReadXml {
	private String host = "";
	private String login_url = "";
	private String post_url = "";
	private String post_info_url = "";
	public String GetLogin() {
		return host + login_url;
	}
	
	public String GetPost(String method) {
		if(method.equals("info")) {
			return host + post_info_url;
		}else if (method.equals("post")) {
			return host + post_url;
		}
		return "error";
	}
	
	public ReadXml() {
		try {
			File inputFile = new File("Config/Config.xml");
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Baha_Setting");
			
			Node nNode = nList.item(0);
			Element eElement = (Element) nNode;
			//讀取XML資訊
			this.host = eElement.getElementsByTagName("host").item(0).getTextContent();
			
			nList = eElement.getElementsByTagName("login");
			nNode = nList.item(0);
			Element eLoginElement  = (Element) nNode;
			this.login_url = String.format("%s/%s/%s/%s", 
					eLoginElement.getAttribute("device"),
					eLoginElement.getAttribute("where"),
					eLoginElement.getAttribute("version"),
					eLoginElement.getElementsByTagName("dologin").item(0).getTextContent()
				);
			
			nList = eElement.getElementsByTagName("post");
			nNode = nList.item(0);
			Element ePostElement = (Element) nNode;
			this.post_url = String.format("%s/%s/%s/%s", 
					ePostElement.getAttribute("device"),
					ePostElement.getAttribute("where"),
					ePostElement.getAttribute("version"),
					ePostElement.getElementsByTagName("post").item(0).getTextContent()
				);
			this.post_info_url = String.format("%s/%s/%s/%s&bsn=%s", 
					ePostElement.getAttribute("device"),
					ePostElement.getAttribute("where"),
					ePostElement.getAttribute("version"),
					ePostElement.getElementsByTagName("getinfo").item(0).getTextContent(),
					ePostElement.getElementsByTagName("bsn").item(0).getTextContent()
				);
			
			System.out.println("Initialized!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
