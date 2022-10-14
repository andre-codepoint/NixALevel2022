package com.alevel.java.ubike.cli;

import com.alevel.java.ubike.command.Command;
import com.alevel.java.ubike.command.CommandFactory;
import com.alevel.java.ubike.command.data.CreateRideRequest;
import com.alevel.java.ubike.command.data.CreateWaypointRequest;
import com.alevel.java.ubike.exceptions.UbikeIngestException;
import com.alevel.java.ubike.model.dto.RideDTO;
import com.alevel.java.ubike.model.dto.WaypointDTO;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class addWaypoint {

    private final CommandFactory commandFactory;

    public addWaypoint(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public void run() throws UbikeIngestException {

        var scanner = new Scanner(System.in);

//        System.out.println("Enter altitude:");
//        double altitude = scanner.nextDouble();
//        scanner.nextLine();
//
//        System.out.println("Enter longitude:");
//        double longitude = scanner.nextDouble();
//        scanner.nextLine();

        double altitude = 49.94405779558482;
        double longitude = 36.30172482719223;

        Command<WaypointDTO> command = commandFactory.ingestWaypoint(new CreateWaypointRequest(
                longitude,
                altitude
        ));

        WaypointDTO wp = command.execute();

        System.out.printf(
                "Created Waypoint %d: altitude %d and longitude %d",
                wp.id(),
                wp.altitude(),
                wp.altitude()
        );
    }
}
