package polep.role;


import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.EnergyProducer;
import polep.domain.market.BiddingStrategy;
import polep.domain.market.EnergyMarket;
import polep.domain.market.PowerPlantWithholdment;
import polep.domain.technology.PowerPlant;
import polep.repository.BidRepository;
import polep.repository.EnergyProducerRepository;
import polep.repository.PowerPlantRepository;
import agentspring.role.AbstractRole;
import agentspring.role.Role;
import agentspring.role.ScriptComponent;

/**
 * @author RubenVerweij
 * This role updates the propensities. It is the last step of the Erev-Roth Algorithm.
 * 
 * The role is the following;
 * Update propensities for all actions using the reward r (=cash - PrevCash)	
 * The chosen strategy is k, 
 * qj(t+1) =(1-phi) * qj(t) +Ej(epsilon,j,k,t)  	
 * Ej(epsilon,k,t) = rk(t) [1-epsilon]  				if j = k
 * Ej(epsilon,k,t) = rk(t) [ epsilon/(N-1) ]			if j != k
 **/
 
@ScriptComponent
public class UpdatePropensityRole extends AbstractRole<EnergyProducer> implements Role<EnergyProducer> {
	
    static Logger logger = LoggerFactory.getLogger(UpdatePropensityRole.class);
      
    @Autowired
    PowerPlantRepository powerPlantRepository;
	BidRepository bidRepository;
	EnergyProducerRepository energyProducerRepository;
  
    @Transactional
    public void act(EnergyProducer producer){
    	
    	//TODO Once we clearly defined a common Strategy-Implementation, some things need to be fixed
    	// here.
    	double recencyParameter; // from producer
    	double experimentationParameter; // from producer
    	double strategy[]; // from producer
    	Set<PowerPlantWithholdment> setOfStrategyElements; // from producer
    	double setOfStrategies[];
       	double chosenStrategy[]; // from bidding strategy
    	double propensity; // from bidding strategy
    	double experienceFunction;
    	double updatePropensity;
    	double reward;
    	
    	//TODO this needs to come out of the energyProducer via a getter-setter method.
    	Set<PowerPlantWithholdment> allStrategies = BiddingStrategy.getSetOfStrategyElements();
    	// get the whole set of strategies
    	
    	//TODO Needs to refer to EnergyProducer.
    	chosenStrategy = BiddingStrategy.getChosenStrategy();
    	// get chosen strategy, is this correctly defined in the bidding strategy?
    	
    	experimentationParameter = producer.getExperimentationParameter();
    	// get exp-parameter
    	
    	recencyParameter = producer.getRecencyParameter();
    	// get rec-parameter
    	
    	propensity = BiddingStrategy.getPropensity();
    	// get propensity
    	
    	// to do get strategyReward from ClearingMarketRole / regulator = Cash - prevCash to be defined		
    	
    	//TODO Looks correct, needs to be adjusted once we adjusted the whole
    	// BiddingStrategy confusion.
    	for (double i = 0; i < setOfStrategies.length; i++) {
    	// iterate for all strategies if the strategy is the chosen strategy 
    				
    		if (strategy = chosenStrategy)  {
        	
    		experienceFunction = reward * (1-experimentationParameter); 
        	updatePropensity.setPropensity((1-recencyParameter)*presentPropensity+experienceFunction); 
        	
        	}
        	else {
        	experienceFunction = reward * (experimentationParameter/(size(SetOfStrategyElements)-1)); 
        	updatePropensity.setPropensity((1-recencyParameter)*presentPropensity+experienceFunction);
        	
        	}   
        } 
    }		
}