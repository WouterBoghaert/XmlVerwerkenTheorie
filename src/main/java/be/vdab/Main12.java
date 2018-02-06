package be.vdab;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

class Main12 {
	private static final String URL =
		"http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
	private static final String XPATH_EXPR = "//Cube[@rate<1]";
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(URL);
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();
			NodeList nodeList = (NodeList)
				xPath.evaluate(XPATH_EXPR, document, XPathConstants.NODESET);
			for(int index=0; index != nodeList.getLength(); index++) {
				Element element = (Element) nodeList.item(index);
				System.out.printf("%s %s%n", xPath.evaluate("@currency",element),
					xPath.evaluate("@rate", element));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
