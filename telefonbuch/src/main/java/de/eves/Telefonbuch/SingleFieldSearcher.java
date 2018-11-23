package de.eves.Telefonbuch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author mbittermann
 *
 */
public class SingleFieldSearcher {

	static Scanner scanner = new Scanner(System.in);

	/**
	 * Method that shows a menu to user where he/she can choose between different
	 * search fields. After that the search term calls specific method
	 * 
	 * @param contact
	 * @param file
	 * @throws FileNotFoundException
	 */
	public static void singleFiledSearch(Scanner scanner) throws FileNotFoundException {

		System.out.println();
		System.out.println("Worin möchten Sie suchen ?\n");
		System.out.println("****Drücken Sie die gewünschte Nummer****");
		System.out.println("1 Vorname");
		System.out.println("2 Nachname");
		System.out.println("3 Telefon");
		System.out.println("4 Strasse");
		System.out.println("5 Hausnummer");
		System.out.println("6 PLZ");
		System.out.println("7 Stadt");
		System.out.println();

		String menu = scanner.nextLine();

		String[] menuEintrag = menu.split("");

		switch (menuEintrag[0]) {

		case "1":
			searchForename(scanner);
			break;
		case "2":
			searchSurname(scanner);
			break;
		case "3":
			searchPhoneNumber(scanner);
			break;
		case "4":
			searchStreets(scanner);
			break;
		case "5":
			searchHouse(scanner);
			break;
		case "6":
			searchZip(scanner);
			break;
		case "7":
			searchStadt(scanner);
			break;
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
				System.out.print(String.format(matrix, contactString[0], contactString[1], contactString[2],
						contactString[3], contactString[4]));

			}
		}

	}

	@SuppressWarnings("resource")
	public static void searchSurname(Scanner scanner) throws FileNotFoundException {
		System.out.println();
		System.out.println("Geben Sie einen Nachnamen ein, nachdem Sie suchen möchten");
		String SurnameToFind = scanner.nextLine();

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
				System.out.print(String.format(matrix, contactString[0], contactString[1], contactString[2],
						contactString[3], contactString[4]));

			}
		}

	}

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
					System.out.print(String.format(matrix, contactString[0], contactString[1], contactString[2],
							contactString[3], contactString[4]));
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
//				String[] kontaktAdresse = contact.getAddressen().split(",");
				;

				if (address.getStadt().length() > 1)
					city = address.getStadt();

				if (city.toLowerCase().contains(findCity.toLowerCase())) {
					System.out.print(String.format(matrix, contactString[0], contactString[1], contactString[2],
							contactString[3], contactString[4]));
					break;
//					matches++;

				}
			}
//			if (matches <= 0)
//				System.out.println("Einen Kontakt, der dieser Stadt zugehörig ist, ist nicht vorhanden");
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
//				String[] kontaktAdresse = contact.getAddressen().split(",");
				;

				if (address.getHausnummer().length() > 1)
					haus = address.getHausnummer();

				if (haus.toLowerCase().contains(findCity.toLowerCase())) {
					System.out.print(String.format(matrix, contactString[0], contactString[1], contactString[2],
							contactString[3], contactString[4]));

					break;

				}
			}
//		
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
//				String[] kontaktAdresse = contact.getAddressen().split(",");
				;

				if (address.getStrasse().length() > 1)
					strasse = address.getStrasse();

				if (strasse.toLowerCase().contains(findCity.toLowerCase())) {
					System.out.print(String.format(matrix, contactString[0], contactString[1], contactString[2],
							contactString[3], contactString[4]));

					break;

				}
			}
//		
		}
	}

	/**
	 * Search in Contact If a zip entry contains users input it prints contact to
	 * console
	 * 
	 * @param scanner
	 */
	public static void searchZip(Scanner scanenr) {
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

				;

				if (address.getPostleitzahl().length() > 1)
					plz = address.getPostleitzahl();

				if (plz.toLowerCase().contains(findCity.toLowerCase())) {
					System.out.print(String.format(matrix, contactString[0], contactString[1], contactString[2],
							contactString[3], contactString[4]));

					break;

				}
			}
//		
		}
	}
}
