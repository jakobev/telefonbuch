package de.eves.Telefonbuch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CSVParserJUnitTest1 {

	private static final String PATH = "src/test/resources/testUser.csv";
	private static final String PATH_TO_WRITE = "C://users/mbittermann/documents/test.csv";
	private static final String CSV_PATH = "C://users/mbittermann/documents/telefonbuch.csv";
	private static final String CSV = "C://users/mbittermann/documents/junit.csv";

	private static final String CSV_READ_COMPARE = "C://users/mbittermann/documents/junitread.csv";
	private static final String CSV_FINAL = "C://users/mbittermann/desktop/testUser.csv";
	private static final String ENTITY_TEST_CSV = "C://users/mbittermann/documents/entityTest.csv";
	private ColumnPositionMappingStrategy strategy;

	private List<Contact> buildContact() {
		List<Contact> contacts = new ArrayList<>();
		Contact contact = new Contact();
		contact.setVorname("marvin");
		contact.setNachname("bitt");
		contact.setNoCompany("eves");
		contact.AddAdress("Am Hasselberg", " 15a", " 38228", " Salzgitter");
//		contact.addPhoneNumber("5551654");
		contact.setPhone("5551654");
		contacts.add(contact);
		return contacts;

	}

	@SuppressWarnings({ "rawtypes", "unchecked", "restriction" })
	@Test
	public void testParse() {
		strategy = new ColumnPositionMappingStrategy();
		strategy.setType(Contact.class);

		strategy.setColumnMapping("noCompany", "vorname", "nachname", "phone", "adresse");

		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get(PATH), Charset.forName("ISO-8859-1"));

			CsvToBean<Contact> csv = new CsvToBeanBuilder(reader).withType(Contact.class).withSeparator(';')
					.withIgnoreLeadingWhiteSpace(true).withMappingStrategy(strategy).build();

			/**
			 * testing if created contact is equals too csv file
			 */
			List<Contact> contacts = buildContact();
			List<Contact> userList = csv.parse();
			Contact alt = userList.get(1);
			Contact neu = contacts.get(0);

			assertEquals(alt.getVorname(), neu.getVorname());
			assertEquals(alt.getVorname(), neu.getVorname());
			assertEquals(alt.getNachname(), neu.getNachname());
			String[] foneAlt = alt.getPhone().split(",");
			String[] foneNeu = neu.getPhone().split(",");
			assertEquals(foneAlt[0], foneNeu[0]);
			String[] adress = alt.getAdresse().split("\\|");
			for (String string : adress) {
				String[] teil = string.split(",");
				assertEquals(teil[0], neu.getAdressen().get(0).getStrasseProp().getValue());
				assertEquals(teil[1], neu.getAdressen().get(0).getHausnummerProp().getValue());
				assertEquals(teil[2], neu.getAdressen().get(0).getPostleitzahlProp().getValue());
				assertEquals(teil[3], neu.getAdressen().get(0).getStadtProp().getValue());

//--------------------------------------Obere Teil läuft-----------------------------------------------------				
			}
			/**
			 * testing if parsed entity is the same as in csv file
			 */
			Reader read = Files.newBufferedReader(Paths.get(CSV_PATH), Charset.forName("ISO-8859-1"));

			CsvToBean<Contact> csvv = new CsvToBeanBuilder(read).withType(Contact.class).withSeparator(';')
					.withIgnoreLeadingWhiteSpace(true).withMappingStrategy(strategy).build();

			List<Contact> parsedFromCsvDataContactList = csvv.parse();

			Contact parsedContact = parsedFromCsvDataContactList.get(0);

			assertEquals("eves", parsedContact.getNoCompany());
			assertEquals("Marvin", parsedContact.getVorname());
			assertEquals("Bittermann", parsedContact.getNachname());
			assertEquals("666", parsedContact.getPhone());
			String[] adresse = parsedContact.getAdresse().split("\\|");
			for (String string : adresse) {
				String[] adressTeil = string.split(",");
				assertEquals("Am Hasselberg", adressTeil[0]);
				assertEquals(" 15a", adressTeil[1]);
				assertEquals(" 38228", adressTeil[2]);
				assertEquals(" Salzgitter", adressTeil[3]);
			}
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// -------------------------Obere Test
		// läuft-------------------------------------------

	}

	/**
	 * testing if written entity to csv file is equals to gotten entity from read
	 * parser
	 * 
	 * @throws IOException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void writeToCsv() {
		File file = new File(CSV);
		file.getParentFile().mkdirs();
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		strategy = new ColumnPositionMappingStrategy();
		strategy.setType(Contact.class);

		strategy.setColumnMapping("noCompany", "vorname", "nachname", "phone", "adresse");

		List<Contact> allContacts = new ArrayList<>();

		String kontaktDaten = "";

		Contact kontakt = new Contact();
		Address adresse = new Address();
		adresse.setStrasse("An der Windmühle");
		adresse.setHausnummer("26");
		adresse.setPostleitzahl("38226");
		adresse.setStadt("Salzgitter");
		Phone phone = new Phone();
		phone.setPhoneNumber("555777789");
		kontakt.setNoCompany("eves");
		kontakt.setVorname("marv");
		kontakt.setNachname("bitt");
		kontakt.addAddress(adresse);
		kontakt.addPhone(phone);
		allContacts.add(kontakt);

		kontaktDaten = kontakt.getNoCompany() + ";" + kontakt.getVorname() + ";" + kontakt.getNachname() + ";"
				+ kontakt.getPhoneNumbers() + ";" + kontakt.getAddressen();
		try {
			Files.write(Paths.get(CSV), (kontaktDaten + "\r\n").getBytes(), StandardOpenOption.WRITE);
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void readWritten() {
		try {
			ColumnPositionMappingStrategy strategie = new ColumnPositionMappingStrategy();
			strategie.setType(Contact.class);
			strategie.setColumnMapping("noCompany", "vorname", "nachname", "phone", "adresse");
			Reader readFile = Files.newBufferedReader(Paths.get(CSV), Charset.forName("ISO-8859-1"));
			CsvToBean<Contact> creader = new CsvToBeanBuilder(readFile).withType(Contact.class).withSeparator(';')
					.withIgnoreLeadingWhiteSpace(true).withMappingStrategy(strategie).build();

			List<Contact> alle = creader.parse();

			assertNotNull(alle);
			Contact newContact = alle.get(0);
			assertEquals("eves", newContact.getNoCompany());
			assertEquals("marv", newContact.getVorname());
			assertEquals("bitt", newContact.getNachname());
			assertEquals("555777789", newContact.getPhone());
			String[] adressen = newContact.getAdresse().split("\\|");
			for (String string : adressen) {
				String[] addTeil = string.split(",");
				assertEquals("An der Windmühle", addTeil[0]);
				assertEquals("26", addTeil[1]);
				assertEquals("38226", addTeil[2]);
				assertEquals("Salzgitter", addTeil[3]);
				readFile.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	@Test(expected = IllegalStateException.class)
	/**
	 * gets an runtime exception because test throws an EntityException because of
	 * missing headers
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(expected = RuntimeException.class)
	public void throwEntityException() {

		try (Reader r = Files.newBufferedReader(Paths.get(ENTITY_TEST_CSV), Charset.forName("ISO-8859-1"));

		) {

			CsvToBean<TestContactEntity> toBean = new CsvToBeanBuilder(r).withSeparator(';')
					.withType(TestContactEntity.class).withIgnoreLeadingWhiteSpace(true).build();

			toBean.parse();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
