package polep.role;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.EnergyProducer;
import polep.domain.market.BiddingStrategy;
import polep.domain.market.EnergyMarket;
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
	
	Set<BiddingStrategy> strategySet;
	public double experiencefunction;
	
    @Autowired
    PowerPlantRepository powerPlantRepository;
	BidRepository bidRepository;
	EnergyProducerRepository energyProducerRepository;
  
    @Transactional
    public void act(EnergyProducer producer){ 	
    	   	
    	double experimentationParameter = producer.getExperimentationParameter();
    	double recencyParameter = producer.getRecencyParameter();	
    	double cash = producer.getCash();
    	
    	BiddingStrategy strategy = new BiddingStrategy();
    	double propensity = strategy.getPropensity();
    	 	
    	DispatchPowerPlantRole dispatch = new DispatchPowerPlantRole();
    	double prevCash = dispatch.getPrevCash() ;
    	
    	BiddingStrategy chosenstrategy = producer.getChosenStrategy();
		strategySet = producer.getBiddingStrategySet();
		BiddingStrategy[] strategyArray = (BiddingStrategy[]) strategySet.toArray();
		
		for (double i = 0; i < strategyArray.length; i++) {
    				
    		if (strategy == chosenstrategy)  {
        	
    		experiencefunction = (cash-prevCash) * (1-experimentationParameter); 
        	strategy.setPropensity((1-recencyParameter)*propensity+experiencefunction); 
        	
        	}
        	else {
        	
        	experiencefunction = (cash-prevCash) * (experimentationParameter/(strategyArray.length-1)); 
        	strategy.setPropensity((1-recencyParameter)*propensity+experiencefunction);

        	}	
		}	
    }
}    
    