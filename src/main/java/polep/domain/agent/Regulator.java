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

	// 0.5 will is the value used for unbiased coin
	@SimulationParameter(label = "Probability to check", from = 0, to = 1)
	double probabilityToCheck;

	@SimulationParameter(label = "Percentage of witholdment that is acceptable", from = 0, to = 1)
	double acceptableWitholdment;

	public double getAcceptableWitholdment() {
		return acceptableWitholdment;
	}

	public void setAcceptableWitholdment(double acceptableWitholdment) {
		this.acceptableWitholdment = acceptableWitholdment;
	}

	public double getProbabilityToCheck() {
		return probabilityToCheck;
	}

	public void setProbabilityToCheck(double probabilityToCheck) {
		this.probabilityToCheck = probabilityToCheck;
	}

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
