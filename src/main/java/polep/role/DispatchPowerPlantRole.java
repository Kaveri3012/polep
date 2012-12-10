package polep.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.EnergyProducer;
import polep.repository.PowerPlantRepository;
import agentspring.role.AbstractRole;
import agentspring.role.Role;

/**
 * @author Ruben Verweij
 *PowerPlant owners dispatch power:
 *Determine volume for dispatch = get ClearingPrice from database and compare with own bids.
 *Determine dispatch order depending upon marginal cost of generation and dispatch volume
 *Store Cash in PrevCash [think of the profit made by withholdment for determining the fine amount]
 *Update cash = cash + Revenue (double)
 *Revenues  = ClearingPrice*Volume 
 */

public class DispatchPowerPlantRole  extends AbstractRole<EnergyProducer> implements Role<EnergyProducer> {

	@Autowired
	PowerPlantRepository powerPlantRepository;

	@Transactional
	public void act(EnergyProducer producer){
		
		clearingPrice = ClearingMarketRole.getClearingPrice();
		// get clearing price
		
		marginalCost = plant.getMarginalCost();
		// get all marginal costs
		
		producersBids = BidIntoMarketRole.getbidPerPowerPlant();
		// get bids
				
		if (MarginalPrice <= ClearingPrice  ) {
			
			dispatchPerPowerPlant.setPrice(marginalCost) = bidPerPowerPlant.setPrice(marginalCost);  
			dispatchPerPowerPlant.setVolume(realVolume) = bidPerPowerPlant.setVolume(realVolume);		
			dispatchPerPowerPlant.persist() = bidPerPowerPlant.persist();
			revenues = ClearingPrice * get ;
			
		} else {
			
			bidPerPowerPlant.setPrice(marginalCost) = 0;
			bidPerPowerPlant.setVolume(realVolume) = 0;
			bidPerPowerPlant.persist() = 0;
		} 
		
		
		
		

	
	}
}