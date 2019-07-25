package com.Baha;

//import pack
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

	private String ac = "";
	private String pw = "";

	public String GetLogin() {
		return host + login_url;
	}

	public String GetPost(String method) {
		if (method.equals("info")) {
			return host + post_info_url;
		} else if (method.equals("post")) {
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
			// 讀取XML資訊
			this.host = eElement.getElementsByTagName("host").item(0).getTextContent();

			nList = eElement.getElementsByTagName("login");
			nNode = nList.item(0);
			Element eLoginElement = (Element) nNode;
			this.login_url = String.format("%s/%s/%s/%s", eLoginElement.getAttribute("device"),
					eLoginElement.getAttribute("where"), eLoginElement.getAttribute("version"),
					eLoginElement.getElementsByTagName("dologin").item(0).getTextContent());

			nList = eElement.getElementsByTagName("post");
			nNode = nList.item(0);
			Element ePostElement = (Element) nNode;
			this.post_url = String.format("%s/%s/%s/%s", ePostElement.getAttribute("device"),
					ePostElement.getAttribute("where"), ePostElement.getAttribute("version"),
					ePostElement.getElementsByTagName("post").item(0).getTextContent());
			this.post_info_url = String.format("%s/%s/%s/%s&bsn=%s", ePostElement.getAttribute("device"),
					ePostElement.getAttribute("where"), ePostElement.getAttribute("version"),
					ePostElement.getElementsByTagName("getinfo").item(0).getTextContent(),
					ePostElement.getElementsByTagName("bsn").item(0).getTextContent());
			// user
			nList = doc.getElementsByTagName("User_information");
			nNode = nList.item(0);
			eElement = (Element) nNode;
			// 讀取XML資訊
			setAc(eElement.getElementsByTagName("account").item(0).getTextContent());
			setPw(eElement.getElementsByTagName("password").item(0).getTextContent());

			System.out.println("Initialized!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public void setRemAc(String ac) {
		try {
			File inputFile = new File("Config/Config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			doc.getDocumentElement().normalize();

			// user
			NodeList nList = doc.getElementsByTagName("User_information");

			Node nNode = nList.item(0);
			Element eElement = (Element) nNode;
			eElement.getElementsByTagName("account").item(0).setTextContent(ac);
			// System.out.print(eElement.getElementsByTagName("account").item(0).getTextContent());
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(inputFile);
			transformer.transform(source, result);
			setAc(ac);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setRem(String pw) {
		try {
			File inputFile = new File("Config/Config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			doc.getDocumentElement().normalize();

			// user
			NodeList nList = doc.getElementsByTagName("User_information");

			Node nNode = nList.item(0);
			Element eElement = (Element) nNode;
			eElement.getElementsByTagName("password").item(0).setTextContent(pw);
			// save
			// https://www.programcreek.com/java-api-examples/?class=org.w3c.dom.Node&method=setTextContent
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(inputFile);
			transformer.transform(source, result);
			setPw(pw);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
