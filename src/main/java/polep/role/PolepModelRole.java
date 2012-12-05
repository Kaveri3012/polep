package polep.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import polep.domain.agent.PolepModel;
import agentspring.role.AbstractRole;
import agentspring.role.Role;
import agentspring.role.ScriptComponent;



@ScriptComponent
public class PolepModelRole extends AbstractRole<PolepModel> implements Role<PolepModel> {
	
    static Logger logger = LoggerFactory.getLogger(PolepModelRole.class);

    public void act(PolepModel model){
	

/*    	
    	 In each time step the following script is run
			
			<----- BidIntoMarketRole----->
    		Each EnergyProducer bids into the market by
    			Establishes the (RealVolume, MarginalCost) for each of his Power Plants by
    				RealVolume: Getting the Capacity of the Power Plant
    				MarginalCost: Get the fuel price of the power plant / get Efficiency of the Power Plant
    			Establishes a Bid (instance of Bid class) for each PowerPlant, by
    				Volume in Bid class:
    				EnergyProducer has his Strategies
    				For each Strategy:
    				generate choice probability for each strategy using Roth Erev Algorithm (PropensityofStrategy / Sum of all StrategyPropensities) [step two algorithm]
    				Select an action k according to the current choice probability distribution. [step 3 algorithm]
    			
    				Pj(t) = qj(t)/(sigma (m from 1 to N) : qm(t))
    		
    				Depending upon the selected strategy calculate the Volume for each power plant : Strategy.getPowerPlant.getWitholdment*RealCapacity
    				Price in bid class: Getting the marginal cost multiplying by (1+Price Markup of producer)
    				Establish Volume-Price pair
				
				
				<------ ClearMarketRole ----->
    			The APX clears the market by:
    				Search for bids of the current time step from the graph database
    				Sort bids according to bid price
    				Determines market clearing price depending on demand.
    				Store ClearingPrice in database


				<------ DispatchPowerPlantRole------>
    			PowerPlant owners dispatch power:
    				Determine volume for dispatch = get ClearingPrice from database and compare with own bids.
    				Determine dispatch order depending upon marginal cost of generation and dispatch volume
    				Store Cash in PrevCash [think of the profit made by withholdment for determining the fine amount]
    				Update cash = cash + Revenue (double)
    				Revenues  = ClearingPrice*Volume 



				<--- RegulatorRole ----->
    			Regulator controls Powerplant owner:
    				Throw a weighted coin (probability to control) to select owners for control (this is to simulate imperfect knowledge of the regulator)
    				Compare Bid volume with installed capacity
    				If Bid volume is less than installed capacity = Fine
    				Fine is property of regulator
    				Update Cash = Cash - Fine
    			
    			<---- UpdatePropensityRole ---->
    			Update learning algorithm for all producers. 
    				Update propensities for all actions using the reward r (=cash - PrevCash)
    				
    				The chosen strategy is k, 
    				qj(t+1) =(1-phi) * qj(t) +Ej(epsilon,j,k,t)  	
    				Ej(epsilon,k,t) = rk(t) [1-epsilon]  				if j = k
    				Ej(epsilon,k,t) = rk(t) [ epsilon/(N-1) ]			if j != k*/
    }
    
}