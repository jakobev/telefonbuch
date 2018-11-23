package de.eves.Telefonbuch;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contacts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	private String vorname;
	private String nachname;
	private String noCompany;
	private ArrayList<Phone> phoneNumbers;
	public ArrayList<Address> adressen;
	private String adresse;
	private String phone;
	private String kontakt;

	public Contacts() {
		super();
	}

	public Contacts(String kontakt) {
		this.kontakt = kontakt;
		this.adressen = new ArrayList<Address>();
		this.phoneNumbers = new ArrayList<Phone>();
	}

	public Contacts(String noCompany, String vorname, String nachname, String telefon, String strasse,
			String hausnummer, String plz, String stadt) {
		super();
		this.noCompany = noCompany;
		this.vorname = vorname;
		this.nachname = nachname;
		Phone phone = new Phone(telefon);
		Address address = new Address(strasse, hausnummer, plz, stadt);
		this.phoneNumbers = new ArrayList<Phone>();
		this.adressen = new ArrayList<Address>();
		this.phoneNumbers.add(phone);
		this.adressen.add(address);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adressen == null) ? 0 : adressen.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((kontakt == null) ? 0 : kontakt.hashCode());
		result = prime * result + ((nachname == null) ? 0 : nachname.hashCode());
		result = prime * result + ((noCompany == null) ? 0 : noCompany.hashCode());
		result = prime * result + ((phoneNumbers == null) ? 0 : phoneNumbers.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacts other = (Contacts) obj;
		if (adressen == null) {
			if (other.adressen != null)
				return false;
		} else if (!adressen.equals(other.adressen))
			return false;
		if (id != other.id)
			return false;
		if (kontakt == null) {
			if (other.kontakt != null)
				return false;
		} else if (!kontakt.equals(other.kontakt))
			return false;
		if (nachname == null) {
			if (other.nachname != null)
				return false;
		} else if (!nachname.equals(other.nachname))
			return false;
		if (noCompany == null) {
			if (other.noCompany != null)
				return false;
		} else if (!noCompany.equals(other.noCompany))
			return false;
		if (phoneNumbers == null) {
			if (other.phoneNumbers != null)
				return false;
		} else if (!phoneNumbers.equals(other.phoneNumbers))
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		} else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getNoCompany() {
		return noCompany;
	}

	public void setNoCompany(String noCompany) {
		this.noCompany = noCompany;
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

	public ArrayList<Phone> getPhoneNumber() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(ArrayList<Phone> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public ArrayList<Address> getAdressen() {
		return adressen;
	}

	public void setAdressen(ArrayList<Address> adressen) {
		this.adressen = adressen;
	}

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

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	@Override
	public String toString() {
		return noCompany + ";" + vorname + ";" + nachname + ";" + getPhoneNumbers() + ";"
				+ (getAddressen().equals("") ? " " : getAddressen());
	}

	public void AddAdress(String strasse, String hausnummer, String postleitzahl, String stadt) {

		Address adresse = new Address(strasse, hausnummer, postleitzahl, stadt);
		this.adressen.add(adresse);
	}

	public void addPhoneNumber(String telefonnummer) {
		Phone phone = new Phone(telefonnummer);
		this.phoneNumbers.add(phone);
	}

}
