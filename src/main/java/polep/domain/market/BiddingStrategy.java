package polep.domain.market;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class BiddingStrategy {

	private double propensity;
	
	@RelatedTo(type = "STRATEGY_ELEMENT", elementClass = StrategyElement.class, direction=Direction.OUTGOING)
	Set<StrategyElement> setOfStrategyElements;
}
