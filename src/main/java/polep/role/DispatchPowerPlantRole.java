package polep.role;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.EnergyProducer;
import polep.domain.market.Bid;
import polep.domain.market.BiddingStrategy;
import polep.domain.market.EnergyMarket;
import polep.domain.market.PowerPlantWithholdment;
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

	/* TODO: Although you can do this, no permanent information can be stored in roles.
	/ To make it easier to remember it might a good convention to only store variables
	 * within the methods.
	 */
	public double prevCash;
	public double revenue;
	
	@Autowired
	PowerPlantRepository powerPlantRepository;
	BidRepository bidRepository;
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
		
		BiddingStrategy bs = producer.getChosenStrategy();
		Set<PowerPlantWithholdment> sppw = bs.getSetOfPowerPlantWithholdments();
							
		for(PowerPlantWithholdment powerPlantWithholdment : sppw){
			
			Bid bidPerPowerPlant = new Bid();
			double price = bidPerPowerPlant.getPrice();
			double volume = bidPerPowerPlant.getVolume();
			// get the volumes and prices
						
			EnergyMarket market = new EnergyMarket();
			double clearingprice = market.getClearingPrice();
			// get the clearing price
			
			double cash = producer.getCash();
			cash = prevCash;
			//get the cash
						
			if (clearingprice <= price) {
				
				revenue = volume * clearingprice;
				cash = prevCash + revenue;
										
				if (clearingprice == price) {
				
				// define the fraction of demand that is dispatched, to do!	
				revenue = volume * clearingprice;
				cash = prevCash + revenue;
					
				}
					
			} else {
				
				revenue = 0 ; 
				cash = prevCash + revenue;
				
			}
			
			bidPerPowerPlant.setTime(getCurrentTick());
			bidPerPowerPlant.setBidder(producer);
			
		} 
	}

	
	//TODO: You should never ever have getters/setters in a role class.
	public double getPrevCash() {
		return prevCash;
	}

	public void setPrevCash(double prevCash) {
		this.prevCash = prevCash;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
}	
		
		
		

