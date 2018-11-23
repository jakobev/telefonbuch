package de.eves.Telefonbuch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * 
 * @author mbittermann
 *
 */
public class ContactDataHandler extends DesignBuilder {

	private AlertHandler alert = new AlertHandler();
	private static File file;
	private static String docupath;
	private Contact contact;
	private static ArrayList<Contact> contacts = new ArrayList<>();
	private DesignBuilder builder;
	private String nummer;

	public ContactDataHandler(DesignBuilder controller) {
		builder = controller;
	}

	/**
	 * if the save button is pressed, the data of the inputfields will be saved to
	 * contact objects and to csv file
	 * 
	 * @param save
	 * @throws IOException
	 */
	public void save(Button save) throws IOException {

		docupath = "src/main/resources/telefonbuch.csv";
		file = new File(docupath);
		file.getParentFile().mkdirs();
		file.createNewFile();
//
		/**
		 * save to file and in contact objects
		 */
		save.setOnAction((event) -> {

			try {
				saveToFileAndObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

	/**
	 * refreshes input fields after clicking save button
	 * 
	 * @throws IOException
	 */
	public void refreshInput() throws IOException {
		firmaTextField.clear();
		vornameTextField.clear();
		nachnameTextField.clear();
		phoneTextField.clear();
		strassenTextField.clear();
		hausnummerTextField.clear();
		plzTextField.clear();
		stadtTextField.clear();
		for (TextField string : builder.getPhoneList()) {
			string.clear();
		}
		clearAdress();

		builder.getVboxList().clear();
		acc.getPanes().clear();
		acc.getPanes().addAll(tp, adressTitlePane);
		parseContacts("C://users/mbittermann/documents/telefonbuch.csv");

	}

	public static ArrayList<Contact> getContacts() {
		return contacts;
	}

//	public void setContacts(ArrayList<Contact> contacts) {
//		ContactDataHandler.contacts = contacts;
//	}

	/**
	 * reads the csv file at start of the program. saves the read data to contact
	 * objects.
	 * 
	 * @param pfad
	 * @return
	 * @throws FileNotFoundException
	 */
	public ArrayList<Contact> dataReader(String pfad) throws FileNotFoundException {
		Scanner inputStream = new Scanner(new File(pfad));
		Contact contact = null;
//		contacts = new ArrayList<>();
		inputStream.useDelimiter(";");
		inputStream.useDelimiter("|");
		while (inputStream.hasNext()) {
			String data = inputStream.nextLine();
			String[] newData = data.split(";");

			contact = new Contact();

			if (contact instanceof Contact) {

				String[] multiAdressen = newData[4].split("\\|");

				for (String string : multiAdressen) {

					String[] cut = string.split(",");
					contact.setNoCompany(newData[0]);
					contact.setVorname(newData[1]);
					contact.setNachname(newData[2]);
					if (3 < cut.length)
						contact.AddAdress(cut[0], cut[1], cut[2], cut[3]);
				}
				if (1 < newData[3].length())
					contact.addPhoneNumber(newData[3]);
				contacts.add(contact);
			}

		}
		inputStream.close();

		return contacts;

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Contact> parseContacts(String path) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(path));

		@SuppressWarnings("rawtypes")
		ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
		strategy.setType(Contact.class);
		String[] fields = { "noCompany", "vorname", "nachname", "phone", "adresse" };
		strategy.setColumnMapping(fields);

		@SuppressWarnings({ "rawtypes" })
		CsvToBean<Contact> csvToBean = new CsvToBeanBuilder(reader).withType(Contact.class)
				.withMappingStrategy(strategy).withSkipLines(1).withIgnoreLeadingWhiteSpace(true).withSeparator(';')
				.build();

		Iterator<Contact> userIterator = csvToBean.iterator();
		while (userIterator.hasNext()) {

			Contact user = userIterator.next();

			Contact contact = new Contact();
			contact.setNoCompany(user.getNoCompany());
			contact.setVorname(user.getVorname());
			contact.setNachname(user.getNachname());

			String[] fon = user.getPhone().split(",");

			// TODO
			contact.addPhoneNumber(fon[0]);
			String[] adr = user.getAdresse().split("\\|");
			for (String eine : adr) {
				String[] cut = eine.split(",");
				if (3 < cut.length)
					contact.AddAdress(cut[0], cut[1], cut[2], cut[3]);

			}

			System.out.println(user.getNoCompany() + " " + user.getVorname() + " " + user.getNachname() + " "
					+ user.getPhone() + " " + user.getAdresse());
			contacts.add(contact);

		}
		return contacts;
	}

	public void saveToFileAndObject() throws IOException {

		contacts.clear();
		String kontakt = "";
		contact = new Contact();

		contact.setNoCompany(firmaTextField.getText());

		contact.setVorname(vornameTextField.getText());
		contact.setNachname(nachnameTextField.getText());

		if (!getTextFromPhoneNumberList().isEmpty() || !phoneTextField.getText().isEmpty()) {

			for (String num : getTextFromPhoneNumberList()) {
				Phone phone = new Phone();
				phone.setPhoneNumber(num);

				contact.addPhone(phone);
			}
		}

		contact.addAddress(getTextFromAdressenList(builder.getAdressCotent()));
		for (VBox vboxValues : builder.getVboxList()) {
			contact.addAddress(getTextFromAdressenList(vboxValues));

		}

		if (vornameTextField.getText().isEmpty() || nachnameTextField.getText().isEmpty()) {
			alert.showAlert();

		} else {

			kontakt = contact.getNoCompany() + ";" + contact.getVorname() + ";" + contact.getNachname() + ";"
					+ contact.getPhoneNumbers() + ";" + contact.getAddressen();

			try {
				Files.write(Paths.get(docupath), (kontakt + "\r\n").getBytes(), StandardOpenOption.APPEND);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			contacts.add(contact);

			refreshInput();

//			builder.getVboxList().clear();
		}
	}

	public List<String> getTextFromPhoneNumberList() {
		List<String> nummers = new ArrayList<>();
		nummers.clear();
		nummers.add(phoneTextField.getText());
		for (TextField phoneNumbers : builder.getPhoneList()) {
			nummers.add(phoneNumbers.getText());
		}
		return nummers;
	}

	public Address getTextFromAdressenList(VBox addresslist) {

		Address adressen = new Address();

		adressen.setStrasse(((TextField) addresslist.getChildren().get(1)).getText());
		adressen.setHausnummer(((TextField) addresslist.getChildren().get(3)).getText());
		adressen.setPostleitzahl(((TextField) addresslist.getChildren().get(5)).getText());
		adressen.setStadt(((TextField) addresslist.getChildren().get(7)).getText());

		return adressen;
	}

	public String getNummer() {
		return nummer;
	}

	public void clearAdress() {
		for (VBox vbox : builder.getVboxList()) {
			if (vbox instanceof VBox) {
				VBox vb = (VBox) vbox;
				for (Node text : vb.getChildren()) {
					if (text instanceof TextField) {
						TextField tf = (TextField) text;
						tf.clear();

					}
				}
			}

			break;
		}
	}

}
