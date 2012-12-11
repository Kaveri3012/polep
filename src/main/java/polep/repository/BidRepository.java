/**
 * 
 */
package polep.repository;


import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryType;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;




import polep.domain.market.Bid;
import polep.domain.market.EnergyMarket;

/**
 * @author Kaveri
 *
 */
public interface BidRepository extends GraphRepository<Bid> {
	
	// Get all supply bids from the energy market in ascending order
	
	 @Query("START market=node({market}) MATCH (market)<-[:BIDDINGMARKET]-(bid) WHERE (bid.time = {time}) and (bid.supplyBid=true) RETURN bid ORDER BY bid.price asc")
	    Iterable<Bid> findOffersForMarketForTime(@Param("market") EnergyMarket market, @Param("time") long time);
	 
	 @Query("START market=node({market}) MATCH (market)<-[:BIDDINGMARKET]-(bid) WHERE (bid.time = {time}) and (bid.supplyBid=true) RETURN sum(bid.volume)")
	    double calculateTotalSupplyForMarketForTime(@Param("market") EnergyMarket market, @Param("time") long time);

	 @Query("START market=node({market}) MATCH (market)<-[:BIDDINGMARKET]-(bid) WHERE (bid.time = {time}) and (bid.supplyBid=true) RETURN max(bid.price)")
	    double calculateTotalSupplyPriceForMarketForTime(@Param("market") EnergyMarket market, @Param("time") long time);
}
