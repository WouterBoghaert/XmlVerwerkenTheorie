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

class Main3 {
	private static final Path PATH = Paths.get("/data/koers.xml");
	public static void main(String[] args) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try(Reader bufferedReader = Files.newBufferedReader(PATH)) {
			XMLStreamReader reader = factory.createXMLStreamReader(bufferedReader);
			try {
				while(reader.hasNext()) {
					switch(reader.next()) {
					case XMLStreamConstants.START_ELEMENT:
						System.out.printf("begintag %s%n", reader.getLocalName());
						for(int index=0; index!=reader.getAttributeCount(); index++) {
							System.out.printf("\t%s:%s%n", 
								reader.getAttributeLocalName(index),
								reader.getAttributeValue(index));
						}
						break;
					case XMLStreamConstants.CHARACTERS:
						System.out.printf("tekst %s%n", reader.getText());
						break;
					case XMLStreamConstants.END_ELEMENT:
						System.out.printf("eindtag %s%n", reader.getLocalName());
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
		System.out.println();
	}

}
