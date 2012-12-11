package polep.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.EnergyProducer;
import polep.domain.market.Bid;
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
	BidRepository bidRepository;
	EnergyProducerRepository energyProducerRepository;
	
	@Transactional
	public void act(EnergyProducer producer){
		
		double marginalCost = 0;
		double realVolume;
		double clearingPrice;
										
		for (PowerPlant plant : producer.getPowerPlantSet()){ 
		
			plant.getMarginalCost();
			clearingPrice = EnergyMarket.getClearingPrice();
			
			if ( marginalCost <= clearingPrice ) {
			
			
				
			//Bid bidPerPowerPlant = new Bid();
				
			//dispatchPerPowerPlant.setPrice(marginalCost);  
			//dispatchPerPowerPlant.setVolume(realVolume);		
			//dispatchPerPowerPlant.persist();
			//revenues = ClearingPrice * get;
			
		} else {
			
			//bidPerPowerPlant.setPrice(marginalCost);
			//bidPerPowerPlant.setVolume(realVolume);
			//bidPerPowerPlant.persist();
		} 
		}
	}
}
