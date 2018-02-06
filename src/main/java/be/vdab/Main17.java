package be.vdab;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

class Main17 {
	private static final Path PATH = Paths.get("/data/nieuw.xml");
	
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Element element = document.createElement("nieuw");
			document.appendChild(element);
			DOMImplementationRegistry registry =
				DOMImplementationRegistry.newInstance();
			DOMImplementationLS implementation = 
				(DOMImplementationLS) registry.getDOMImplementation("LS");
			LSSerializer serializer = implementation.createLSSerializer();
			serializer.getDomConfig().setParameter("format-pretty-print", true);
			LSOutput output = implementation.createLSOutput();
			try(Writer bufferedWriter = Files.newBufferedWriter(PATH)) {
				output.setCharacterStream(bufferedWriter);
				serializer.write(document, output);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
