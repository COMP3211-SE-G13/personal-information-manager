package controller;

import model.SimpleDatabase;

public class Contact {
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String address;

    /**
     * Contact Contract
     * @param lastName: the last name of contact
     * @param firstName: the first name of contact
     * @param phoneNumber: the phone number of contact
     * @param address: the address of contact
     */
    public Contact(String lastName, String firstName, String phoneNumber, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Create Contact Function
     * @param lastName: The last name of the contact
     * @param firstName: The first name of the contact
     * @param phoneNumber: The Phone number of the contact
     * @param address: The address of the contact
     * @param userId: The ID of the user
     */
    private void createContact(String lastName, String firstName, String phoneNumber, String address, int userId) {
        try {
            int contactId = SimpleDatabase.getNewID("contacts.csv");

            String[][] newContactData = {
                    {String.valueOf(contactId), String.valueOf(userId), firstName, lastName, phoneNumber, address}
            };

            new SimpleDatabase("insert", "contact.csv", newContactData);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

    /**
     * Get All Contacts Function
     */
    private void getAllContacts() {

    }

    /**
     * Get One Contact Function
     */
    private void getOneContact() {

    }

    /**
     * Modify Contact Function
     */
    private void modifyContact() {

    }

    /**
     * Remove Contact Function
     */
    private void removeContact() {

    }

}
