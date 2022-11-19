package datos;

import java.util.ArrayList;


public class ContactList {
    private static ArrayList<Contact> contacts = new ArrayList<>();

    public static ArrayList<Contact> getContacts() {
        return contacts;
    }

    public static void addContacts(Contact contact) {
        ContactList.contacts.add(contact);
    }

    public static void deleteContact(Contact contact){
        ContactList.contacts.remove(contact);
    }
}
