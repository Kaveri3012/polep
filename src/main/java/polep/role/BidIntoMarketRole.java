package polep.role;

import org.springframework.beans.factory.annotation.Autowired;

import polep.domain.agent.EnergyProducer;
import polep.domain.market.Bid;
import polep.domain.market.BiddingStrategy;
import polep.domain.market.StrategyElement;
import polep.domain.technology.PowerPlant;
import polep.repository.PowerPlantRepository;
import agentspring.role.AbstractRole;
import agentspring.role.Role;

public class BidIntoMarketRole  extends AbstractRole<EnergyProducer> implements Role<EnergyProducer> {
	
	@Autowired
	PowerPlantRepository powerPlantRepository;
	
	
    public void act(EnergyProducer producer){
    	
    	double realVolume;
    	double marginalCost;
    	Bid bidPerPowerPlant = new Bid(); 
    	BiddingStrategy thisBiddingStrategy = new BiddingStrategy(); 
    	StrategyElement chosenSE = new StrategyElement();
    	
    	//how to access powerPlantSet of a certain energyproducer? 

    	
    	for (PowerPlant plant : powerPlantRepository.findAllByEnergyProducer()){
    		
    		realVolume = plant.getCapacity(); 
    		marginalCost = plant.getPrice();
    		
    		chosenSE = thisBiddingStrategy.getChosenStrategy();
    		
    		bidPerPowerPlant.setVolume(realVolume*(1-chosenSE.getWithholdment())); 
    		
    		bidPerPowerPlant.setPrice(marginalCost*thisBiddingAgent.getPriceMarkUp()); 
    			
    	
    }
	
	//for every power plant
	
	
}
