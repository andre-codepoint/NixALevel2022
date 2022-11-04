package com.alevel.java.report.ubike.command.data;

import java.time.Instant;

public record CreateRideRequest(
        String nickname,
        Long vehicleId,
        Long finishId,
        Instant startedAt,
        Instant finishedAt
) {

}
