package be.vdab;

import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

class Main14 {
	private static final Path PATH = Paths.get("/data/koersen.xml");
	private static final String XPATH_EXPR_DATUM = "//datum/text()";
	private static final String XPATH_EXPR_USD_KOERS =
		"//munt[@code='USD']/@koers";
	
	public static void main(String args[]) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(PATH.toString());
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();
			Text text = (Text) xPath.evaluate(XPATH_EXPR_DATUM, document,
				XPathConstants.NODE);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			text.setData(format.format(new Date()));
			Attr attr = (Attr) xPath.evaluate(XPATH_EXPR_USD_KOERS, document,
				XPathConstants.NODE);
			if(attr == null) {
				System.out.println("USD niet gevonden");
			}
			else {
				System.out.printf("Koers:%s%n", new BigDecimal(attr.getValue()));
				System.out.print("Nieuwe koers: ");
				try(Scanner scanner = new Scanner(System.in)) {
					BigDecimal nieuweKoers = scanner.nextBigDecimal();
					attr.setValue(nieuweKoers.toString());
					DOMImplementationRegistry registry =
						DOMImplementationRegistry.newInstance();
					DOMImplementationLS implementation =
						(DOMImplementationLS) registry.getDOMImplementation("LS");
					LSSerializer serializer = implementation.createLSSerializer();
					LSOutput output = implementation.createLSOutput();
					try(Writer bufferedWriter = Files.newBufferedWriter(PATH)) {
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
