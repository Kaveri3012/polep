package polep.role;

import java.util.Set;

import polep.domain.market.BiddingStrategy;
import polep.domain.market.RelatedTo;
import polep.domain.market.StrategyElement;

public class BidIntoMarketRole {
	
	double realVolume;
	double marginalCost;
	Bid bidPerPowerPlant = new Bid(); 
	BiddingStrategy thisBiddingStrategy = new BiddingStrategy(); 
	StrategyElement chosenSE = new StrategyElement();
	
	public String biddingAgent(String nameEnergyProducer){
		thisBiddingAgent = nameEnergyProducer; 
	}
	
	@RelatedTo(type = "PLAYED BY", elementClass = EnergyProducer.class, direction=Direction.OUTGOING)
	EnergyProducer thisBiddingAgent; 
	
	//how to access powerPlantSet of a certain energyproducer? 
	

	
	for (Iterator<powerPlantSet> it = set.iterator(); it.hasNext();){
		powerPlantSet p = it.next(); 
		
		realVolume = p.getCapacity(); 
		marginalCost = p.getPrice();
		
		chosenSE = thisBiddingStrategy.getChosenStrategy();
		
		bidperPowerPlant.volume = realVolume*(1-chosenSE.withholdment); 
		
		bidperPowerPlant.price = marginalCost*thisBiddingAgent.priceMarkUp; 
			
			
	}
	
	
	//for every power plant
	
	
}
