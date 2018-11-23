package de.eves.Telefonbuch;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author mbittermann
 *
 */
public class Phone {

	private SimpleStringProperty telefonnummer = new SimpleStringProperty("");

	public Phone() {

	}

	public Phone(String telefonnummer) {
		this.telefonnummer.setValue(telefonnummer);
	}

	public String getPhoneNumber() {
		return telefonnummer.getValue();
	}

	public void setPhoneNumber(String telefonnummer) {
		this.telefonnummer.setValue(telefonnummer);
	}

	public SimpleStringProperty getTeleNr() {
		return telefonnummer;
	}
}
