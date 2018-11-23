package de.eves.Telefonbuch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * LÄUFT !!!
 * 
 * @author mbittermann
 *
 */
public class MultiFieldSearcher {

	static Scanner scanner = new Scanner(System.in);
	private static Contact contact;
	static List<Contact> gefundeneKontakte = new ArrayList<>();

	/**
	 * Shows the menu to the user. the if-block is to check which method is to
	 * execute. And the methods will add the found contacts to an array which will
	 * be print to console with format
	 * 
	 * @param scanner
	 * @throws FileNotFoundException
	 */
	public static void Menu(Scanner scanner) throws FileNotFoundException {

		System.out.println();
		System.out.println("Geben Sie hintereinander mit LEERZEICHEN die Nummern ein\n"
				+ "für die Feldern in denen Sie suchen möchten und drücken ENTER " + "auf der Tastatur ");
		System.out.println();
		System.out.println("0 Beende Programm");
		System.out.println("1 Vorname");
		System.out.println("2 Nachname");
		System.out.println("3 Telefon");
		System.out.println("4 Strasse");
		System.out.println("5 Hausnummer");
		System.out.println("6 PLZ");
		System.out.println("7 Stadt");
		System.out.println();

		String menu = scanner.nextLine();

		if (menu.contains("1"))
			searchForename(scanner);
		if (menu.contains("2"))
			searchSurname(scanner);
		if (menu.contains("3"))
			searchPhoneNumber(scanner);
		if (menu.contains("4"))
			searchStreets(scanner);
		if (menu.contains("5"))
			searchHouse(scanner);
		if (menu.contains("6"))
			searchZip(scanner);
		if (menu.contains("7"))
			searchStadt(scanner);

		/**
		 * Print List if list isnt empty
		 */

		if (!gefundeneKontakte.isEmpty()) {
			Printer.ConsolePrinter(gefundeneKontakte);

		} else {
			System.out.println("Die Kontakte konnten nicht gefunden oder ausgelesen werden !");
		}
	}

