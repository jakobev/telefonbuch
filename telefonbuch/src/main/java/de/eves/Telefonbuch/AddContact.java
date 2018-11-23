package de.eves.Telefonbuch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author mbittermann
 *
 */
public class AddContact extends Main {

	private static Scanner scanner;
	private static String[] auswahl;
	private static String path;
	private static String name;
	private static File file;
	private static String nummer;
	private static Contact contact = new Contact();
	private static String search;
	private static String vorname;
	private static String kontakt = "";
	private static List<String> fileString = new ArrayList<>();
	private static ArrayList<Contact> contacts;
	private CompanyContacts companyContact = new CompanyContacts();
	private ArrayList<CompanyContacts> companyContacts = new ArrayList<>();

	/**
	 * Method to addContacts to CSV File
	 * 
	 * @throws IOException
	 */
	public void addContactTo() throws IOException {

		contacts = new ArrayList<>();
		// Schleife um das Menu kontinuierlich anzuzeigen
		while (true) {
			scanner = new Scanner(System.in);
			System.out.println();
			System.out.println("Drücken Sie eine Nummer auf der Tastatur und danach Enter für die Menüpunktauswahl !");
			System.out.println("0 Beende Programm");
			System.out.println("1 Einen Kontakt erstellen");
			System.out.println("2 Alle Kontakte ausgeben");
			System.out.println("3 Suche");
			System.out.println("4 Einzelfeldsuche");
			System.out.println("5 Mehrfeldsuche (ODER-Suche)");
			System.out.println("6 Mehrfeldsuche (UND-Suche)");
			System.out.println();

			String eingabe = scanner.nextLine();
			auswahl = eingabe.split(" ");
			path = "C://users/mbittermann/test.csv";
			file = new File(path);
			file.getParentFile().mkdirs();
			file.createNewFile();

			switch (auswahl[0]) {
			case "0":
				scanner.close();
				System.exit(0);
				break;
			case "1":
				addContactOrFirm(scanner);
//				addContact(scanner);
				break;
			case "2":
				ShowContacts.showContacts(scanner, file);
				break;
			case "3":
				CSVReader.searchContacts(scanner, file);
				break;
			case "4":
				SingleFieldSearcher.singleFiledSearch(scanner);
				break;
			case "5":
				MultiFieldSearcher.Menu(scanner);
				break;
			case "6":
				MultiFieldSearcher.searchMethod(scanner);
				break;
			default:
				System.out.println(
						"Der eingegebene Menüpunkt ist nicht vorhanden\n,wählen Sie bitte erneut einen Menüpunkt aus\n");
				break;
			}

		}

	}

