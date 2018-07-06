package fr.competitor.ws.contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import fr.competitor.model.Contact;
import fr.competitor.ws.BddObject;

public class ContactBDD extends BddObject {
	/**
	 * Fonction qui récupère le contact spécifié
	 * 
	 * @param id
	 * @return Contact
	 */
	public static Contact getContact(int id) {
		initBdd();
		try {
			Statement statementContact = (Statement) bdd.createStatement();
			ResultSet resultsetContact = statementContact
					.executeQuery("SELECT lastName, firstName, eMail, phone, address, postalCode, city, country "
							+ "FROM contact WHERE id_contact = " + id);
			resultsetContact.next();
			Contact contact = new Contact(id,
					resultsetContact.getString("address"),
					resultsetContact.getString("postalCode"),
					resultsetContact.getString("city"),
					resultsetContact.getString("country"),
					resultsetContact.getString("lastName"),
					resultsetContact.getString("firstName"),
					resultsetContact.getString("eMail"),
					resultsetContact.getString("phone"));
			return contact;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * Fonction qui récupère la liste des contacts
	 * 
	 * @return Liste de contacts
	 */
	public static ArrayList<Contact> getContacts() {
		initBdd();
		try {
			ArrayList<Contact> contacts = new ArrayList<Contact>();
			Statement statementContact = (Statement) bdd.createStatement();
			ResultSet resultsetContact = statementContact
					.executeQuery("SELECT id_contact, lastName, firstName, eMail, phone, address, postalCode, city, country "
							+ "FROM contact");
			while (resultsetContact.next()) {
				Contact contact = new Contact(
						resultsetContact.getInt("id_contact"),
						resultsetContact.getString("address"),
						resultsetContact.getString("postalCode"),
						resultsetContact.getString("city"),
						resultsetContact.getString("country"),
						resultsetContact.getString("lastName"),
						resultsetContact.getString("firstName"),
						resultsetContact.getString("eMail"),
						resultsetContact.getString("phone"));
				contacts.add(contact);
			}
			return contacts;
		} catch (SQLException e) {
		}
		return null;
	}
}
