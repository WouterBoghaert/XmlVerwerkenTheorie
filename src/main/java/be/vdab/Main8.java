package be.vdab;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class Main8 {
	private static final Path PATH = Paths.get("/data/koersenMetSchema.xml");
	private static final String JAXP_SCHEMA_LANGUAGE = 
		"http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	public static void main(String[] args) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		try {
			SAXParser parser = factory.newSAXParser();
			parser.setProperty(JAXP_SCHEMA_LANGUAGE, 
				XMLConstants.W3C_XML_SCHEMA_NS_URI);
			KoersenHandler handler = new KoersenHandler();
			parser.parse(PATH.toString(), handler);
			if(handler.isValid()) {
				System.out.printf("%d munt(en)", handler.getAantalMunten());
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
