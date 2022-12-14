package com.alevel.java.report.ubike.model.dto;

import java.time.Instant;

public record RideDTO(
        Long id,
        String rider,
        Long vehicleId,
        Coordinates start,
        Coordinates finish,
        Instant startedAt,
        Instant finishedAt
) {
}
