package de.eves.Telefonbuch;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author mbittermann
 *
 */
public class Main extends Application {

	private static final DesignBuilder builder = new DesignBuilder();

	/**
	 * This Method prints a Menu to the console and with the help of a scanner you
	 * can choose from the menu and use different functions
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		launch(Main.class, args);
		/**
		 * calls method from AddContact class
		 */

		AddContact addContact = new AddContact();
		addContact.addContactTo();

	}

	/**
	 * calls all needed methods
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		ContactDataHandler co = new ContactDataHandler(builder);

		
		co.parseContacts("src/main/resources/telefonbuch.csv");
		builder.buildDesign(primaryStage);
		co.save(builder.getSaveButton());

	}

	public static DesignBuilder getBuilder() {
		return builder;
	}

}
