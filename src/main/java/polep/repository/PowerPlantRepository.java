package polep.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryType;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import polep.domain.agent.EnergyProducer;
import polep.domain.market.Bid;
import polep.domain.market.ClearingPoint;
import polep.domain.market.EnergyMarket;
import polep.domain.technology.PowerPlant;

public interface PowerPlantRepository extends GraphRepository<PowerPlant> {

	
	@Query(value = "g.v(producer).out('OWNS').sort{it.marginalCost}._()", type = QueryType.Gremlin)
	Iterable<PowerPlant> findAllPerProducerSortedByMarginalCost(@Param("producer") EnergyProducer producer);
	
/*	 @Query(value = "g.V.filter{it.__type__=='polep.domain.technology.PowerPlant' && (it.dismantleTime > tick) && ((it.constructionStartTime + it.actualPermittime + it.actualLeadtime) <= tick)}.out('TECHNOLOGY').sum{it.capacity};", type = QueryType.Gremlin)
	    double calculateCapacityOfOperationalPowerPlants(@Param("tick") long tick);*/
	 
	 //total capacity of power plants
	 @Query(value ="g.v(producer).out('OWNS').sum{it.capacity}", type = QueryType.Gremlin)
	 double calculateCapacityOfPowerPlantsofProducer(@Param("producer") EnergyProducer producer);
	 
}