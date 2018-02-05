package be.vdab;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

class Main5 {
	private static final String URL = 
		"http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
	public static void main(String[] args) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try(InputStream stream = new URL(URL).openStream();
			InputStream bufferedStream = new BufferedInputStream(stream)) {
			XMLStreamReader reader = factory.createXMLStreamReader(bufferedStream);
			try {
				while(reader.hasNext()) {
					if(reader.next() == XMLStreamConstants.START_ELEMENT
						&& "Cube".equals(reader.getLocalName())
						&& reader.getAttributeCount() == 2) {
						System.out.printf("%s:%s%n", 
							reader.getAttributeValue(null, "currency"),
							reader.getAttributeValue(null, "rate"));
					}
				}
			}
			finally {
				reader.close();
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
