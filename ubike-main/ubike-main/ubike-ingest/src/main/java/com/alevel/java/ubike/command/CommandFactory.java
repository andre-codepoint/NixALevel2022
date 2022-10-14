package com.alevel.java.ubike.command;

import com.alevel.java.ubike.command.data.CreateRideRequest;
import com.alevel.java.ubike.command.data.CreateWaypointRequest;
import com.alevel.java.ubike.model.dto.RideDTO;
import com.alevel.java.ubike.model.dto.WaypointDTO;
import org.hibernate.SessionFactory;

public class CommandFactory {

    private final SessionFactory sessionFactory;

    public CommandFactory(SessionFactory session) {
        this.sessionFactory = session;
    }

    public Command<RideDTO> ingestRide(CreateRideRequest context) {
        return new IngestRideCommand(sessionFactory, context);
    }

    public Command<WaypointDTO> ingestWaypoint(CreateWaypointRequest context) {
        return new IngestWaypointCommand(sessionFactory, context);
    }


}
