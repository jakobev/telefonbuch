package de.eves.Telefonbuch;

import javafx.beans.property.SimpleStringProperty;

public class Address {

	private SimpleStringProperty hausnummer = new SimpleStringProperty("");
	private SimpleStringProperty stadt = new SimpleStringProperty("");
	private SimpleStringProperty postleitzahl = new SimpleStringProperty("");
	private SimpleStringProperty strasse = new SimpleStringProperty("");
	private String adresse;

	public Address(String strasse, String hausnummer, String postleitzahl, String stadt) {

		this.strasse.setValue(strasse);
		this.hausnummer.setValue(hausnummer);
		this.postleitzahl.setValue(postleitzahl);
		this.stadt.setValue(stadt);

	}

	public Address() {

	}

	public Address(String adresse) {
		this.adresse = adresse;
	}

	public String getHausnummer() {
		return hausnummer.getValue();
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer.setValue(hausnummer);
	}

	public SimpleStringProperty getHausnummerProp() {
		return hausnummer;
	}

	public String getStadt() {
		return stadt.getValue();
	}

	public void setStadt(String stadt) {
		this.stadt.setValue(stadt);
	}

	public SimpleStringProperty getStadtProp() {
		return stadt;
	}

	public String getPostleitzahl() {
		return postleitzahl.getValue();
	}

	public void setPostleitzahl(String postleitzahl) {
		this.postleitzahl.setValue(postleitzahl);
	}

	public SimpleStringProperty getPostleitzahlProp() {
		return postleitzahl;
	}

	public String getStrasse() {
		return strasse.getValue();
	}

	public SimpleStringProperty getStrasseProp() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse.setValue(strasse);
	}
}