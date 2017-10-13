package com.company;

import java.io.FileReader;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

    public static void main(String[] args) {
        JSONArray companyEntries = null;
        List convertedCompanyList;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("Companies.json"));
            companyEntries = (JSONArray) obj;
        } catch (Exception e) {
            System.out.println("Error in main: " + e);
            System.exit(5);
        }

        for (int i=0; i<companyEntries.size();i++) {
            JSONObject holder = (JSONObject)companyEntries.get(0);
        }
    }


}
