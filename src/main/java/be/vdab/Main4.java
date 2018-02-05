package be.vdab;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

class Main4 {
	private static final Path PATH = Paths.get("/data/koersen.xml");
	public static void main(String[] args) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try(Reader bufferedReader = Files.newBufferedReader(PATH)) {
			XMLStreamReader reader = factory.createXMLStreamReader(bufferedReader);
			try {
				while(reader.hasNext()) {
					if(reader.next() == XMLStreamConstants.START_ELEMENT
						&& "munt".equals(reader.getLocalName())
						&& "USD".equals(reader.getAttributeValue(null, "code"))) {
						System.out.println(reader.getAttributeValue(null,"koers"));
						break;
					}
				}
			}
			finally {
				reader.close();
			}
		}
		catch(IOException | XMLStreamException ex) {
			ex.printStackTrace();
		}
	}
}
