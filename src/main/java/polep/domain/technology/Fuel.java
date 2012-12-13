package polep.domain.technology;

import org.springframework.data.neo4j.annotation.NodeEntity;

import agentspring.simulation.SimulationParameter;

@NodeEntity
public class Fuel {
	
	@SimulationParameter(label = "FUEL_PRICE", from = 0, to = 500)
	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
