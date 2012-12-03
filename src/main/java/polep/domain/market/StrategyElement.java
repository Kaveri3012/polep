package polep.domain.market;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import polep.domain.technology.PowerPlant;

@NodeEntity
public class StrategyElement {

	private double witholdment;
	
	@RelatedTo(type = "STRATEGY_POWERPLANT", elementClass=PowerPlant.class, direction=Direction.OUTGOING)
	private PowerPlant powerplant;
	
}
