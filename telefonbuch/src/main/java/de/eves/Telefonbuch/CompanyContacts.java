package de.eves.Telefonbuch;

import java.util.ArrayList;

public class CompanyContacts extends Contact {

	private String vorname;
	private String nachname;
	private String firma;
	public ArrayList<Address> address = new ArrayList<>();
	public ArrayList<Phone> phoneNumbers = new ArrayList<>();
	private String companyContact;

	public CompanyContacts() {
		this.address = new ArrayList<Address>();
		this.phoneNumbers = new ArrayList<Phone>();

	}

	public CompanyContacts(String companyContact) {
		this.companyContact = companyContact;
		this.address = new ArrayList<Address>();
	}

	public CompanyContacts(String firma, String telefon, String strasse, String hausnummer, String plz, String stadt) {
		this.firma = firma;

		Phone phoneNumber = new Phone(telefon);
		Address address = new Address(strasse, hausnummer, plz, stadt);
		this.address = new ArrayList<Address>();
		this.phoneNumbers = new ArrayList<Phone>();
		this.address.add(address);
		this.phoneNumbers.add(phoneNumber);

	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public String getCompanyVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getCompanyNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public ArrayList<Address> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<Address> address) {
		this.address = address;
	}

//	public ArrayList<Phone> getPhoneNumbers() {
//		return phoneNumbers;
//	}

	public void setPhoneNumbers(ArrayList<Phone> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public void addPhone(Phone phone) {
		this.phoneNumbers.add(phone);
	}

	public String getCompanyContact() {
		return companyContact;
	}

	public void addAdresssen(Address adress) {
		this.address.add(adress);
	}

	public void setCompanyContact(String companyContact) {
		this.companyContact = companyContact;
	}

	public void addAddress(String strasse, String hausnummer, String plz, String stadt) {
		Address address = new Address(strasse, hausnummer, plz, stadt);
		this.address.add(address);
	}

	public void addPhoneNumbers(String telefon) {
		Phone phoneNumber = new Phone(telefon);
		this.phoneNumbers.add(phoneNumber);
	}

	public String getAddresses() {

		String companyContakt = "";
		for (Address address2 : this.address) {

			if (companyContakt.isEmpty()) {
				companyContakt = address2.getStrasse() + "," + address2.getHausnummer() + ","
						+ address2.getPostleitzahl() + "," + address2.getStadt();

			} else {
				companyContakt += "|" + address2.getStrasse() + "," + address2.getHausnummer() + ","
						+ address2.getPostleitzahl() + "," + address2.getStadt();
			}
		}
		return companyContakt;
	}

	public String getPhoneNumbers() {
		String companyPhone = "";
		for (Phone phone : phoneNumbers) {
			if (companyPhone.isEmpty()) {
				companyPhone = phone.getPhoneNumber();
			} else {
				companyPhone += "," + phone.getPhoneNumber();
			}
		}
		return companyPhone;
	}

//ADDED FIRMA ************************************************************************
	@Override
	public String toString() {
		return firma + ";" + vorname + ";" + nachname + ";" + getPhoneNumbers() + ";"
				+ (getAddresses().equals("") ? " " : getAddresses());
	}
}
