package com.alevel.java.report.ubike.model.dto;

import java.time.Instant;

public record WaypointDTO(
        Long id,
        Double altitude,
        Double longitude
) {
}
