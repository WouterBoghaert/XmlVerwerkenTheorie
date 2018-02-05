package be.vdab;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class Main7 {
	private static final Path PATH = Paths.get("/data/koersen.xml");
	public static void main(String[] args) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(PATH.toString(), new KoersenHandler());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
