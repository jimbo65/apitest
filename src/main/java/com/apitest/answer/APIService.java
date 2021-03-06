package com.apitest.answer;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class APIService {

    public String getLondonUsers() throws Exception {
        String users;
        URL url = new URL("https://bpdts-test-app.herokuapp.com/city/London/users");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "text/xml, application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        users = br.readLine();

        conn.disconnect();

        return users;
    }

    public String getUsersCloseToLondon() throws Exception {

        UserUtils util = new UserUtils();
        JSONArray users;
        double distance = 50D;

        URL url = new URL("https://bpdts-test-app.herokuapp.com/users");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "text/xml, application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        users = new JSONArray(br.readLine());
        conn.disconnect();

        return util.getUsersLocationWithinGivenDistance(users, distance).toString();
    }
}
