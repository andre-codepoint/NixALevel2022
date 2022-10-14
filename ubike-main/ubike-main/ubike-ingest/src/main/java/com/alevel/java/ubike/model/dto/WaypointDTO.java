package com.alevel.java.ubike.model.dto;

import java.time.Instant;

public record WaypointDTO(
        Long id,
        Double altitude,
        Double longitude
) {
}
