package be.vdab;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

class Main2 {
	private static final Path XML_PATH = Paths.get("/data/koersen.xml");
	private static final Path SCHEMA_PATH = Paths.get("koersen.xsd");
	private static final String DEFAULT_NAMESPACE = "http://www.vdab.be/koersen";
	public static void main(String[] args) {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try(Scanner scanner = new Scanner(System.in);
			Writer bufferedWriter = Files.newBufferedWriter(XML_PATH)) {
			factory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
			XMLStreamWriter writer = factory.createXMLStreamWriter(bufferedWriter);
			try {
				writer.setDefaultNamespace(DEFAULT_NAMESPACE);
				writer.writeStartDocument();
				writer.writeStartElement("koersen");
				writer.writeNamespace("xsi", XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
				writer.writeAttribute(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, 
					"schemalocation",
					DEFAULT_NAMESPACE + " " + SCHEMA_PATH.toString());
				writer.writeStartElement("datum");
				SimpleDateFormat formaat = new SimpleDateFormat("yyyy-MM-dd");
				writer.writeCharacters(formaat.format(new Date()));
				writer.writeEndElement();
				System.out.print("code:");
				for(String code; !"stop".equalsIgnoreCase(code = scanner.next());) {
					writer.writeEmptyElement("munt");
					writer.writeAttribute("code", code.toUpperCase());
					System.out.print("koers:");
					BigDecimal koers = scanner.nextBigDecimal();
					writer.writeAttribute("koers", koers.toString());
					System.out.println("code:");
				}
				writer.writeEndElement();
			}
			finally {
				writer.close();
			}
		}
		catch(IOException | XMLStreamException ex) {
			ex.printStackTrace();
		}

	}

}
