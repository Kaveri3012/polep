package polep.domain.agent;

import java.util.Set;

import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import agentspring.agent.AbstractAgent;
import agentspring.agent.Agent;
import agentspring.simulation.SimulationParameter;

@NodeEntity
public class Regulator extends AbstractAgent implements Agent {

	//TODO: There are still some properties missing (as compared to mind map).
    
    @SimulationParameter(label = "Agents Cash Balance", from = 1, to = 100)
    double cash;
    @SimulationParameter(label = "Amount of fine", from = 1, to = 100000)
    double fine;
    String name;
    @SimulationParameter(label = "Probability to check", from = 1, to = 100000)
    double probabilityToCheck;


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
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}


}
