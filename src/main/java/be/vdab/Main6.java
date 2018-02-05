package be.vdab;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class Main6 {
	private static final Path PATH = Paths.get("/data/koers.xml");
	public static void main(String[] args) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(PATH.toString(), new KoersHandler());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
