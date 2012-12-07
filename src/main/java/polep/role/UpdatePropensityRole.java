package polep.role;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.EnergyProducer;
import polep.repository.EnergyProducerRepository;
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
    EnergyProducerRepository energyProducerRepository;

    @Transactional
    public void act(EnergyProducer producer){
    	
    	allStrategies = thisBiddingStrategy.getSetOfStrategyElements();
    	chosenSE = thisBiddingStrategy.getChosenStrategy(); 
    	presentPropensity = thisBiddingStrategy.getPropensity();
    	// get strategyReward from DispatchPowerPlantRole = Cash - prevCash to be defined
    	experimentationParameter = producer.getExperimentationParameter();
    	recencyParameter = producer.getRecencyParameter();
   
    	if (StrategyElement = chosenSE)  {
    	//experienceFunction = reward * (1-experimentationParameter) 
    	//updatePropensity.setPropensity((1-recencyParameter)*presentPropensity+experienceFunction); 
    	}
    	else {
    	//experienceFunction = reward * (experimentationParameter/(size(SetOfStrategyElements)-1)) 
        //updatePropensity.setPropensity((1-recencyParameter)*presentPropensity+experienceFunction);
    	}   
    } 
}