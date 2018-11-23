package de.eves.Telefonbuch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author mbittermann
 *
 */
public class CSVReader {

	public static ArrayList<Contact> contacts;

	/**
	 * This is to read out contacts from file
	 * 
	 * @param pfad
	 * @return
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Contact> readFile(String pfad) throws FileNotFoundException {

		Scanner inputStream = new Scanner(new File(pfad));
		Contact contact = null;
		CompanyContacts companyContact = null;
		contacts = new ArrayList<>();
		inputStream.useDelimiter(";");
		inputStream.useDelimiter("|");

		while (inputStream.hasNext()) {
			String data = inputStream.nextLine();
			String[] newData = data.split(";");

			contact = new Contact();
			companyContact = new CompanyContacts();

			if (contact instanceof Contact) {

				String[] multiAdressen = newData[4].split("\\|");

				for (String string : multiAdressen) {

					String[] cut = string.split(",");
					contact.setNoCompany(newData[0]);
					contact.setVorname(newData[1]);
					contact.setNachname(newData[2]);
					if (1 < cut.length)
						contact.AddAdress(cut[0], cut[1], cut[2], cut[3]);
				}

				contact.addPhoneNumber(newData[3]);
				contacts.add(contact);
			} else if (contact instanceof CompanyContacts) {
				String[] multiAdressen = newData[4].split("\\|");
				for (String string : multiAdressen) {
					String[] cut = string.split(",");
					companyContact.setFirma(newData[0]);
					companyContact.setVorname(newData[1]);
					companyContact.setNachname(newData[2]);
					if (1 < cut.length)
						companyContact.addAddress(cut[0], cut[1], cut[2], cut[3]);
				}
				companyContact.addPhoneNumbers(newData[3]);
				contacts.add(companyContact);
			}
		}
		inputStream.close();

		return contacts;

	}

	public static ArrayList<Contact> getContacts() {
		return contacts;
	}

	public static void setContacts(ArrayList<Contact> contacts) {
		CSVReader.contacts = contacts;
	}

	public ArrayList<Contact> readFile() throws FileNotFoundException {
		return readFile("Standartpfad");
	}

	/**
	 * This method is to auto fill data to contact object
	 * 
	 * @param newData
	 * @param contact
	 */
	public static void fillContact(String[] newData, Contact contact) {
		try {
			contact.addPhoneNumber(newData[2]);
//			contact.setTelefonnummer(newData[2]);

//			contact.AddAdress(strasse, hausnummer, postleitzahl, stadt);
//			contact.setStrasse(newData[3]);
//			contact.setHausnummer(newData[4]);
//			contact.setPostleitzahl(newData[5]);
//			contact.setStadt(newData[6]);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * This method is to search content of a contact with comparing search term with
	 * data in ArrayList contacts
	 * 
	 * @param searcher
	 * @param file
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public static void searchContacts(Scanner searcher, File file) throws FileNotFoundException {
		String searchContact;
		System.out.println();
		System.out.println("Geben sie einen Kontakt ein den sie suchen möchten..");

		searchContact = searcher.nextLine();
		searcher = new Scanner(file);
		searcher.useDelimiter(";");

		int[] lines = new int[5];
		try {
			lines = AddContact.laengsteWort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String matrix = "|%" + lines[0] + "s|%" + lines[1] + "s|%" + lines[2] + "s|%" + lines[3] + "s|%" + lines[4]
				+ "s|\n";
//				+ "%" + lines[4]
//				+ "s|%" + lines[5] + "s|%" + lines[6] + "s|\n";

		while (searcher.hasNext()) {
			String data = searcher.nextLine();

			if (data.toLowerCase().contains(searchContact.toLowerCase())) {
				String[] newData = data.split(";");
				System.out.print(String.format(matrix, newData[0], newData[1], newData[2], newData[3], newData[4]));

			}
		}
	}

}
