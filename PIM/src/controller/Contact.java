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
            if (lastName.isEmpty() || firstName.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
                System.out.println("Please enter all information!");
                return;
            }

            int contactId = SimpleDatabase.getNewID("contacts.csv");

            String[][] newContactData = {
                    {String.valueOf(contactId), String.valueOf(userId), firstName, lastName, phoneNumber, address}
            };

            new SimpleDatabase("insert", "contacts.csv", newContactData);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

    /**
     * Get All Contacts Function
     */
    private String[][] getAllContacts() {
        try{
            String[][] data = SimpleDatabase.get("contacts.csv");
            return data;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
        return null;

    }

    /**
     * Get One Contact Function
     * @param contactId: the id of contact
     * @return String[]: the contact data
     */
    private String[] getOneContact(String contactId) {
        try{
            String[][] data = SimpleDatabase.get("contacts.csv");

            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals(contactId) & data[i][1].equals(String.valueOf(Auth.getUserId()))) {
                    return data[i];
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            return null;
        }

        return null;
    }

    /**
     * Modify Contact Function
     */
//    private void modifyContact() {
//        try {
//            String[][] dataWantUpdate = {
//
//            };
//            new SimpleDatabase("update", "contacts.csv", dataWantUpdate);
//            System.out.println("Update Successfully!");
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//            System.out.println("Please try again!");
//        }
//    }

    /**
     * Remove Contact Function
     */
    private void removeContact(String contactId) {
        try {
            new SimpleDatabase("remove", "contacts.csv", Auth.getUserId(),  Integer.parseInt(contactId));
            System.out.println("Remove Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

}
