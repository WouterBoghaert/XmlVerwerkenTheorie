package be.vdab;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

class Main {
	private static final Path PATH = Paths.get("/data/koers.xml");
	public static void main(String[] args) {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try(Writer bufferedWriter = Files.newBufferedWriter(PATH)) {
			XMLStreamWriter writer = factory.createXMLStreamWriter(bufferedWriter);
			try {
				writer.writeStartDocument();
				writer.writeStartElement("koersen");
				writer.writeStartElement("datum");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				writer.writeCharacters(format.format(new Date()));
				writer.writeEndElement();
				writer.writeEmptyElement("munt");
				writer.writeAttribute("code", "USD");
				writer.writeAttribute("koers", BigDecimal.valueOf(1.1).toString());
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
