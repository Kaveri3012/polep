package polep.domain.agent;



import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import polep.domain.technology.PowerPlant;

import agentspring.agent.AbstractAgent;
import agentspring.agent.Agent;
import agentspring.simulation.SimulationParameter;

/**
 * @author Ruben Verweij
 * working on it today
 */


@NodeEntity
public class EnergyProducer extends AbstractAgent implements Agent {

    
    @SimulationParameter(label = "Agents Cash Balance", from = 1, to = 100)
    private double cash;
    
    @RelatedTo(type = "OWNS", elementClass = PowerPlant.class, direction = Direction.OUTGOING)
    private Set<PowerPlant> powerPlantSet;
    
    private double recencyParameter;
    
    private double priceMarkUp;
    
    String name;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

	public double getRecencyParameter() {
		return recencyParameter;
	}

	public void setRecencyParameter(double recencyParameter) {
		this.recencyParameter = recencyParameter;
	}

	public double getPriceMarkUp() {
		return priceMarkUp;
	}

	public void setPriceMarkUp(double priceMarkUp) {
		this.priceMarkUp = priceMarkUp;
	}

	public Set<PowerPlant> getPowerPlantSet() {
		return powerPlantSet;
	}

	public void setPowerPlantSet(Set<PowerPlant> powerPlantSet) {
		this.powerPlantSet = powerPlantSet;
	}
   

}