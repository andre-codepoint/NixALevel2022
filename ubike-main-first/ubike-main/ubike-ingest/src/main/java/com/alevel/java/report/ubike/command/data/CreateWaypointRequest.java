package com.alevel.java.report.ubike.command.data;

import java.time.Instant;

public record CreateWaypointRequest(

        Double altitude,
        Double longitude
) {

}
