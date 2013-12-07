package fmat.arquitectura.DBAccess.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import fmat.arquitectura.DBAccess.Exceptions.DBConfigXMLException;

public class DBConfigReader {
	private String url, user, pass;

	public DBConfigReader() {

		try {
			File fXmlFile = new File("DBConfig.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			url = doc.getElementsByTagName("url").item(0).getTextContent();
			if(url.isEmpty())
				throw new DBConfigXMLException("La url esta vacia en el archivo DBConfig.xml");
			user = doc.getElementsByTagName("user").item(0).getTextContent();
			if(user.isEmpty())
				throw new DBConfigXMLException("User esta vacio en el archivo DBConfig.xml");
			pass = doc.getElementsByTagName("pass").item(0).getTextContent();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public static void main(String args[]){
		DBConfigReader db = new DBConfigReader();
		System.out.println(db.getUrl()+"/"+db.getUser()+"/"+db.getPass());
	}

}
