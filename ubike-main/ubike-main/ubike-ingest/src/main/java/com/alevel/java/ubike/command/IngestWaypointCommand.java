package com.alevel.java.ubike.command;

import com.alevel.java.ubike.command.data.CreateRideRequest;
import com.alevel.java.ubike.command.data.CreateWaypointRequest;
import com.alevel.java.ubike.exceptions.UbikeIngestException;
import com.alevel.java.ubike.model.Ride;
import com.alevel.java.ubike.model.Rider;
import com.alevel.java.ubike.model.Vehicle;
import com.alevel.java.ubike.model.Waypoint;
import com.alevel.java.ubike.model.dto.Coordinates;
import com.alevel.java.ubike.model.dto.RideDTO;
import com.alevel.java.ubike.model.dto.WaypointDTO;
import jakarta.persistence.EntityTransaction;
import org.hibernate.SessionFactory;

class IngestWaypointCommand implements Command<WaypointDTO> {

    private final SessionFactory sessionFactory;

    private final CreateWaypointRequest context;

    IngestWaypointCommand(SessionFactory sessionFactory, CreateWaypointRequest context) {
        this.sessionFactory = sessionFactory;
        this.context = context;
    }

    @Override
    public WaypointDTO execute() throws UbikeIngestException {

        EntityTransaction tx = null;

        try (var session = sessionFactory.openSession()) {

            tx = session.beginTransaction();

//            Waypoint waypoint = session.bySimpleNaturalId(Waypoint.class)
//                    .loadOptional(new Coordinates(context.altitude(),context.longitude()))
//                    .orElseThrow(() -> new UbikeIngestException("No waypoint found by altitude " + context.altitude() +
//                            " and longitude " + context.longitude()));

            var wp = new Waypoint();
            wp.setAltitude(context.altitude());
            wp.setLongitude(context.longitude());
            session.persist(wp);

            var result = new WaypointDTO(
                    wp.getId(),
                    wp.getAltitude(),
                    wp.getLongitude()
            );

            tx.commit();

            return result;

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new UbikeIngestException(e);
        }
    }

}
