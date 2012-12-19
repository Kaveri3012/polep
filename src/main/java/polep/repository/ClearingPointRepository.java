package polep.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryType;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import polep.domain.agent.EnergyProducer;
import polep.domain.market.Bid;
import polep.domain.market.ClearingPoint;
import polep.domain.market.EnergyMarket;

public interface ClearingPointRepository extends GraphRepository<ClearingPoint> {

	 @Query(value = "g.idx('__types__')[[className:'polep.domain.market.ClearingPoint']].filter{it.time==tick}", type = QueryType.Gremlin)
	 Iterable<ClearingPoint> findClearingPointForIterableTime(@Param("tick") long tick);
	 
	 @Query(value="cps = g.idx('__types__')[[className:'polep.domain.market.ClearingPoint']].filter{it.time == tick}; return cps.next().price", type=QueryType.Gremlin)
	 double findTheOneClearingPriceForTime(@Param("tick") long time);
	 
	 
	// @Query(value = "g.v(market).in('MARKET_POINT').filter{it.time==tick}.next", type = QueryType.Gremlin)
	  //  ClearingPoint findClearingPointForMarketAndTime(@Param("market") EnergyMarket market, @Param("tick") long tick);
	 
	// @Query(value="cps = g.idx('__types__')[[className:'polep.domain.market.ClearingPoint']].filter{it.time == tick}; return cps.next.price", type=QueryType.Gremlin)
	 //double findTheOneClearingPriceForTime(@Param("tick") long time);
	 	 
	 @Query(value = "g.v(market).in('MARKET_POINT').filter{(it.time>=timeFrom) && (it.time<=timeTo)}.price.mean()", type = QueryType.Gremlin)
	    double calculateAverageClearingPriceForMarketAndTimeRange(@Param("market") EnergyMarket market,
	            @Param("timeFrom") long timeFrom, @Param("timeTo") long timeTo);

	
	}


