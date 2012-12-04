package polep.domain.agent;

import java.util.Set;

import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import agentspring.agent.AbstractAgent;
import agentspring.agent.Agent;
import agentspring.simulation.SimulationParameter;

@NodeEntity
public class Regulator extends AbstractAgent implements Agent {

    
    @SimulationParameter(label = "Agents Cash Balance", from = 1, to = 100)
    double cash;
    double Fine;
    String name;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getFine() {
		return Fine;
	}

	public void setFine(double fine) {
		Fine = fine;
	}


}
