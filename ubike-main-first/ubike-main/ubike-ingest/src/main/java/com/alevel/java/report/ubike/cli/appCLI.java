package com.alevel.java.report.ubike.cli;

import com.alevel.java.report.ubike.IngestApp;

import java.util.Scanner;

public class appCLI {


    public void run() {
        var scanner = new Scanner(System.in);
        System.out.println("Enter command:\nAW-add Waypoints\nAV-add Vehicles\nAR-add Riders\n");
        String command = scanner.nextLine();
        switch (command) {
            case ("AR"):
                new IngestApp().run();
                break;
        }
    }
    }
