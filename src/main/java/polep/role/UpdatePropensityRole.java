package polep.role;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.EnergyProducer;
import polep.domain.market.BiddingStrategy;
import polep.domain.market.PowerPlantWithholdment;
import polep.repository.BidRepository;
import polep.repository.EnergyProducerRepository;
import polep.repository.PowerPlantRepository;
import agentspring.role.AbstractRole;
import agentspring.role.Role;
import agentspring.role.ScriptComponent;

//import com.google.common.collect.CustomConcurrentHashMap.Strategy;

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
	
	/* TODO: As a convention it is better not to define variables outside of methods in
	* role classes. */
	
	Set<BiddingStrategy> strategySet;
		
    @Autowired
    PowerPlantRepository powerPlantRepository;
    
    @Autowired
    BidRepository bidRepository;
	
    @Autowired
    EnergyProducerRepository energyProducerRepository;
  
    @Transactional
    public void act(EnergyProducer producer){ 	
    	   	
    	double experimentationParameter = producer.getExperimentationParameter();
    	double recencyParameter = producer.getRecencyParameter();	
    	double cash = producer.getCash();
    	double experiencefunction = producer.getExperiencefunction();
    	
    	Set<BiddingStrategy> strategy = producer.getBiddingStrategySet();
    	double propensity = ((BiddingStrategy) strategy).getPropensity();	
    	  		   	  	
    	double prevCash = producer.getPrevCash() ;
    	BiddingStrategy chosenstrategy = producer.getChosenStrategy();
		strategySet = producer.getBiddingStrategySet();
		BiddingStrategy[] strategyArray = (BiddingStrategy[]) strategySet.toArray();
		
		/* TODO: This works, but it is easier to call:
		* for(Strategy strategy : strategySet)
		* in that case you need to exchange the strategyArray.length function though */
		
		for (double i = 0; i < strategyArray.length; i++){ 
		   				
    		if (strategy == chosenstrategy)  {
        	
    		experiencefunction = (cash-prevCash) * (1-experimentationParameter); 
        	((BiddingStrategy) strategy).setPropensity((1-recencyParameter)*propensity+experiencefunction); 
        	
        	}
        	else {
        	
        	experiencefunction = (cash-prevCash) * (experimentationParameter/(strategyArray.length-1)); 
        	((BiddingStrategy) strategy).setPropensity((1-recencyParameter)*propensity+experiencefunction);

        	}
    		
    		// should we save the propensities in a repository?
    		
		}	
    }
}    
    