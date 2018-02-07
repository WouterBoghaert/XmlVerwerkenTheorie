package be.vdab;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

class Main21 {
	private static final Path RESULT_PATH =
		Paths.get("/data/werknemersafdelingen.xml");
	private static final Path XSLT_PATH = Paths.get("/data/personeel.xslt");
	private static final Path SOURCE_PATH = Paths.get("/data/personeel.xml");
	
	public static void main(String[] args) {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			StreamSource styleSource = new StreamSource(XSLT_PATH.toString());
			Transformer transformer = transformerFactory.newTransformer(styleSource);
			StreamSource source = new StreamSource(SOURCE_PATH.toString());
			StreamResult result = new StreamResult(RESULT_PATH.toString());
			transformer.transform(source, result);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
