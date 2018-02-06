package be.vdab;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

class Main10 {
	private static final String URL =
		"http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(URL);
			NodeList nodeList = document.getElementsByTagName("Cube");
			for(int index = 0; index != nodeList.getLength(); index++) {
				Element element = (Element) nodeList.item(index);
				String currency = element.getAttribute("currency");
				if(! currency.isEmpty()) {
					System.out.printf("%s %s%n", currency, element.getAttribute("rate"));
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
