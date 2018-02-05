package be.vdab;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class KoersHandler extends DefaultHandler {
	@Override
	public void startElement(String namespaceURI, String sName, String qName,
		Attributes attrs) throws SAXException {
		System.out.printf("begintag %s%n", qName);
		for(int index = 0; index!= attrs.getLength(); index++) {
			System.out.printf("\t%s:%s%n", attrs.getLocalName(index),
				attrs.getValue(index));
		}
	}
	
	@Override
	public void characters(char buf[], int offset, int len) throws SAXException {
		System.out.printf("tekst %s%n", new String (buf, offset, len));
	}
	
	@Override
	public void endElement(String namespaceURI, String sName, String qName)
		throws SAXException {
		System.out.printf("eindtag %s%n", qName);
	}
}
