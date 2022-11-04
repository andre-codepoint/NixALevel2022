package com.alevel.java.report.ubike;

import com.alevel.java.report.ubike.cli.addRider;
import com.alevel.java.report.ubike.cli.addWaypoint;
import com.alevel.java.report.ubike.command.CommandFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class IngestApp {

    private static final Logger log = LoggerFactory.getLogger(IngestApp.class);

    public static void main(String[] args) {
        new IngestApp().run();
    }

    public void run() {
        try (var serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
             var sessionFactory = new Configuration().buildSessionFactory(serviceRegistry)) {
            var scanner = new Scanner(System.in);

            System.out.println("Enter command:\nAW-add Waypoints\nAV-add Vehicles\nAR-add Riders\n");
            String command = scanner.nextLine();
            var commandFactory = new CommandFactory(sessionFactory);
            switch (command) {
                case ("AR"):
                    var arCLI = new addRider(commandFactory);
                    arCLI.run();
                    break;
                case ("AW"):
                    var awCLI = new addWaypoint(commandFactory);
                    awCLI.run();
                    break;
            }

        } catch (Exception e) {
            log.error("Error during user interaction", e);
        }
    }

}
