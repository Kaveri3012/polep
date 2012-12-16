package polep.role;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.EnergyProducer;
import polep.domain.market.Bid;
import polep.domain.market.BiddingStrategy;
import polep.domain.market.EnergyMarket;
import polep.domain.technology.PowerPlant;
import polep.repository.BidRepository;
import polep.repository.EnergyProducerRepository;
import polep.repository.PowerPlantRepository;
import agentspring.role.AbstractRole;
import agentspring.role.Role;

/**
 * @author RubenVerweij
 *PowerPlant owners dispatch power:
 *Determine volume for dispatch = get ClearingPrice from database and compare with own bids.
 *Determine dispatch order depending upon marginal cost of generation and dispatch volume
 *Store Cash in PrevCash 
 *Update cash = cash + Revenue (double)
 *Revenues  = ClearingPrice*Volume 
 */

public class DispatchPowerPlantRole  extends AbstractRole<EnergyProducer> implements Role<EnergyProducer> {
	
	@Autowired
	PowerPlantRepository powerPlantRepository;
	
	@Autowired
	BidRepository bidRepository;
	
	@Autowired
	EnergyProducerRepository energyProducerRepository;
	
	@Transactional
	public void act(EnergyProducer producer){
		
		/* TODO: Hey, the clearing via the clearingPrice should work.
		 * However, after thinking a bit about it, and looking at the
		 * polep.domain.market.Bid class, I think we already have a 
		 * solution via the bid status (ACCEPTED, PARTLY_ACCEPTED, etc.)
		 * and also via the variable acceptedAmount. Just assume for now
		 * that these are properly dealt with in the MarketClearingRole.
		 * You could for example assume, that via 
		 * double totalAmount = bidRespository.calculateTotalAcceptedAmountforEnergyProducerInTimeStep(producer, getCurrentTick())
		 * you have the total accepted amount, and that you have a marginal cost sorted power plant list: Iterable<PowerPlant> marginalCostSortedPowerPlants.
		 */
		
		acceptedAmount = bidRespository.calculateAcceptedVolumeofBidInTimeStep(bid, getCurrentTick());
		// get accepted amount per bid?
		
		Iterable<Bid> sortedListofBidPairsPerProducer = bidRepository.findOffersForMarketForTime(producer, getCurrentTick());
		// gives sorted list of bids per producer? list to be defined in repository
				
		EnergyMarket market = new EnergyMarket();
    	double clearingprice = market.getClearingPrice();
	
		
		for (Bid currentBid:sortedListofBidPairsPerProducer){
			
			double revenue = producer.getRevenue();
			double prevCash = producer.getPrevCash();
			double cash = producer.getCash();	
					
							
				if (currentBid.getStatus() == Bid.ACCEPTED){
					
					revenue = acceptedAmount * clearingprice;
					cash = prevCash + revenue;
				
				} 


				if (currentBid.getStatus() == Bid.PARTLY_ACCEPTED){
					
					revenue = acceptedAmount * clearingprice;
					cash = prevCash + revenue;
		
				}

				else {
				
					revenue = 0;
					cash = prevCash + revenue;
					
				}
			}	

	}
	
	// save cash in repository?
	// save dispatched bids?
	
}			
		
		
		
	

	

		
		
		