	/**
	 * This method is used to add a complete new Contact using following methods and
	 * the scanner is passed as parameter
	 * 
	 * @param scanner
	 * @throws IOException
	 */
	public void addContact(Scanner scanner) throws IOException {
		addName(scanner);
		addVorname(scanner);
		addNumber(scanner);
		addAdress(scanner);

		String kontakt = "";

		kontakt = contact.getNoCompany() + ";" + contact.getVorname() + ";" + contact.getNachname() + ";"
				+ contact.getPhoneNumbers() + ";" + contact.getAddressen();

		try {
			Files.write(Paths.get(path), (kontakt + "\n").getBytes(), StandardOpenOption.APPEND);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CSVReader.readFile("C:/users/mbittermann/test.csv");

	}

	/**
	 * This method is used to add a new Contacts number Scanner is passed as
	 * parameter the global string variable nummer is given the input of user
	 * 
	 * @param scanner
	 */
	public void addNumber(Scanner scanner) {
		while (true) {
			Phone phone = new Phone();
			System.out.println();
			System.out.println("Geben Sie eine Telefonnummer ein");
			phone.setPhoneNumber(scanner.nextLine());
			contact.addPhone(phone);
			System.out.println();
			System.out.println("Möchten Sie noch eine Telefonnummer hinzufügen ?");
			System.out.println("1 Ja");
			System.out.println("2 Nein");
			String input = scanner.nextLine();
			if (!input.equals("1"))
				break;

		}
//		
	}

	/**
	 * This method is used to add address to a contact(street, house number, city)
	 * the local variables strasse ,hausnummer and stadt are given the input of user
	 * The string kontakt adds all informations together as string finally the whole
	 * contact will be saved to csv file
	 * 
	 * @param scanner
	 */
	public void addAdress(Scanner scanner) {

		while (true) {
			Address adresse = new Address();
			System.out.println();
			System.out.println("Geben Sie eine Strasse ein");
			System.out.println("Bitte nur Buchstaben von a-Z und falls nötig einen Bindestrich");
			System.out.println();
			scanner.useDelimiter("([a-z][A-Z]-)");
			adresse.setStrasse(scanner.nextLine());
			System.out.println();
			System.out.println("Geben Sie eine Hausnummer ein");

			adresse.setHausnummer(scanner.nextLine());
			System.out.println();
			System.out.println("Geben Sie eine Postleitzahl ein");
			System.out.println("Bitte nur Zahlen von 0-9 verwenden");
			System.out.println();
			scanner.useDelimiter("([0-9])");
			adresse.setPostleitzahl(scanner.nextLine());
			System.out.println();
			System.out.println("Geben Sie eine Stadt ein");
			System.out.println("Bitte nur Buchstaben von a-Z und falls nötig einen Bindestrich");
			System.out.println();
			scanner.useDelimiter("([a-z][A-Z]-)");
			adresse.setStadt(scanner.nextLine());

			contact.addAddress(adresse);
			System.out.println();
			System.out.println("Möchten Sie noch eine Adresse hinzufügen?");
			System.out.println("1 Ja");
			System.out.println("2 Nein");
			String input = scanner.nextLine();
			if (!input.equals("1"))
				break;

		}

	}

	/**
	 * This method is used to get the longest String of the array
	 * 
	 * @return
	 * @throws IOException
	 */
	public static int[] laengsteWort() throws IOException {
		Scanner inputStream = new Scanner(file);
		int[] laenge = new int[7];
		while (inputStream.hasNext()) {
			String[] splitted = inputStream.nextLine().split(";");
			for (int i = 0; i < splitted.length; i++) {
				if (laenge[i] < splitted[i].length()) {
					laenge[i] = splitted[i].length();
				}
			}
		}
		return laenge;
	}

	/**
	 * This method is used to add forename to contact
	 * 
	 * @param scanner
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public void addVorname(Scanner scanner) throws IOException {
		System.out.println("Geben Sie einen Vornamen ein");
		System.out.println("Bitte nur Buchstaben von a-Z und bei zweitnamen einen Bindestrich verwenden");
		System.out.println();
		scanner.useDelimiter("([a-z][A-Z]-)");
//		Scanner fileScanner = new Scanner(file);
//		fileString = Files.readAllLines(Paths.get(path));
		fileString.addAll(Files.readAllLines(Paths.get(path)));

//		String fileInput = fileScanner.nextLine();
		vorname = scanner.nextLine();
		contact.setVorname(vorname);

		while (contact.getVorname().equals("") || fileString.contains(contact.getVorname())) {

			System.out.println("Sie müssen einen Vornamen eingeben");
			vorname = scanner.nextLine();
			contact.setVorname(vorname);
		}
		contact.setVorname(vorname);
//		vorname += ";";
	}

	/**
	 * This method is used to add surname to contact
	 * 
	 * @param scanner
	 */
	public void addName(Scanner scanner) {
		System.out.println("Geben Sie einen Nachnamen ein");
		System.out.println("Bitte nur Buchstaben von a-Z und bei zweitnamen einen Bindestrich verwenden");
		System.out.println();
		scanner.useDelimiter("([a-z][A-Z]-)");
		name = scanner.nextLine();
		contact.setNachname(name);
		while (contact.getNachname().equals("")) {
			System.out.println("Sie müssen einen Nachnamen eingeben");
			name = scanner.nextLine();
			contact.setNachname(name);
		}
		contact.setNachname(name);
//		name += ";";
	}

	public static String getKontakt() {
		return kontakt;
	}

	public void addContactToArray(Contact c) {
		contacts.add(c);
	}

	public static ArrayList<Contact> getArray() {
		return contacts;
	}

	public void addContactOrFirm(Scanner scanner) throws IOException {
		System.out.println();
		System.out.println("Wollen Sie einen Personenkontakt oder einen Firmenkontakt erstellen?");
		System.out.println();
		System.out.println("1 Personenkontakt");
		System.out.println("2 Firmenkontakt");

		String input = scanner.nextLine();
		String[] inputChoice = input.split("");

		switch (inputChoice[0]) {
		case "1":
			addContact(scanner);
			break;
		case "2":
			addCompanyContact(scanner);
			break;
		default:
			System.out.println("Das war Mist BROO");
		}
	}

	public void addCompanyContact(Scanner scanner) throws IOException {
		addCompanyName(scanner);
		addContactPersonName(scanner);
		addContactPersonVorname(scanner);
		addCompanyNumber(scanner);
		addCompanyAdress(scanner);

		String kontakt = "";

		kontakt = companyContact.getFirma() + ";" + companyContact.getCompanyVorname() + ";"
				+ companyContact.getCompanyNachname() + ";" + companyContact.getPhoneNumbers() + ";"
				+ companyContact.getAddresses();

		try {
			Files.write(Paths.get(path), (kontakt + "\n").getBytes(), StandardOpenOption.APPEND);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CSVReader.readFile("C:/users/mbittermann/test.csv");

	}

	public void addCompanyName(Scanner scanner) {
		System.out.println("Bitte beben Sie einen Firmennamen ein");

		name = scanner.nextLine();
		companyContact.setFirma(name);

		while (companyContact.getFirma().equals("")) {
			System.out.println("Sie müssen einen Firmennamen eingeben");
			name = scanner.nextLine();
			companyContact.setFirma(name);
		}
		companyContact.setFirma(name);
	}

	public void addCompanyAdress(Scanner scanner) {

		while (true) {
			Address adresse = new Address();
			System.out.println();
			System.out.println("Geben Sie eine Strasse ein");
			System.out.println("Bitte nur Buchstaben von a-Z und falls nötig einen Bindestrich");
			System.out.println();
			scanner.useDelimiter("([a-z][A-Z]-)");
			adresse.setStrasse(scanner.nextLine());
			System.out.println();
			System.out.println("Geben Sie eine Hausnummer ein");
			System.out.println("Zum Beispiel eine Zahl oder eine kombination aus Nummer und Zahl");
			System.out.println();
			scanner.useDelimiter("([a-z][A-Z][0-9])");
			adresse.setHausnummer(scanner.nextLine());
			System.out.println();
			System.out.println("Geben Sie eine Postleitzahl ein");
			System.out.println("Bitte nur Zahlen von 0-9 verwenden");
			System.out.println();
			scanner.useDelimiter("([0-9])");
			adresse.setPostleitzahl(scanner.nextLine());
			System.out.println();
			System.out.println("Geben Sie eine Stadt ein");
			System.out.println("Bitte nur Buchstaben von a-Z verwenden");
			System.out.println();
			scanner.useDelimiter("([a-z][A-Z])");
			adresse.setStadt(scanner.nextLine());
			companyContact.addAdresssen(adresse);
			System.out.println("Möchten Sie noch eine Adresse hinzufügen?");
			System.out.println("1 Ja");
			System.out.println("2 Nein");
			String input = scanner.nextLine();
			if (!input.equals("1"))
				break;

		}

	}

	public void addCompanyNumber(Scanner scanner) {
		while (true) {
			Phone phone = new Phone();
			System.out.println();
			System.out.println("Geben Sie eine Telefonnummer ein");
			System.out.println("Bitte nur Zahlen von 0-9 und dem Zeichen '+'");
			System.out.println();
			scanner.useDelimiter("([0-9]\\+)");
			phone.setPhoneNumber(scanner.nextLine());
			companyContact.addPhone(phone);
			System.out.println();
			System.out.println("Möchten Sie noch eine Telefonnummer hinzufügen ?");
			System.out.println("1 Ja");
			System.out.println("2 Nein");
			String input = scanner.nextLine();
			if (!input.equals("1"))
				break;

		}

	}

	public void addContactPersonName(Scanner scanner) {
		System.out.println();
		System.out.println("Geben Sie einen Nachnamen ein");
		System.out.println("Bitte nur Buchstaben von a-Z und bei zweitnamen einen Bindestrich verwenden");
		System.out.println();
		scanner.useDelimiter("([a-z][A-Z]-)");
		name = scanner.nextLine();
		companyContact.setNachname(name);
		while (companyContact.getNachname().equals("")) {
			System.out.println("Sie müssen einen Nachnamen eingeben");
			name = scanner.nextLine();
			companyContact.setNachname(name);
		}
		companyContact.setNachname(name);

	}

	public void addContactPersonVorname(Scanner scanner) throws IOException {
		System.out.println();
		System.out.println("Geben Sie einen Vornamen ein");
		System.out.println("Bitte nur Buchstaben von a-Z und bei Zweitnamen einen Bindestrich verwenden");
		System.out.println();
		scanner.useDelimiter("([a-z][A-Z]-)");
		vorname = scanner.nextLine();
		companyContact.setVorname(vorname);

		while (companyContact.getVorname().equals("")) {

			System.out.println("Sie müssen einen Vornamen eingeben");
			vorname = scanner.nextLine();
			companyContact.setVorname(vorname);
		}
		companyContact.setVorname(vorname);

	}
}
