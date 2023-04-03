package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchJson {
    Contest[] cont;
    Gson g;
    URL url;
    HttpURLConnection con;
    BufferedReader in;
    StringBuffer response;
    String inputLine;

    public void configure() {
        try {
            url = new URL("https://kontests.net/api/v1/all");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            response = new StringBuffer();
            g = new Gson();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void fetch() {
        try {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        cont = g.fromJson(String.valueOf(response), Contest[].class);
    }
}