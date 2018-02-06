package be.vdab;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class Main9 {
	private static final Path PATH = Paths.get("/data/koers.xml");
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(PATH.toString());
			Element documentElement = document.getDocumentElement();
			verwerkNode(documentElement);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void verwerkNode(Node node) {
		switch(node.getNodeType()) {
		case Node.ELEMENT_NODE:
			System.out.println(node.getNodeName());
			NamedNodeMap attributen = node.getAttributes();
			for(int index=0; index != attributen.getLength(); index++) {
				Node attribuut = attributen.item(index);
				System.out.printf("\t%s %s%n", attribuut.getNodeName(),
					attribuut.getNodeValue());
			}
			NodeList childNodes = node.getChildNodes();
			for(int index = 0; index != childNodes.getLength(); index++) {
				verwerkNode(childNodes.item(index));
			}
			break;
		case Node.TEXT_NODE:
			System.out.println(node.getNodeValue());
			break;
		}
	}
}
