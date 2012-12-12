package polep.role;

import agentspring.role.AbstractRole;
import polep.domain.agent.Regulator;

/*<--- RegulatorRole: Prad ----->
Regulator controls Powerplant owner:
	Throw a weighted coin (probability to control) to select owners for control (this is to simulate imperfect knowledge of the regulator)
	Compare Bid volume with installed capacity
	If Bid volume is less than installed capacity = Fine
	Fine is property of regulator
	Update Cash = Cash - Fine */


public abstract class RegulatorRole extends AbstractRole<Regulator> {

}