	/**
	 * Method that compares search term with field in file and prints founded data
	 * to console
	 * 
	 * @param scanner
	 * @param file
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public static void searchForename(Scanner scanner) throws FileNotFoundException {
		System.out.println();
		System.out.println("Geben Sie einen Vornamen ein, nachdem Sie suchen möchten");
		String forenameToFind = scanner.nextLine();
		int matches = 0;
		int[] lines = new int[5];
		try {
			lines = AddContact.laengsteWort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String matrix = "|%" + lines[0] + "s|%" + lines[1] + "s|%" + lines[2] + "s|%" + lines[3] + "s|%" + lines[4]
				+ "s|\n";
		for (Contact contact : CSVReader.contacts) {
			String[] contactString = contact.toString().split(";");
			if (contact.getVorname().toLowerCase().contains(forenameToFind.toLowerCase())) {
				gefundeneKontakte.remove(contact);
				gefundeneKontakte.add(contact);

				matches++;

			}
		}
		if (matches <= 0)
			System.out.println("Einen Kontakt mit diesem Vornamen ist nicht vorhanden");
	}

	/**
	 * search surname in contacts if user input is contained in contacts surnames it
	 * will print contact to console
	 * 
	 * @param scanner
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public static void searchSurname(Scanner scanner) throws FileNotFoundException {
		System.out.println();

		System.out.println("Geben Sie einen Nachnamen ein, nachdem Sie suchen möchten");
		String SurnameToFind = scanner.nextLine();
		int matches = 0;
		int[] lines = new int[5];
		try {
			lines = AddContact.laengsteWort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String matrix = "|%" + lines[0] + "s|%" + lines[1] + "s|%" + lines[2] + "s|%" + lines[3] + "s|%" + lines[4]
				+ "s|\n";
		for (Contact contact : CSVReader.contacts) {
			String[] contactString = contact.toString().split(";");
			if (contact.getNachname().toLowerCase().contains(SurnameToFind.toLowerCase())) {
				gefundeneKontakte.remove(contact);
				gefundeneKontakte.add(contact);

				matches++;

			}
		}
		if (matches <= 0)
			System.out.println("Einen Kontakt mit diesem Nachnamen ist nicht vorhanden");
	}

	/**
	 * search number in contacts if user input is contained in contacts phone
	 * numbers it will print contact to console
	 * 
	 * @param scanner
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public static void searchPhoneNumber(Scanner scanner) throws FileNotFoundException {
		System.out.println();
		System.out.println("Geben Sie eine Telefonnummer ein, nach der Sie suchen möchten");
		String searchNumber = scanner.nextLine();
		int matches = 0;
		int[] lines = new int[5];
		try {
			lines = AddContact.laengsteWort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String matrix = "|%" + lines[0] + "s|%" + lines[1] + "s|%" + lines[2] + "s|%" + lines[3] + "s|%" + lines[4]
				+ "s|\n";
		for (Contact contact : CSVReader.contacts) {
			String[] contactString = contact.toString().split(";");
			String[] phoneNummer = contact.getPhoneNumbers().split(",");
			for (String nummer : phoneNummer) {

				if (nummer.toLowerCase().contains(searchNumber.toLowerCase())) {
					gefundeneKontakte.remove(contact);
					gefundeneKontakte.add(contact);

				}
				matches++;
			}
		}
		if (matches <= 0)
			System.out.println("Einen Kontakt mit dieser Telefonnummer ist nicht vorhanden");
	}

	/**
	 * Search in Contact If a city entry contains users input it prints contact to
	 * console
	 * 
	 * @param scanner
	 */
	public static void searchStadt(Scanner scanner) {
		System.out.println();
		System.out.println("Geben Sie eine Stadt ein, nach der Sie suchen möchten");
		String findCity = scanner.nextLine();
		int matches = 0;
		int[] lines = new int[5];
		try {
			lines = AddContact.laengsteWort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String matrix = "|%" + lines[0] + "s|%" + lines[1] + "s|%" + lines[2] + "s|%" + lines[3] + "s|%" + lines[4]
				+ "s|\n";

		for (Contact contact : CSVReader.contacts) {
			for (Address address : contact.adressen) {

				String city = "";
				String[] contactString = contact.toString().split(";");

				if (address.getStadt().length() > 1)
					city = address.getStadt();

				if (city.toLowerCase().contains(findCity.toLowerCase())) {
					gefundeneKontakte.remove(contact);
					gefundeneKontakte.add(contact);

				}
			}

		}
	}

	/**
	 * Search in Contact If a housenumber entry contains users input it prints
	 * contact to console
	 * 
	 * @param scanner
	 */
	public static void searchHouse(Scanner scanner) {
		System.out.println();
		System.out.println("Geben Sie eine Hausnummer ein, nach der Sie suchen möchten");
		String findCity = scanner.nextLine();
		int matches = 0;
		int[] lines = new int[5];
		try {
			lines = AddContact.laengsteWort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String matrix = "|%" + lines[0] + "s|%" + lines[1] + "s|%" + lines[2] + "s|%" + lines[3] + "s|%" + lines[4]
				+ "s|\n";

		for (Contact contact : CSVReader.contacts) {
			for (Address address : contact.adressen) {

				String haus = "";
				String[] contactString = contact.toString().split(";");

				if (address.getHausnummer().length() > 1)
					haus = address.getHausnummer();

				if (haus.toLowerCase().contains(findCity.toLowerCase())) {
					gefundeneKontakte.remove(contact);
					gefundeneKontakte.add(contact);

				}
			}

		}
	}

	/**
	 * Search in Contact If a street entry contains users input it prints contact to
	 * console
	 * 
	 * @param scanner
	 */
	public static void searchStreets(Scanner scanner) {
		System.out.println();
		System.out.println("Geben Sie eine Strasse ein, nach der Sie suchen möchten");
		String findCity = scanner.nextLine();
		int matches = 0;
		int[] lines = new int[5];
		try {
			lines = AddContact.laengsteWort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String matrix = "|%" + lines[0] + "s|%" + lines[1] + "s|%" + lines[2] + "s|%" + lines[3] + "s|%" + lines[4]
				+ "s|\n";

		for (Contact contact : CSVReader.contacts) {
			for (Address address : contact.adressen) {

				String strasse = "";
				String[] contactString = contact.toString().split(";");

				if (address.getStrasse().length() > 1)
					strasse = address.getStrasse();

				if (strasse.toLowerCase().contains(findCity.toLowerCase())) {
					gefundeneKontakte.remove(contact);
					gefundeneKontakte.add(contact);

				}
			}

		}
	}

	/**
	 * Search in Contact If a zip entry contains users input it prints contact to
	 * console
	 * 
	 * @param scanner
	 */
	public static void searchZip(Scanner scanner) {
		System.out.println();
		System.out.println("Geben Sie eine Postleitzahl ein, nach der Sie suchen möchten");
		String findCity = scanner.nextLine();
		int matches = 0;
		int[] lines = new int[5];
		try {
			lines = AddContact.laengsteWort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String matrix = "|%" + lines[0] + "s|%" + lines[1] + "s|%" + lines[2] + "s|%" + lines[3] + "s|%" + lines[4]
				+ "s|\n";

		for (Contact contact : CSVReader.contacts) {
			for (Address address : contact.adressen) {

				String plz = "";
				String[] contactString = contact.toString().split(";");

				if (address.getPostleitzahl().length() > 1)
					plz = address.getPostleitzahl();

				if (plz.toLowerCase().contains(findCity.toLowerCase())) {
					gefundeneKontakte.remove(contact);
					gefundeneKontakte.add(contact);

				}
			}

		}
	}

	/**
	 * AND-Search it compares input with content from contactlist and if each input
	 * is contained in contactlist it will print the contact
	 * 
	 * @param scanner
	 * @return
	 */
	public static ArrayList<Contact> searchMethod(Scanner scanner) {
		System.out.println();
		System.out.println("Suchmöglichkeiten");
		System.out.println();
		System.out.println("Vorname - Nachname - Telefonnummer - Adresse");
		System.out.println();
		System.out.println("Bitte geben Sie hintereinander in der richtigen Reihenfolge Ihre Suchbegriffe ein");
		System.out.println();
		System.out.println(
				"Also erst einen Vornamen, dann einen Nachnamen, dann die Telefonnummer und als letztes Addresse");
		System.out
				.println("Wenn Sie für ein Feld keine Daten zur Verfügung haben lassen Sie es leer und drücken Enter");
		System.out.println("um zur nächsten Eingabe zu kommen");

		System.out.println();
		System.out.println(
				"Es werden Ihnen nur Kontakte angezeigt, bei denen die Kontaktdaten einer Person mit Ihrer Eingabe übereinstimmt");
		System.out.println();
		System.out.println("Bitte geben Sie einen Vornamen ein");
		System.out.println("Wenn Sie keinen haben drücken Sie enter");

		String input = scanner.nextLine();
		System.out.println();
		System.out.println("Bitte geben Sie einen Nachnamen ein");
		System.out.println("Wenn Sie keinen haben drücken Sie enter");
		String inputnach = scanner.nextLine();

		System.out.println();
		System.out.println("Bitte geben Sie eine Telefonummer ein");
		System.out.println("Wenn Sie keine haben drücken Sie enter");
		String inputphone = scanner.nextLine();
		System.out.println();
		System.out.println("Bitte geben Sie eine Addresse ein");
		System.out.println("Wenn Sie keine haben drücken Sie enter");
		System.out.println();
		String inputadresse = scanner.nextLine();

		ArrayList<Contact> result = new ArrayList<Contact>();
		for (Contact contact : CSVReader.contacts) {

			boolean matched = false;

			if ((null != contact.getVorname().toLowerCase()
					&& contact.getVorname().toLowerCase().contains(input.toLowerCase())) || input.isEmpty()) {

				if ((null != contact.getNachname().toLowerCase()
						&& contact.getNachname().toLowerCase().contains(inputnach.toLowerCase()))
						|| inputnach.isEmpty()) {

					if ((null != contact.getPhoneNumbers().toLowerCase()
							&& contact.getPhoneNumbers().toLowerCase().contains(inputphone.toLowerCase()))
							|| inputphone.isEmpty()) {

						if ((null != contact.getAddressen().toLowerCase()
								&& contact.getAddressen().toLowerCase().contains(inputadresse.toLowerCase()))
								|| inputadresse.isEmpty()) {
							matched = true;

						}
					}
				}

			} else {
				matched = false;

			}
			if (!result.contains(contact) && matched)
				result.add(contact);

		}
		if (!result.isEmpty()) {
			Printer.ConsolePrinter(result);
		} else {
			System.out.println();
			System.out.println("Kontakteingaben stimmen nicht überein");
		}
		return result;
	}

}
