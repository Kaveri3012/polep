package polep.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.EnergyProducer;
import polep.domain.market.Bid;
import polep.domain.technology.PowerPlant;
import polep.repository.BidRepository;
import polep.repository.ClearingPointRepository;
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
	
	private double dispatchedVolume;
	
	@Autowired
	PowerPlantRepository powerPlantRepository;
	
	@Autowired
	BidRepository bidRepository;
	
	@Autowired
	EnergyProducerRepository energyProducerRepository;
	
	@Autowired
	ClearingPointRepository clearingPointRepository; 
	
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
		
		Iterable<Bid> allBids = bidRepository.findAllBidsPerProducerForTime(producer, getCurrentTick());
		// gives sorted list of bids per producer? list to be defined in repository
		

		double clearingPrice = clearingPointRepository.findTheOneClearingPriceForTime(getCurrentTick());
				
		// getting the revenues		
		for (Bid currentBid:allBids){
			
			double revenue = producer.getRevenue();
			double prevCash = producer.getPrevCash();
			double cash = producer.getCash();	
							
			producer.setTotalAcceptedVolume(currentBid.getVolume()+producer.getTotalAcceptedVolume());		
							
				if (currentBid.getStatus() == Bid.ACCEPTED){
					
					revenue = currentBid.getAcceptedVolume() * clearingPrice;
					producer.setCash(prevCash + revenue);
							
				} 


				if (currentBid.getStatus() == Bid.PARTLY_ACCEPTED){
					
					revenue = currentBid.getAcceptedVolume() * clearingPrice;
					producer.setCash(prevCash + revenue);
		
				}

				else {
				
										
				}
			}	
		
		// should be ordered according to marginal costs
		Iterable<PowerPlant> allPlants = powerPlantRepository.findAllSortedPlantsPerProducerForTime(producer, getCurrentTick());
		
		// physical dispatching 
		double totalCost = producer.getTotalCost();
		double cash = producer.getCash();
		producer.getTotalAcceptedVolume();
		
		for (PowerPlant plant:allPlants){
		
			if( plant.getCapacity() < producer.getTotalAcceptedVolume()){
			
			dispatchedVolume = plant.getCapacity();
			producer.setTotalAcceptedVolume(producer.getTotalAcceptedVolume()-dispatchedVolume);
			producer.setTotalCost(plant.calculateMarginalCost()*dispatchedVolume);
									
			}
			if(plant.getCapacity() > producer.getTotalAcceptedVolume()){
			
			dispatchedVolume = producer.getTotalAcceptedVolume();
			producer.setTotalCost(plant.calculateMarginalCost()*dispatchedVolume);
			producer.setTotalAcceptedVolume(producer.getTotalAcceptedVolume()-dispatchedVolume);
			// should be zero	
			
			}
			else{	
			}
		
		producer.setCash(cash - totalCost);
			
		}	
	}	
	
}			
		
		
		
	

	

		
		
		

