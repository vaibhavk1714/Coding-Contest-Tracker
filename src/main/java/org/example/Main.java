package org.example;
public class Main {

    public static void main(String[] args) {
        App.load();
        App.connectToDatabase();
        App.insertIntoDatabase();
        App.retrieveFromDatabase();
    }
}