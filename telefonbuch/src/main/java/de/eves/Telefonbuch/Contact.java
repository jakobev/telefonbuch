package de.eves.Telefonbuch;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author mbittermann
 *
 */
@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private SimpleStringProperty noCompany = new SimpleStringProperty("");
	private SimpleStringProperty vorname = new SimpleStringProperty("");
	private SimpleStringProperty nachname = new SimpleStringProperty("");

	private ArrayList<Phone> phoneNumbers;

	public ArrayList<Address> adressen;
	private String adresse;
	private String phone;

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String kontakt;

	public Contact() {
		this.adressen = new ArrayList<Address>();
		this.phoneNumbers = new ArrayList<Phone>();
	}

	public Contact(String kontakt) {
		this.kontakt = kontakt;
		this.adressen = new ArrayList<Address>();
	}

	public Contact(String noCompany, String vorname, String nachname, String telefonnummer, String strasse,
			String hausnummer, String postleitzahl, String stadt) {
		this.noCompany.setValue(noCompany);
		this.vorname.setValue(vorname);
		this.nachname.setValue(nachname);
		Phone phonenumber = new Phone(telefonnummer);
		Address adresse = new Address(strasse, hausnummer, postleitzahl, stadt);
		this.adressen = new ArrayList<Address>();
		this.phoneNumbers = new ArrayList<Phone>();
		this.phoneNumbers.add(phonenumber);
		this.adressen.add(adresse);

	}

	public void setPhoneNumbers(ArrayList<Phone> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	//
	public void AddAdress(String strasse, String hausnummer, String postleitzahl, String stadt) {

		Address adresse = new Address(strasse, hausnummer, postleitzahl, stadt);
		this.adressen.add(adresse);
	}

	public void addPhoneNumber(String telefonnummer) {
		Phone phone = new Phone(telefonnummer);
		this.phoneNumbers.add(phone);
	}

	public String getVorname() {
		return vorname.getValue();
	}

	public void setVorname(String vorname) {
		this.vorname.setValue(vorname);
	}

	public String getNachname() {
		return nachname.getValue();
	}

	public void setNachname(String nachname) {
		this.nachname.setValue(nachname);
	}

	public String getAddressen() {

		String kontakt = "";
		for (Address address : this.adressen) {

			if (kontakt.isEmpty()) {
				kontakt = address.getStrasse() + "," + address.getHausnummer() + "," + address.getPostleitzahl() + ","
						+ address.getStadt();

			} else {
				kontakt += "|" + address.getStrasse() + "," + address.getHausnummer() + "," + address.getPostleitzahl()
						+ "," + address.getStadt();

			}

		}
		return kontakt;
	}

	public void addAddress(Address address) {
		this.adressen.add(address);
	}

	public void addPhone(Phone phone) {
		this.phoneNumbers.add(phone);
	}

	public String getPhoneNumbers() {
		String nummerBuilder = "";
		for (Phone phone : phoneNumbers) {
			if (nummerBuilder.isEmpty()) {
				nummerBuilder = phone.getPhoneNumber();
			} else {
				nummerBuilder += "," + phone.getPhoneNumber();
			}
		}
		return nummerBuilder;
	}

	public String getNoCompany() {
		return noCompany.getValue();
	}

	public void setNoCompany(String noCompany) {
		this.noCompany.setValue(noCompany);
	}

	public SimpleStringProperty vornameProperty() {
		return vorname;
	}

	public SimpleStringProperty nachnameProperty() {
		return nachname;
	}

	public SimpleStringProperty firmaProperty() {
		return noCompany;
	}

	/**
	 * 
	 * @param contact
	 * @return
	 */

	// added noCompany**************************************************
	@Override
	public String toString() {
		return noCompany + ";" + vorname + ";" + nachname + ";" + getPhoneNumbers() + ";"
				+ (getAddressen().equals("") ? " " : getAddressen());
	}

	public ArrayList<Address> getAdressen() {
		return adressen;
	}

	public void setAdressen(ArrayList<Address> adressen) {
		this.adressen = adressen;
	}

	public void setAdressenString(String adressen) {
		adresse = adressen;
	}

	public void setPhoneNumberString(String phones) {
		phone = phones;
	}

	public ArrayList<Phone> getPhoneNumberArray() {
		return phoneNumbers;
	}

}
