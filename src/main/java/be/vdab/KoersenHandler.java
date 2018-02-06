package be.vdab;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class KoersenHandler extends DefaultHandler {
	private int aantalMunten;
	private boolean valid = true;
	@Override
	public void startElement(String namespaceURI, String sName, String qName,
		Attributes attrs) throws SAXException {
		if("munt".equals(qName)) {
/*			System.out.printf("%s %s%n", 
				attrs.getValue("code"), attrs.getValue("koers"));
*/		
			aantalMunten++;
		}
	}
	
	@Override
	public void error(SAXParseException ex) throws SAXException {
		System.out.printf("%d %d %s%n", ex.getLineNumber(),
			ex.getColumnNumber(), ex.getMessage());
		valid = false;
	}
	
	public int getAantalMunten() {
		return aantalMunten;
	}
	
	public boolean isValid() {
		return valid;
	}
}
