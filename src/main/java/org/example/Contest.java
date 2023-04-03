package org.example;

public class Contest {
    String name;
    String url;
    String start_time;
    String end_time;
    String status;
    String site;
    public void printInformation() {
        System.out.println("Name of the contest: " + this.name);
        System.out.println("Host site: " + site);
        System.out.println("Contest Link: " + this.url);
        System.out.println("Start time: " + this.start_time);
        System.out.println("End time: " + this.end_time);
        if(status.equals("CODING"))
            System.out.println("The contest is open\n");
        else
            System.out.println("The contest is closed\n");
    }
}