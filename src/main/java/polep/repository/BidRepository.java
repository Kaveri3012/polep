/**
 * 
 */
package polep.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import polep.domain.market.Bid;

/**
 * @author Kaveri
 *
 */
public interface BidRepository extends GraphRepository<Bid> {

}
