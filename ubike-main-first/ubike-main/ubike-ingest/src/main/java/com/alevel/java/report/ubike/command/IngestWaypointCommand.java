package com.alevel.java.report.ubike.command;

import com.alevel.java.report.ubike.command.data.CreateWaypointRequest;
import com.alevel.java.report.ubike.exceptions.UbikeIngestException;
import com.alevel.java.report.ubike.model.Waypoint;
import com.alevel.java.report.ubike.model.dto.WaypointDTO;
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
