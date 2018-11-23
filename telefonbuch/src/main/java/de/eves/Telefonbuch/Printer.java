package de.eves.Telefonbuch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mbittermann
 *
 */
public class Printer {
	/**
	 * different printer can be called instead of the standart sysout output this
	 * printer gets an String array as parameter print to console
	 * 
	 * @param input
	 */
	public static void consolePrinter(String[] input) {

		int[] lines = new int[5];
		try {
			lines = AddContact.laengsteWort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String matrix = "|%" + lines[0] + "s|%" + lines[1] + "s|%" + lines[2] + "s|%" + lines[3] + "s|%" + lines[4]
				+ "s|\n";

		System.out.format(matrix, input[0], input[1], input[2], input[3], input[4]);
	}

	/**
	 * this is a printer which can get an list as parameter print to console
	 * 
	 * @param found
	 */
	public static void ConsolePrinter(List<Contact> found) {
		for (Contact contact : found) {
			ConsolePrinter(contact);
		}
	}

	/**
	 * printer that gets a contact object as parameter print to console
	 * 
	 * @param contact
	 */
	public static void ConsolePrinter(Contact contact) {
		ArrayList<String> mehrereStrings = new ArrayList<>();
		if (contact instanceof Contact) {
			mehrereStrings.add(contact.getNoCompany());
			mehrereStrings.add(contact.getVorname());
			mehrereStrings.add(contact.getNachname());
			mehrereStrings.add(contact.getPhoneNumbers());
			mehrereStrings.add(contact.getAddressen());
			String[] contactArray = new String[mehrereStrings.size()];
			contactArray = mehrereStrings.toArray(contactArray);
			consolePrinter(contactArray);

		}
//		} else if (contact instanceof CompanyContacts) {
//			mehrereStrings.add(((CompanyContacts) contact).getFirma());
//			mehrereStrings.add(contact.getVorname());
//			mehrereStrings.add(contact.getNachname());
//			mehrereStrings.add(contact.getPhoneNumbers());
//			mehrereStrings.add(contact.getAddressen());
//			String[] companyContactArray = new String[mehrereStrings.size()];
//			companyContactArray = mehrereStrings.toArray(companyContactArray);
//			consolePrinter(companyContactArray);
//		}
	}

}
