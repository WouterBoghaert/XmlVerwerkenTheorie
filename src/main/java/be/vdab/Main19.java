package be.vdab;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import be.vdab.entities.Werknemer;

class Main19 {
	private static final Path PATH = Paths.get("/data/werknemer.xml");
	private final static Path SCHEMA_PATH = Paths.get("/data/werknemer.xsd");
	
	public static void main(String[] args) {
		try (Reader reader = Files.newBufferedReader(PATH)) {
			JAXBContext context = JAXBContext.newInstance(Werknemer.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			SchemaFactory factory = 
				SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(SCHEMA_PATH.toFile());
			unmarshaller.setSchema(schema);
			Werknemer werknemer = (Werknemer) unmarshaller.unmarshal(reader);
			System.out.println(werknemer);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
