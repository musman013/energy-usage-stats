package com.energy.usage.application.energyusage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsageStatsDto {

    private String sourceType;
    private String timestamp;
    private Double cost;
}
