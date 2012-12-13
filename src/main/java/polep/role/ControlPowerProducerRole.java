package polep.role;

//TODO: These class seems to be superfluos, since we also have a Regulator role.

import polep.domain.technology.PowerPlant;
import agentspring.role.AbstractRole;
import polep.domain.agent.EnergyProducer;

public abstract class ControlPowerProducerRole extends AbstractRole<EnergyProducer> {
	
	



	public double calculateMarginalCost(PowerPlant powerPlant) {
		double mc = 0d;
		// fuel cost
		mc += calculateMarginalCost(powerPlant);

		logger.info("Margincal cost for plant {} is {}", powerPlant.getName(), mc);
		return mc;

	}
}