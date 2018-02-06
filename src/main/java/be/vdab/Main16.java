package be.vdab;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

class Main16 {
	private static final Path PATH = Paths.get("/data/koersen.xml");
	private static final String XPATH_EXPR = "//munt[@code='%s']";
	
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(PATH.toString());
			System.out.print("Code: ");
			try(Scanner scanner = new Scanner(System.in)) {
				String code = scanner.next().toUpperCase();
				XPathFactory xPathFactory = XPathFactory.newInstance();
				XPath xPath = xPathFactory.newXPath();
				Element element = (Element) xPath.evaluate(
					String.format(XPATH_EXPR, code), document, XPathConstants.NODE);
				if(element == null) {
					System.out.println("munt niet gevonden");
				}
				else {
					element.getParentNode().removeChild(element);
					DOMImplementationRegistry registry =
						DOMImplementationRegistry.newInstance();
					DOMImplementationLS implementation =
						(DOMImplementationLS) registry.getDOMImplementation("LS");
					LSSerializer serializer = implementation.createLSSerializer();
					serializer.getDomConfig().setParameter("format-pretty-print", true);
					try(Writer bufferedWriter = Files.newBufferedWriter(PATH)) {
						LSOutput output = implementation.createLSOutput();
						output.setCharacterStream(bufferedWriter);
						serializer.write(document, output);
					}
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
