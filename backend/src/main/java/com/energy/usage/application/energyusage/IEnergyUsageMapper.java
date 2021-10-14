package com.energy.usage.application.energyusage;

import com.energy.usage.application.energyusage.dto.*;
import com.energy.usage.domain.energyusage.EnergyUsage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEnergyUsageMapper {
    FindEnergyUsageByIdOutput energyUsageToFindEnergyUsageByIdOutput(EnergyUsage entity);
}
