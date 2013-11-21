package fmat.arquitectura.DBAccess.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class PoolConfigReader {
	private int numSegmentos,tamSegmento;
	
	public PoolConfigReader(){
		try {

			File fXmlFile = new File("PoolConfig.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			numSegmentos = Integer.parseInt(doc.getElementsByTagName("numSegmentos").item(0).getTextContent());
			tamSegmento = Integer.parseInt(doc.getElementsByTagName("tamSegmento").item(0).getTextContent());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNumSegmentos() {
		return numSegmentos;
	}
	public void setNumSegmentos(int numSegmentos) {
		this.numSegmentos = numSegmentos;
	}
	public int getTamSegmento() {
		return tamSegmento;
	}
	public void setTamSegmento(int tamSegmento) {
		this.tamSegmento = tamSegmento;
	}
}
