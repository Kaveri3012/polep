package polep.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import agentspring.role.AbstractRole;
import agentspring.role.Role;
import polep.domain.agent.EnergyProducer;
import polep.domain.agent.PolepModel;
import polep.domain.market.Bid;
import polep.domain.market.ClearingPoint;
import polep.domain.market.EnergyMarket;
import polep.repository.BidRepository;
import polep.repository.ClearingPointRepository;

/** 
 * clears market
 * @author pbhagwat
 *
 * @param <T>
 */

public abstract class ClearSpotMarketRole extends AbstractRole<EnergyMarket> implements Role<EnergyMarket> {

	@Autowired
	BidRepository bidRepository;

	@Autowired
	ClearingPointRepository clearingPointRepository;

	public void act (EnergyMarket market){

		Iterable<Bid> sortedListofBidPairs = bidRepository.findOffersForMarketForTime(market, getCurrentTick());
		double demand = market.getDemand();
		double sumofSupplyBidsAccepted = 0;
		double acceptedPrice = 0;
		boolean isTheMarketCleared = false;

		for (Bid currentBid:sortedListofBidPairs){
			if (isTheMarketCleared == false) {


				if (sumofSupplyBidsAccepted < demand){
					sumofSupplyBidsAccepted += currentBid.getVolume();
					acceptedPrice = currentBid.getPrice();
					currentBid.setStatus(Bid.ACCEPTED);
					currentBid.setAcceptedVolume(currentBid.getVolume());

				} 


				else if (sumofSupplyBidsAccepted >= demand){
					currentBid.setStatus(Bid.PARTLY_ACCEPTED);
					currentBid.setAcceptedVolume(currentBid.getVolume()-(sumofSupplyBidsAccepted-demand));
					acceptedPrice = currentBid.getPrice();
					isTheMarketCleared = true;

				}

			}
			else {
				currentBid.setStatus(Bid.FAILED);
			}
		}
		if (isTheMarketCleared==true){
			sumofSupplyBidsAccepted = demand;
			ClearingPoint clearingPoint = new ClearingPoint();
			clearingPoint.setPrice(acceptedPrice);
			clearingPoint.setVolume(demand);
			clearingPoint.persist();
		}
		else {
			ClearingPoint clearingPoint = new ClearingPoint();
			clearingPoint.setPrice(market.getValueOfLostLoad());
			clearingPoint.setVolume(sumofSupplyBidsAccepted);
			clearingPoint.persist();
		}
	}

}



/**	
 * Get sorted list of Bid-Volume Pairs in ascending order
 * Get Demand
 * match demand supply and clear market
 * Store price of last bid as clearing price
 */


