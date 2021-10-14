package com.energy.usage.application.energyusage.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindEnergyUsageByIdOutput {

    private Integer hours;
    private Long id;
    private String sourceType;
    private LocalDateTime timestamp;
    private Integer unitCost;
    private Integer units;
    private Long versiono;
}
