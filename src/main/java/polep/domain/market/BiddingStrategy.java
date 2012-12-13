package polep.domain.market;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class BiddingStrategy {

	private double propensity;
	private double probability;
	
	@RelatedTo(type = "STRATEGY_ELEMENT", elementClass = PowerPlantWithholdment.class, direction=Direction.OUTGOING)
	Set<PowerPlantWithholdment> setOfPowerPlantWithholdments;

	public BiddingStrategy() {
		super();
	}

	/**
	 * @return the propensity
	 */
	public double getPropensity() {
		return propensity;
	}

	/**
	 * @param propensity the propensity to set
	 */
	public void setPropensity(double propensity) {
		this.propensity = propensity;
	}

	/**
	 * @return the probability
	 */
	public double getProbability() {
		return probability;
	}

	/**
	 * @param probability the probability to set
	 */
	public void setProbability(double probability) {
		this.probability = probability;
	}

	public Set<PowerPlantWithholdment> getSetOfPowerPlantWithholdments() {
		return setOfPowerPlantWithholdments;
	}

	public void setSetOfPowerPlantWithholdments(
			Set<PowerPlantWithholdment> setOfPowerPlantWithholdments) {
		this.setOfPowerPlantWithholdments = setOfPowerPlantWithholdments;
	}
	
	
	
}
