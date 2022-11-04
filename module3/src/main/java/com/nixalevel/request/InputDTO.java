package com.nixalevel.request;

import java.util.List;

public record InputDTO(

        String label,

        List<String> colors,

        Long intervalChange,

        Long switchNumber

) {
}
