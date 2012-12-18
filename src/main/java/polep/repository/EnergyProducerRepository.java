package polep.repository;

import java.util.ArrayList;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryType;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;


import polep.domain.agent.EnergyProducer;
import polep.domain.market.EnergyMarket;


public interface EnergyProducerRepository extends
GraphRepository<EnergyProducer> {	
	
	@Query("START market=node({market}) MATCH (market)<-[:BIDDINGMARKET]-(bid) WHERE (bid.time = {time}) and (bid.supplyBid=true) RETURN sum(bid.volume)")
    double calculateTotalSupplyofProducerForTime(@Param ("producer") EnergyProducer producer, @Param("time") long time);

	// Get list of power plant producer
	@Query(value = "g.idx('__types__')[[className:'polep.domain.agent.EnergyProducer'].filter{it.time == tick}", type = QueryType.Gremlin)
	Iterable<EnergyProducer> listofpowerplantowners(@Param("tick") long time);
}
	