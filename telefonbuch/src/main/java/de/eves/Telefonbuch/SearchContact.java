package de.eves.Telefonbuch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author mbittermann
 *
 */
public class SearchContact {

	public static String searchContact;
	public static String matrix;

	@SuppressWarnings("resource")
	/**
	 * This method is used to search a contact in the csv table if the contact is
	 * found it will be printed to the console with informations
	 * 
	 * @param searcher
	 */
	public static void searchContacts(Scanner searcher, File file) {
		System.out.println("Geben sie einen Kontakt ein den sie suchen möchten..");

		try {
			searcher = new Scanner(System.in);
			searchContact = searcher.nextLine();

//			for (Contact contact : AddContact.getArray()) {
//				if (contact.toString().contains(searchContact)) {
//					System.out.println(contact);
//				}
//			}
			searcher = new Scanner(file);
			searcher.useDelimiter(";");
			int[] lines = new int[7];
			try {
				lines = AddContact.laengsteWort();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			matrix = "|%" + lines[0] + "s|%" + lines[1] + "s|%" + lines[2] + "s|%" + lines[3] + "s|%" + lines[4] + "s|%"
					+ lines[5] + "s|%" + lines[6] + "s\n";

			while (searcher.hasNext()) {

				String line = searcher.nextLine();
				String[] lang = line.split(";");
//				line += "|";
				if (line.toLowerCase().contains(searchContact.toLowerCase())) {
					System.out.print(
							String.format(matrix, lang[0], lang[1], lang[2], lang[3], lang[4], lang[5], lang[6]));

				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
