package com.company;

import java.awt.event.ComponentAdapter;
import java.io.FileReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        List<Guest> convertedGuestList = new ArrayList<>();
        List<Company> convertedCompanyList = new ArrayList<>();
        convertedGuestList = main.getGuestEntries();
        convertedCompanyList = main.getCompanyEntries();
        boolean leave = false;
        while(!leave) {
            Scanner reader = new Scanner(System.in);
            System.out.println("Welcome to the Super Dynamic Messenger Program!\nIf you would like to exit at any time just enter \"exit\"\n");
            String input = reader.next().trim();
            //null safe input check
            if("exit".equals(input)) {
                System.out.println("Thank you for using the Super Dynamic Messenger Program, I hope the experience was enjoyable!");
                System.exit(5);
            }
            String greetingTime = main.getTimeGreeting();

        }
    }

    public JSONArray getJsonFromFile(String fileName) {
        JSONArray array = null;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(fileName));
            array = (JSONArray) obj;
        } catch (Exception e) {
            System.out.println("Error in main: " + e);
            System.exit(5);
        }
        return array;
    }

    public List<Guest> getGuestEntries() {
        JSONArray guestEntries = getJsonFromFile("Guests.json");
        List<Guest> guestList = new ArrayList<>();
        for(int i=0; i<guestEntries.size();i++) {
            JSONObject holder = (JSONObject) guestEntries.get(i);
            long id = (long) holder.get("id");
            String firstName = (String) holder.get("firstName");
            String lastName = (String) holder.get("lastName");
            JSONObject reservation = (JSONObject) holder.get("reservation");
            long roomNumber = (long) reservation.get("roomNumber");
            Date startTimestamp = new Date(((long) reservation.get("startTimestamp"))*1000);
            Date endTimestamp = new Date(((long) reservation.get("endTimestamp"))*1000);
            Guest guest = new Guest(id, firstName, lastName, firstName+" "+lastName, roomNumber, startTimestamp, endTimestamp);
            guestList.add(guest);
        }
        return guestList;
    }

    public List<Company> getCompanyEntries() {
        JSONArray companyEntries = getJsonFromFile("Companies.json");
        List<Company> companyList = new ArrayList<>();
        for(int i=0; i<companyEntries.size(); i++) {
            JSONObject holder = (JSONObject) companyEntries.get(i);
            long id = (long) holder.get("id");
            String companyName = (String) holder.get("company");
            String cityName = (String) holder.get("city");
            String timeZone = (String) holder.get("timezone");
            Company company = new Company(id, companyName, cityName, timeZone);
            companyList.add(company);
        }
        return companyList;
    }

    public String getTimeGreeting() {
        String ret;
        int localTimeHour = LocalDateTime.now().getHour();
        if(localTimeHour >= 0 && localTimeHour < 12) {
            ret = "Good morning";
        } else if (localTimeHour >= 12 && localTimeHour < 6) {
            ret = "Good afternoon";
        } else {
            ret = "Good evening";
        }
        return ret;
    }

    /*public String getGuestSelector(String input) {
        System.out.println("Would you like to select a user by id, full name, or room number?");
    }*/
}
