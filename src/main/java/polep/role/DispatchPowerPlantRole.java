package polep.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.EnergyProducer;
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
		
		double marginalCost;
		double realVolume;
		double dispatchVolume = 0;
		double clearingPrice;
		double cash;
		double revenuePerPowerPlant = 0;
		double revenue = 0;
		double prevCash = 0;
		
		cash = prevCash;
		// set cash is prevCash
										
		for (PowerPlant plant : producer.getPowerPlantSet()){ 
		
			// define the plants who dispatch their power
			marginalCost = plant.getMarginalCost();
			clearingPrice = EnergyMarket.getClearingPrice();
			realVolume = plant.getCapacity();
			
			if ( marginalCost <= clearingPrice ) {
			
			realVolume = dispatchVolume;
			// if this is the case the bid is transformed to dispatch bid
					
			}else {
			
			realVolume = dispatchVolume;
			dispatchVolume = 0;
			// when this is the case, the bid is set to zero
			
			} 
		}
		
		for (PowerPlant plant : producer.getPowerPlantSet()) {
			
			// define the revenue
			clearingPrice = EnergyMarket.getClearingPrice();
			revenuePerPowerPlant = dispatchVolume * clearingPrice;
			revenue =+ revenuePerPowerPlant;
		}
		
		// define the revenue per producer, how to that?
		cash = prevCash + revenue ;
	}
}
