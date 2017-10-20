package com.company;

import java.awt.event.ComponentAdapter;
import java.io.FileReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
    private final List<String> dynamicMessageNames = new ArrayList<>(Arrays.asList("companyName", "city", "timeZone", "firstName", "lastName", "fullName", "roomNumber", "startTimestamp", "endTimestamp"));
    private String timeGreeting;
    public static void main(String[] args) {
        Main main = new Main();
        List<Guest> convertedGuestList = main.getGuestEntries();
        List<Company> convertedCompanyList = main.getCompanyEntries();
        List<Message> convertedMessageList = main.getMessageEntries();
        boolean leave = false;
        mainLoop:
        while(!leave) {
            Scanner reader = new Scanner(System.in);
            String input;
            System.out.println("Welcome to the Super Dynamic Messenger Program!\nIf you would like to exit at any time just enter \"exit\"");
            System.out.println("If you would like to restart at any time just enter \"restart\"\n");
            main.getTimeGreeting();
            System.out.println("Would you like to select a guest by their ID, Room Number, or Full Name?");
            String guestSortChosen = "";
            boolean selectionLoop = false;
            while(!selectionLoop) {
                input = reader.nextLine().trim();
                //null safe input check
                if("exit".equals(input.toLowerCase())) {
                    System.out.println("Thank you for using the Super Dynamic Messenger Program, I hope the experience was enjoyable!");
                    System.exit(5);
                } else if ("restart".equals(input.toLowerCase())) {
                    continue mainLoop;
                }
                if("id".equals(input.toLowerCase())) {
                    guestSortChosen = "ID";
                    selectionLoop = true;
                } else if ("room number".equals(input.toLowerCase())) {
                    guestSortChosen = "Room Number";
                    selectionLoop = true;
                } else if ("full name".equals(input.toLowerCase())) {
                    guestSortChosen = "Full Name";
                    selectionLoop = true;
                } else {
                    System.out.println("The input of: " + input + " is not a selection option currently.\nThe selection options are ID, Room Number, or Full Name.");
                }
            }
            boolean searchGuest = false;
            Guest selectedGuest = null;
            while(!searchGuest) {
                System.out.println("Here is a list of all the guests.\n");
                System.out.println(main.retrieveGuestSelections(convertedGuestList) + "\n");
                System.out.println("What " + guestSortChosen + " of a guest are you searching for?");
                input = reader.nextLine().trim();
                if("exit".equals(input.toLowerCase())) {
                    System.out.println("Thank you for using the Super Dynamic Messenger Program, I hope the experience was enjoyable!");
                    System.exit(5);
                } else if ("restart".equals(input.toLowerCase())) {
                    continue mainLoop;
                }
                if("id".equals(guestSortChosen.toLowerCase())) {
                    for(Guest g : convertedGuestList) {
                        if(String.valueOf(g.getId()).equals(input.toLowerCase())) {
                            selectedGuest = g;
                            searchGuest = true;
                        }
                    }
                } else if ("room number".equals(guestSortChosen.toLowerCase())) {
                    for(Guest g : convertedGuestList) {
                        if(String.valueOf(g.getRoomNumber()).equals(input.toLowerCase())) {
                            selectedGuest = g;
                            searchGuest = true;
                        }
                    }
                } else {
                    for(Guest g : convertedGuestList) {
                        if(String.valueOf(g.getFullName().toLowerCase()).equals(input.toLowerCase())) {
                            selectedGuest = g;
                            searchGuest = true;
                        }
                    }
                }
                if(selectedGuest == null) {
                    System.out.println("The " + guestSortChosen + " " + input + " is not located in the selection of guests");
                    System.out.println("Please try again.");
                }
            }
            System.out.println("\nWould you like to select a company by Company Name or ID?");
            String companySortChosen = "";
            selectionLoop = false;
            while(!selectionLoop) {
                input = reader.nextLine().trim();
                //null safe input check
                if("exit".equals(input.toLowerCase())) {
                    System.out.println("Thank you for using the Super Dynamic Messenger Program, I hope the experience was enjoyable!");
                    System.exit(5);
                } else if ("restart".equals(input.toLowerCase())) {
                    continue mainLoop;
                }
                if("id".equals(input.toLowerCase())) {
                    guestSortChosen = "ID";
                    selectionLoop = true;
                } else if ("company name".equals(input.toLowerCase())) {
                    guestSortChosen = "Company Name";
                    selectionLoop = true;
                }  else {
                    System.out.println("The input of: " + input + " is not a selection option currently.\nThe selection options are Company Name or ID.");
                }
            }
            searchGuest = false;
            Company selectedCompany = null;
            while(!searchGuest) {
                System.out.println("Here is a list of all the companies.\n");
                System.out.println(main.retrieveCompanySelections(convertedCompanyList) + "\n");
                System.out.println("What " + guestSortChosen + " of a company are you searching for?");
                input = reader.nextLine().trim();
                if("exit".equals(input.toLowerCase())) {
                    System.out.println("Thank you for using the Super Dynamic Messenger Program, I hope the experience was enjoyable!");
                    System.exit(5);
                } else if ("restart".equals(input.toLowerCase())) {
                    continue mainLoop;
                }
                if("id".equals(guestSortChosen.toLowerCase())) {
                    for(Company c : convertedCompanyList) {
                        if(String.valueOf(c.getId()).equals(input.toLowerCase())) {
                            selectedCompany = c;
                            searchGuest = true;
                        }
                    }
                } else {
                    for(Company c : convertedCompanyList) {
                        if(String.valueOf(c.getCompanyName().toLowerCase()).equals(input.toLowerCase())) {
                            selectedCompany = c;
                            searchGuest = true;
                        }
                    }
                }
                if(selectedCompany == null) {
                    System.out.println("The " + guestSortChosen + " " + input + " is not located in the selection of companies");
                    System.out.println("Please try again.");
                }
            }
            System.out.println("You have selected the guest: " + "ID - " + selectedGuest.getId() + ", Full Name - " + selectedGuest.getFullName() + ", Room Number - " + selectedGuest.getRoomNumber());
            System.out.println("You have selected the company: " + "ID - " + selectedCompany.getId() + ", Company Name - " + selectedCompany.getCompanyName() + ", City - " + selectedCompany.getCityName());
            searchGuest = false;
            boolean customTemplate = false;
            while(!searchGuest) {
                System.out.println("Would you like to create your own custom message, yes or no?");
                input = reader.nextLine().trim();
                if ("exit".equals(input.toLowerCase())) {
                    System.out.println("Thank you for using the Super Dynamic Messenger Program, I hope the experience was enjoyable!");
                    System.exit(5);
                } else if ("restart".equals(input.toLowerCase())) {
                    continue mainLoop;
                }
                if ("yes".equals(input.toLowerCase())) {
                    customTemplate = true;
                    searchGuest = true;
                } else if ("no".equals(input.toLowerCase())) {
                    customTemplate = false;
                    searchGuest = true;
                } else {
                    System.out.println("The input of: " + input + " is not yes or no. Please try again.");
                }
            }
            String finalOutput;
            if(customTemplate) {
                searchGuest = false;
                while (!searchGuest) {
                    System.out.println("Here is a list of all the possible insertable values.\n");
                    System.out.println("companyName, city, timeZone, firstName, lastName, fullName, roomNumber, startTimestamp, endTimestamp, timeGreeting\n");
                    System.out.println("Please input the text you would like, and include the words above contained in curly braces to recieve dynamic content.");
                    System.out.println("Here is an example.\n\"dear {firstName}, I hope you are having a {timeGreeting}. Welcome to {roomNumber}. Please check in at {startTimestamp}\"");
                    System.out.println("Please be weary of \'{\'s that are not closed by \'}\'s. This will result in an error.");
                    System.out.print("Insert your message here: ");
                    input = reader.nextLine().trim();
                    if ("exit".equals(input.toLowerCase())) {
                        System.out.println("Thank you for using the Super Dynamic Messenger Program, I hope the experience was enjoyable!");
                        System.exit(5);
                    } else if ("restart".equals(input.toLowerCase())) {
                        continue mainLoop;
                    }
                    String validResult = main.checkValidStringformat(input);
                    if(!"Good".equals(validResult)) {
                        System.out.println("\n"+validResult+"\n");
                        continue;
                    }
                    Message customMessage = new Message(input,convertedMessageList.size()+1);
                    finalOutput = main.generateOutputMessage(selectedGuest, selectedCompany, customMessage);
                    System.out.println("The final output is:\n"+ finalOutput+"\n");
                    continue mainLoop;
                }

            } else {
                searchGuest = false;
                Message selectedMessage = null;
                while (!searchGuest) {
                    System.out.println("Here is a list of all the messages.\n");
                    System.out.println(main.retrieveMessageSelections(convertedMessageList) + '\n');
                    System.out.println("What ID of a message are you searching for?");
                    input = reader.nextLine().trim();
                    if ("exit".equals(input.toLowerCase())) {
                        System.out.println("Thank you for using the Super Dynamic Messenger Program, I hope the experience was enjoyable!");
                        System.exit(5);
                    } else if ("restart".equals(input.toLowerCase())) {
                        continue mainLoop;
                    }
                    for (Message m : convertedMessageList) {
                        if (String.valueOf(m.getId()).equals(input.toLowerCase())) {
                            selectedMessage = m;
                            searchGuest = true;
                        }
                    }
                    if (selectedMessage == null) {
                        System.out.println("The id " + input + " is not located in the selection of messages.");
                        System.out.println("Please try again.");
                    }
                }
                finalOutput = main.generateOutputMessage(selectedGuest, selectedCompany, selectedMessage);
                System.out.println("The final output is:\n"+ finalOutput+"\n");
                continue mainLoop;
            }
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

    public List<Message> getMessageEntries() {
        JSONArray messageEntries = getJsonFromFile("Messages.json");
        List<Message> messageList = new ArrayList<>();
        for(int i=0; i<messageEntries.size(); i++) {
            JSONObject holder = (JSONObject) messageEntries.get(i);
            String content = (String) holder.get("message");
            long id = (long) holder.get("id");
            Message message = new Message(content,id);
            messageList.add(message);
        }
        return messageList;
    }

    public void getTimeGreeting() {
        int localTimeHour = LocalDateTime.now().getHour();
        if(localTimeHour >= 0 && localTimeHour < 12) {
            timeGreeting = "Good morning";
        } else if (localTimeHour >= 12 && localTimeHour < 6) {
            timeGreeting = "Good afternoon";
        } else {
            timeGreeting = "Good evening";
        }
    }

    public String retrieveGuestSelections(List<Guest> convertedGuestList) {
        String collection = "";
        for(int i=0; i<convertedGuestList.size(); i++) {
            collection += "ID - " + convertedGuestList.get(i).getId() + ", Full Name - " + convertedGuestList.get(i).getFullName() + ", Room Number - " + convertedGuestList.get(i).getRoomNumber() + "\n";
        }
        return collection.substring(0,collection.length()-1);
    }

    public String retrieveCompanySelections(List<Company> convertedCompanyList) {
        String collection = "";
        for(int i=0; i<convertedCompanyList.size(); i++) {
            collection += "ID - " + convertedCompanyList.get(i).getId() + ", Company Name - " + convertedCompanyList.get(i).getCompanyName() + "\n";
        }
        return collection.substring(0,collection.length()-1);
    }

    public String retrieveMessageSelections(List<Message> convertedMessageList) {
        String collection = "";
        for(int i=0; i<convertedMessageList.size(); i++) {
            collection += "ID - " + convertedMessageList.get(i).getId() + ", Message - " + convertedMessageList.get(i).getContent() + "\n";
        }
        return collection.substring(0,collection.length()-1);
    }

    public String checkValidStringformat(String input) {

        final String OPEN = "{";
        final String CLOSE = "}";
        int both = 0;
        char curr;
        Stack<Character> pstack = new Stack<>();
        for(int i=0; i<input.length(); i++){
            curr = input.charAt(i);
            if (OPEN.indexOf(curr)>-1){
                pstack.push(curr);
                both++;
            } else if (CLOSE.indexOf(curr)>-1){
                try {
                    char top = pstack.pop();
                    if (OPEN.indexOf(top)!=CLOSE.indexOf(curr)){
                        return "Incorrect number of curly braces.";
                    }
                } catch (EmptyStackException e){
                    return "Incorrect number of curly braces.";
                }
            }
        }
        if(!pstack.empty()) {
            return "Incorrect number of curly braces.";
        }
        List<String> dynamics = retrieveDynamicNames(input);
        if(!checkDynamicVariables(dynamics)){
            return "Bad insertable name.";
        }
        return "Good";
    }

    public List<String> retrieveDynamicNames(String input) {
        List<String> dynamics = new ArrayList<>();
        int location = 0;
        int endLocation = 0;
        int both = StringUtils.countMatches(input, "{");
        for(int i=0; i<both; i++) {
            location = input.indexOf("{")+1;
            endLocation = input.indexOf("}");
            dynamics.add(input.substring(location,endLocation));
            input = input.substring(endLocation+1);
        }
        return dynamics;
    }

    public boolean checkDynamicVariables(List<String> dynamics) {
        dynamics = dynamics.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        List<String> result = dynamicMessageNames.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        dynamics.removeAll(result);
        dynamics.remove("timegreeting");
        for(int i=0; i<dynamics.size(); i++) {
            System.out.println("There was an issue processing the given insertable value: " + dynamics.get(i));
        }
        if(dynamics.size()==0){
            return true;
        } else {
            return false;
        }
    }

    public String generateOutputMessage(Guest guest, Company company, Message message) {
        List<String> dynamics = retrieveDynamicNames(message.getContent());
        String output = message.getContent();

        int endPoint = 0;
        int frontPoint = 0;
        String inserted;
        for(String dynamic : dynamics) {
            frontPoint = output.indexOf('{');
            endPoint = output.indexOf('}');
            inserted = getInsertValue(output.substring(frontPoint+1,endPoint),guest,company);
            output = output.substring(0, frontPoint) + inserted + output.substring(endPoint+1);
        }
        return output;
    }

    public String getInsertValue(String dynamicString, Guest guest, Company company) {
        switch (dynamicString.toLowerCase()) {
            case "companyname": return company.getCompanyName();
            case "city": return company.getCityName();
            case "timezone": return company.getTimeZone();
            case "firstname": return guest.getFirstName();
            case "lastname": return guest.getLastName();
            case "roomnumber": return String.valueOf(guest.getRoomNumber());
            case "starttimestamp": return guest.getStartTimestamp().toString();
            case "endtimestamp": return guest.getEndTimestamp().toString();
            case "fullname": return guest.getFullName();
            case "timegreeting": return timeGreeting;
        }
        return "NULL";
    }
}
