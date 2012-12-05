package polep.role;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import polep.domain.agent.PolepModel;
import agentspring.role.AbstractRole;
import agentspring.role.Role;
import agentspring.role.ScriptComponent;

/**
 * @author Ruben Verweij
 * Working on it today
 */


@ScriptComponent
public class UpdatePropensityRole extends AbstractRole<PolepModel> implements Role<PolepModel> {
	
    static Logger logger = LoggerFactory.getLogger(UpdatePropensityRole.class);

    public void act(PolepModel model){
    	
    	
    }
    
}