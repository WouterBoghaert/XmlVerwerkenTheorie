package be.vdab;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

class Main11 {
	private static final Path PATH = Paths.get("/data/koersenMetSchema.xml");
	private static final String JAXP_SCHEMA_LANGUAGE = 
		"http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			factory.setValidating(true);
			factory.setNamespaceAware(true);
			factory.setAttribute(JAXP_SCHEMA_LANGUAGE, 
				XMLConstants.W3C_XML_SCHEMA_NS_URI);
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new KoersenErrorHandler());
			builder.parse(PATH.toString());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
