package polep.domain.agent;

/**
 * @author RubenVerweij
 * In this class the EnergyProducer is defined. This producer has the following properties; cash, a set of power plants, a recency parameter, a experimentation
 * parameter and a price mark up
 * 
 * An EnergyProducer is supposed to have a set of strategies. 
 * Each strategy has a set of StrategyElements (which contains 
 * a link to one power plant, and the witholdment for that power plant). 
 * He also is supposed to have one currentStrategy, that is the Strategy
 * of the Set of Strategies he is currently using.
 * 
 */

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import polep.domain.technology.PowerPlant;
import agentspring.agent.AbstractAgent;
import agentspring.agent.Agent;
import agentspring.simulation.SimulationParameter;

@NodeEntity
public class EnergyProducer extends AbstractAgent implements Agent {
	
	//TODO: These should be moved out of this class, as well as their getters and setters.
	EnergyProducer producer1 = new EnergyProducer();
	EnergyProducer producer2 = new EnergyProducer();
	EnergyProducer producer3 = new EnergyProducer();
    
        
    @RelatedTo(type = "OWNS", elementClass = PowerPlant.class, direction = Direction.OUTGOING)
    private Set<PowerPlant> powerPlantSet;
    
    @SimulationParameter(label = "Agents Cash Balance", from = -1e5, to = 1e5)
    private double cash;
    
    /* TODO: Thhis should be defined like the Set<PowerPlant> above. The correct
     * class it should refer to is polep.domain.market.BiddingStrategy. Sorry for the
     * confusion. */
 	@SimulationParameter(label = "The set of strategies", from = 0, to = 1)
    private double setOfStrategies[];
    // one set of strategies   
    
    @SimulationParameter(label = "The strategy", from = 0, to = 8)
    private double strategy[];
    // 9 strategies
    
    /* TODO: see setOfStrategies above. The correct class is polep.domain.market.BiddingStrategy */
    @SimulationParameter(label = "The strategy element", from = 0, to = 5)
    private double strategyElement;
    // contains the powerplant 1,2,3 and the three percentages of withholdment
    
    // TODO: see above, same class.
    @SimulationParameter(label = "The chosen strategy", from = 0, to = 1)
    private double chosenStrategy[];
    // chosen strategy at the moment
        
    @SimulationParameter(label = "The recency parameter", from = 0, to = 1)
    private double recencyParameter;
    
    @SimulationParameter(label = "The experimentation parameter", from = 0, to = 1)
    private double experimentationParameter;
    
    @SimulationParameter(label = "The price markup", from = 0, to = 1)
    private double priceMarkUp;
    
    public String name;

    
    // get and setters
    
	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public Set<PowerPlant> getPowerPlantSet() {
		return powerPlantSet;
	}

	public void setPowerPlantSet(Set<PowerPlant> powerPlantSet) {
		this.powerPlantSet = powerPlantSet;
	}

	public double getRecencyParameter() {
		return recencyParameter;
	}

	public void setRecencyParameter(double recencyParameter) {
		this.recencyParameter = recencyParameter;
	}

	public double getExperimentationParameter() {
		return experimentationParameter;
	}

	public void setExperimentationParameter(double experimentationParameter) {
		this.experimentationParameter = experimentationParameter;
	}

	public double getPriceMarkUp() {
		return priceMarkUp;
	}

	public void setPriceMarkUp(double priceMarkUp) {
		this.priceMarkUp = priceMarkUp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EnergyProducer getProducer1() {
		return producer1;
	}

	public void setProducer1(EnergyProducer producer1) {
		this.producer1 = producer1;
	}

	public EnergyProducer getProducer2() {
		return producer2;
	}

	public void setProducer2(EnergyProducer producer2) {
		this.producer2 = producer2;
	}

	public EnergyProducer getProducer3() {
		return producer3;
	}

	public void setProducer3(EnergyProducer producer3) {
		this.producer3 = producer3;
	}

	public double[] getSetOfStrategies() {
		return setOfStrategies;
	}

	public void setSetOfStrategies(double[] setOfStrategies) {
		this.setOfStrategies = setOfStrategies;
	}

	public double[] getChosenStrategy() {
		return chosenStrategy;
	}

	public void setChosenStrategy(double[] chosenStrategy) {
		this.chosenStrategy = chosenStrategy;
	}

}