package be.vdab;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import be.vdab.entities.Koersen;
import be.vdab.entities.Munt;

class Main20 {
	private final static Path PATH = Paths.get("/data/koersen.xml");

	public static void main(String[] args) {
		try (Reader reader = Files.newBufferedReader(PATH)) {
			JAXBContext context = JAXBContext.newInstance(Koersen.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Koersen koersen = (Koersen) unmarshaller.unmarshal(reader);
			for(Munt munt : koersen.getMunt()) {
				System.out.printf("%s %f%n", munt.getCode(), munt.getKoers());
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
