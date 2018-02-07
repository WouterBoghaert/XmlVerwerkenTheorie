package be.vdab;

import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import be.vdab.entities.Werknemer;

class Main18 {
	private final static Path PATH = Paths.get("/data/werknemer.xml");
	
	public static void main(String[] args) {
		Werknemer werknemer = new Werknemer(1, "Joe", "Dalton", 
			Arrays.asList("The Boss", "Heatedly"), BigDecimal.valueOf(100));
		try {
			JAXBContext context = JAXBContext.newInstance(Werknemer.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			try (Writer writer = Files.newBufferedWriter(PATH)) {
				marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
					"http://www.vdab.be/werknemer werknemer.xsd");
				marshaller.marshal(werknemer, writer);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
