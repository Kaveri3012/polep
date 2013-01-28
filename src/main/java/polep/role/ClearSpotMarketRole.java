package polep.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import agentspring.role.AbstractRole;
import agentspring.role.Role;
import agentspring.role.RoleComponent;
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
@RoleComponent
public class ClearSpotMarketRole extends AbstractRole<EnergyMarket> implements Role<EnergyMarket> {

	@Autowired
	BidRepository bidRepository;

	@Autowired
	ClearingPointRepository clearingPointRepository;

	@Transactional
	public void act (EnergyMarket market){

		Iterable<Bid> sortedListofBidPairs = bidRepository.findAllSortedBidsByPrice(getCurrentTick());
		double demand = market.getDemand();
		double sumofSupplyBidsAccepted = 0;
		double acceptedPrice = 0;
		boolean isTheMarketCleared = false;
		
		//This epsilon is to account for rounding errors for java (only relevant for exact clearing)
		double clearingEpsilon = 0.001;
		
		if (demand == 0){
			isTheMarketCleared = true;
			acceptedPrice=0;
		}

		for (Bid currentBid:sortedListofBidPairs){
			


			if (isTheMarketCleared == false ) {


				if (demand-(sumofSupplyBidsAccepted + currentBid.getVolume()) >=  - clearingEpsilon){
						acceptedPrice = currentBid.getPrice();
						currentBid.setStatus(Bid.ACCEPTED);
						currentBid.setAcceptedVolume(currentBid.getVolume());
						sumofSupplyBidsAccepted += currentBid.getVolume();

					// logger.warn("{}", sumofSupplyBidsAccepted);
				} 


				else if (demand-(sumofSupplyBidsAccepted + currentBid.getVolume())< clearingEpsilon){

					currentBid.setStatus(Bid.PARTLY_ACCEPTED);
					currentBid.setAcceptedVolume((demand-sumofSupplyBidsAccepted));
					acceptedPrice = currentBid.getPrice();
					sumofSupplyBidsAccepted += currentBid.getAcceptedVolume();
					isTheMarketCleared = true;

					//logger.warn("Accepted" + currentBid.getAcceptedVolume());

				}

			} else{
				currentBid.setStatus(Bid.FAILED);
				currentBid.setAcceptedVolume(0);
			}

			if(demand - sumofSupplyBidsAccepted <   clearingEpsilon)
				isTheMarketCleared = true;

		}
		if (isTheMarketCleared==true){
			sumofSupplyBidsAccepted = demand;
			ClearingPoint clearingPoint = new ClearingPoint();
			clearingPoint.setPrice(acceptedPrice);
			clearingPoint.setVolume(demand);
			clearingPoint.setTime(getCurrentTick());
			clearingPoint.persist(); 
		}
		else {
			ClearingPoint clearingPoint = new ClearingPoint();
			clearingPoint.setPrice(market.getValueOfLostLoad());
			clearingPoint.setVolume(sumofSupplyBidsAccepted);
			clearingPoint.setTime(getCurrentTick());
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


