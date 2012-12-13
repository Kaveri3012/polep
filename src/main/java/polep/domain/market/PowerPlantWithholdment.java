package polep.domain.market;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import polep.domain.technology.PowerPlant;

@NodeEntity
public class PowerPlantWithholdment {

	private double withholdment;
	
	@RelatedTo(type = "STRATEGY_POWERPLANT", elementClass=PowerPlant.class, direction=Direction.OUTGOING)
	private PowerPlant powerplant;

	public PowerPlantWithholdment() {
		super();
	}

	/**
	 * @return the withholdment
	 */
	public double getWithholdment() {
		return withholdment;
	}

	/**
	 * @param withholdment the withholdment to set
	 */
	public void setWithholdment(double witholdment) {
		this.withholdment = witholdment;
	}

	/**
	 * @return the powerplant
	 */
	public PowerPlant getPowerplant() {
		return powerplant;
	}

	/**
	 * @param powerplant the powerplant to set
	 */
	public void setPowerplant(PowerPlant powerplant) {
		this.powerplant = powerplant;
	}
	
	
	
}
