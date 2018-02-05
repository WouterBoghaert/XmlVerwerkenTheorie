package be.vdab;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class KoersenHandler extends DefaultHandler {
	@Override
	public void startElement(String namespaceURI, String sName, String qName,
		Attributes attrs) throws SAXException {
		if("munt".equals(qName)) {
			System.out.printf("%s %s%n", 
				attrs.getValue("code"), attrs.getValue("koers"));
		}
	}
}
