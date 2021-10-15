package com.energy.usage.application.energyusage;

import com.energy.usage.application.energyusage.dto.*;
import com.energy.usage.domain.energyusage.EnergyUsage;
import com.energy.usage.domain.energyusage.IEnergyUsageRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.*;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnergyUsageAppService implements IEnergyUsageAppService {
	
	@NonNull
	private IEnergyUsageRepository energyUsageRepository;

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Double getTotalCost() {
		return energyUsageRepository.getTotalCost();
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<UsageStatsDto> getGraphData(String duration, List<String> sourceTypes) {
		List<List<String>> data = energyUsageRepository.getGraphData(duration, sourceTypes);
		
		List<UsageStatsDto> array = new ArrayList<UsageStatsDto>();

		for(List<String> row: data) {
			UsageStatsDto item = new UsageStatsDto();
			item.setSourceType(row.get(0));
			item.setTimestamp(row.get(1));
			item.setCost(Double.valueOf(row.get(2)));
			array.add(item);
		}
		
		return array;
	}

}
