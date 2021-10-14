package com.energy.usage.domain.energyusage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnergyUsageRepository
    extends JpaRepository<EnergyUsage, Long> {
	
	@Query("select sum(unitCost * units) from EnergyUsage")
	Double getTotalCost();
	
	@Query("SELECT eu.sourceType, date_trunc(?1, eu.timestamp) AS monthly,\n" + 
			"       sum(eu.unitCost * eu.units)           \n" + 
			"FROM EnergyUsage as eu\n" + 
			"where eu.sourceType in ?2\n" + 
			"GROUP BY eu.sourceType,monthly\n" + 
			"ORDER BY monthly")
	List<List<String>> getGraphData(String duration, List<String> sourceTypes);
}
