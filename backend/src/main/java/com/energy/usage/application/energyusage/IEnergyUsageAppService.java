package com.energy.usage.application.energyusage;

import com.energy.usage.application.energyusage.dto.*;
import java.util.*;

public interface IEnergyUsageAppService {
	
    Double getTotalCost();
    List<UsageStatsDto> getGraphData(String duration, List<String> sourceTypes);
}
