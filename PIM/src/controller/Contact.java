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
            if (lastName.equals("") || firstName.equals("") || phoneNumber.equals("") || address.equals("")) {
                System.out.println("Please enter all information!");
                return;
            }

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
        try{
            String[][] data = SimpleDatabase.get("contacts.csv");

            for (int i = 0; i < data.length - 1; i++) {
                System.out.println("Contact ID: " + data[i][0]);
                System.out.println("First Name: " + data[i][2]);
                System.out.println("Last Name: " + data[i][3]);
                System.out.println("Phone Number: " + data[i][4]);
                System.out.println("Address: " + data[i][5]);
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }

    }

    /**
     * Get One Contact Function
     */
    private void getOneContact(String contactId) {
        try{
            String[][] data = SimpleDatabase.get("contacts.csv");

            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals(contactId)) {
                    System.out.println("Contact ID: " + data[i][0]);
                    System.out.println("First Name: " + data[i][2]);
                    System.out.println("Last Name: " + data[i][3]);
                    System.out.println("Phone Number: " + data[i][4]);
                    System.out.println("Address: " + data[i][5]);
                    System.out.println();
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }

    }

    /**
     * Modify Contact Function
     */
    private void modifyContact() {
        try {
            String[][] dataWantUpdate = {

            };
            new SimpleDatabase("update", "contacts.csv", dataWantUpdate);
            System.out.println("Update Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

    /**
     * Remove Contact Function
     */
    private void removeContact(int userId, int contactId) {
        try {
            new SimpleDatabase("remove", "contacts.csv", userId, contactId);
            System.out.println("Remove Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

}
