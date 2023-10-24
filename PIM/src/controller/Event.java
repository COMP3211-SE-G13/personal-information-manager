package controller;

import model.SimpleDatabase;

import java.io.IOException;

public class Event {
    private String eventName;
    private String eventStartTime;
    private String eventAlarm;
    private String eventDescription;

    /**
     * Event Contract
     * @param eventName: the name of event
     * @param eventStartTime: the start time of event
     * @param eventAlarm: the alarm of event
     * @param eventDescription: the description of event
     */
    public Event(String eventName, String eventStartTime, String eventAlarm, String eventDescription) {
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventAlarm = eventAlarm;
        this.eventDescription = eventDescription;
    }

    /**
     * Create Event Function
     */
    private void createEvent(String eventName, String eventStartTime, String eventAlarm, String eventDescription, int userId) {
        try {
            if (eventName.isEmpty() || eventStartTime.isEmpty() || eventAlarm.isEmpty() || eventDescription.isEmpty()) {
                System.out.println("Please enter all information!");
                return;
            }

            int eventId = SimpleDatabase.getNewID("events.csv");

            String[][] newEventData = {
                    {String.valueOf(eventId), String.valueOf(userId), eventName, eventStartTime, eventAlarm, eventDescription}
            };

            new SimpleDatabase("insert", "events.csv", newEventData);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

    /**
     * Get All Events Function
     */
    private String[][] getAllEvents() {
        try{
            String[][] data = SimpleDatabase.get("events.csv");
            return data;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
        return null;
    }

    /**
     * Get One Event Function
     * @param eventId: the id of event
     * @return: the event data
     */
    private String[] getOneEvent(String eventId) {
        try{
            String[][] data = SimpleDatabase.get("events.csv");

            for (int i = 0; i < data.length - 1; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    if (data[i][0].equals(eventId) & data[i][1].equals(String.valueOf(Auth.getUserId()))) {
                        return data[i];
                    }
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
     * Modify Event Function
     */
    private void modifyEvent(String eventName, String eventStartTime, String eventAlarm, String eventDescription, int userId, int eventId) {
        try{
            String [] dataWantUpdate = {String.valueOf(eventId), String.valueOf(userId), eventName, eventStartTime, eventAlarm, eventDescription};
            new SimpleDatabase("update", "events.csv", eventId, dataWantUpdate);
            System.out.println("Update Successfully!");
        }catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

    /**
     * Remove Event Function
     */
    private void removeEvent(String eventId) {
        try {
            new SimpleDatabase("remove", "events.csv", Auth.getUserId(), Integer.parseInt(eventId));
            System.out.println("Remove Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

//    /**
//     * Set Alarm Function
//     */
//    private void setAlarm() {
//
//    }
//
//    /**
//     * Cancel Alarm Function
//     */
//    private void cancelAlarm() {
//
//    }

}
