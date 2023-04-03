package org.example;

import java.sql.*;
import java.util.Scanner;

public class App {
    static String username, password,  driver;
    static FetchJson h;
    static Login l;
    static Connection conn;
    static Statement st;
    static Scanner sc;
    static Contest[] result;
    static PreparedStatement statement;
    static ResultSet rs;
    public static void display(Contest[] temp) {
        System.out.println("\n------------CONTEST INFORMATION---------------\n");
        for(Contest i : temp) {
            if(i!=null) {
                i.printInformation();
                System.out.println("---------------------------------------------------------------");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public static void load() {
        l = new Login();
        while (!l.flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        username = l.getUsername();
        password = l.getPassword();

        h = new FetchJson();
        h.configure();
        h.fetch();
    }
    public static void connectToDatabase() {
        driver = "com.mysql.cj.jdbc.Driver";
        conn = null;
        st = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contest_data", username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void insertIntoDatabase() {
        try {
            st = conn.createStatement();
            for (Contest i : h.cont) {
                String sql = "INSERT INTO contest (name, site, url, start_time, end_time, status) VALUES ('" + i.name + "','" + i.site + "','" + i.url + "','" + i.start_time + "','" + i.end_time + "','" + i.status + "')";
                st.executeUpdate(sql);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void retrieveFromDatabase() {
        int ch;
        sc = new Scanner(System.in);
        while(true) {
            System.out.println("Please select one of the below queries to retrieve data accordingly!");
            System.out.println("1: Retrieve information based on name of the contest.");
            System.out.println("2: Retrieve information based on host of the contest.");
            System.out.println("3: Retrieve information based on starting date.");
            System.out.println("4: Retrieve information based on ending date.");
            System.out.println("5: Retrieve information with status 'CODING'.");
            System.out.println("6: Exit the application");
            ch = sc.nextInt();
            switch (ch) {
                case 1 -> {
                    result = basedOnName();
                    display(result);
                }
                case 2 -> {
                    result = basedOnSite();
                    display(result);
                }
                case 3 -> {
                    result = basedOnStartTime();
                    display(result);
                }
                case 4 -> {
                    result = basedOnEndTime();
                    display(result);
                }
                case 5 -> {
                    result = basedOnStatus();
                    display(result);
                }
                case 6 -> {
                    System.out.println("Thank you!!!\nExiting the application...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option please try again...");
            }
        }
    }
    public static Contest[] basedOnName() {
        Contest[] temp = new Contest[20];
        int i = 0;
        for(Contest i1 : temp) {
            i1 = new Contest();
        }
        try {
            String sql = "SELECT * FROM contest WHERE name=?";
            statement = conn.prepareStatement(sql);
            System.out.println("Enter the name of the contest...");
            String nameOfContest = sc.next();
            nameOfContest += sc.nextLine();
            statement.setString(1, nameOfContest);
            rs = statement.executeQuery();
            while (rs.next() && i < 20) {
                temp[i] = new Contest();
                temp[i].name = rs.getString("name");
                temp[i].url = rs.getString("url");
                temp[i].site = rs.getString("site");
                temp[i].start_time = rs.getString("start_time");
                temp[i].end_time = rs.getString("end_time");
                temp[i].status = rs.getString("status");
                i++;
            }
            rs.close();
            statement.close();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temp;
    }
    public static Contest[] basedOnSite() {
        Contest[] temp = new Contest[20];
        int i = 0;
        for(Contest i1 : temp) {
            i1 = new Contest();
        }
        try {
            String sql = "SELECT * FROM contest WHERE site=?";
            statement = conn.prepareStatement(sql);
            System.out.println("Enter the name of the site...");
            String nameOfSite = sc.next();
            nameOfSite += sc.nextLine();
            statement.setString(1, nameOfSite);
            rs = statement.executeQuery();
            while (rs.next() && i < 20) {
                temp[i] = new Contest();
                temp[i].name = rs.getString("name");
                temp[i].url = rs.getString("url");
                temp[i].site = rs.getString("site");
                temp[i].start_time = rs.getString("start_time");
                temp[i].end_time = rs.getString("end_time");
                temp[i].status = rs.getString("status");
                i++;
            }
            rs.close();
            statement.close();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temp;
    }
    public static Contest[] basedOnStartTime()  {
        Contest[] temp = new Contest[20];
        int i = 0;
        for(Contest i1 : temp) {
            i1 = new Contest();
        }
        try {
            String sql = "SELECT * FROM contest WHERE start_time=?";
            statement = conn.prepareStatement(sql);
            System.out.println("Enter the starting time of the contest in the format YYY-MM-DD hh-mm-ss TIMEZONE...");
            String startTime = sc.next();
            startTime += sc.nextLine();
            statement.setString(1, startTime);
            rs = statement.executeQuery();
            while (rs.next() && i < 20) {
                temp[i] = new Contest();
                temp[i].name = rs.getString("name");
                temp[i].url = rs.getString("url");
                temp[i].site = rs.getString("site");
                temp[i].start_time = rs.getString("start_time");
                temp[i].end_time = rs.getString("end_time");
                temp[i].status = rs.getString("status");
                i++;
            }
            rs.close();
            statement.close();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temp;
    }
    public static Contest[] basedOnEndTime() {
        Contest[] temp = new Contest[20];
        int i = 0;
        for(Contest i1 : temp) {
            i1 = new Contest();
        }
        try {
            String sql = "SELECT * FROM contest WHERE end_time=?";
            statement = conn.prepareStatement(sql);
            System.out.println("Enter the ending time of the contest in the format YYY-MM-DD hh-mm-ss TIMEZONE...");
            String endTime = sc.next();
            endTime += sc.nextLine();
            statement.setString(1, endTime);
            rs = statement.executeQuery();
            while (rs.next() && i < 20) {
                temp[i] = new Contest();
                temp[i].name = rs.getString("name");
                temp[i].url = rs.getString("url");
                temp[i].site = rs.getString("site");
                temp[i].start_time = rs.getString("start_time");
                temp[i].end_time = rs.getString("end_time");
                temp[i].status = rs.getString("status");
                i++;
            }
            rs.close();
            statement.close();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temp;
    }
    public static Contest[] basedOnStatus()  {
        Contest[] temp = new Contest[20];
        int i = 0;
        for(Contest i1 : temp) {
            i1 = new Contest();
        }
        try {
            String sql = "SELECT * FROM contest WHERE status=?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "CODING");
            rs = statement.executeQuery();
            while (rs.next() && i < 20) {
                temp[i] = new Contest();
                temp[i].name = rs.getString("name");
                temp[i].url = rs.getString("url");
                temp[i].site = rs.getString("site");
                temp[i].start_time = rs.getString("start_time");
                temp[i].end_time = rs.getString("end_time");
                temp[i].status = rs.getString("status");
                i++;
            }
            rs.close();
            statement.close();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temp;
    }
}