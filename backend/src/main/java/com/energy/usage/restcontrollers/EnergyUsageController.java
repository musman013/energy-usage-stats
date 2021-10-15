package com.energy.usage.restcontrollers;

import com.energy.usage.application.energyusage.IEnergyUsageAppService;
import com.energy.usage.application.energyusage.dto.UsageStatsDto;
import com.google.gson.JsonArray;

import java.util.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/energyUsage")
@RequiredArgsConstructor
public class EnergyUsageController {

    @NonNull
    private final IEnergyUsageAppService _energyUsageAppService;

    @RequestMapping(value = "/getTotalCost", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Double>> getTotalCost() {
        Double cost= _energyUsageAppService.getTotalCost();
        Map<String, Double> res = new HashMap<String,Double>();
        res.put("cost", cost);
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "/getGraphData", method = RequestMethod.GET)
    public ResponseEntity<List<UsageStatsDto>> getGraphData(@RequestParam String duration, @RequestParam List<String> sourceTypes) {
    	return ResponseEntity.ok(_energyUsageAppService.getGraphData(duration, sourceTypes));
    }

}
