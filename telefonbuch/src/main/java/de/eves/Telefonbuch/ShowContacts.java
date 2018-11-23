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
public class ShowContacts {

	/**
	 * This method is used to print all of the contacts in csv file to the console
	 * Gets a scanner to read the file Data is the content of the read csv file
	 * while the scanner read the file and there is text it prints all of content in
	 * a table
	 * 
	 * @param inputStream
	 * @throws FileNotFoundException
	 */
	public static void showContacts(Scanner inputStream, File file) throws FileNotFoundException {

		inputStream = new Scanner(file);
		inputStream.useDelimiter(";");
		int[] laenge = new int[5];
		try {
			laenge = AddContact.laengsteWort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String matrix = "|%" + laenge[0] + "s|%" + laenge[1] + "s|%" + laenge[2] + "s|%" + laenge[3] + "s|%" + laenge[4]
				+ "s|\n";
//		+ "s|%" + laenge[4]
//				+ "s|%" + laenge[5] + "s|%" + laenge[6] + "s\n";

		while (inputStream.hasNext()) {
			String data = inputStream.nextLine();
			String[] newData = data.split(";");
			System.out.print(String.format(matrix, newData[0], newData[1], newData[2], newData[3], newData[4]));
//					, newData[4],
//					newData[5], newData[6]));
		}
		inputStream.close();
	}
}
