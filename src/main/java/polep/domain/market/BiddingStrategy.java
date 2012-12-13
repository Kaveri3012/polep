package polep.domain.market;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class BiddingStrategy {

	private double propensity;
	private double probability;
	/* TODO The chosenStrategy should be defined in EnergyProducer, and refer to a whole Strategy
	 * not only one strategyElement.
	 */
	StrategyElement chosenStrategy = new StrategyElement(); 
	
	@RelatedTo(type = "STRATEGY_ELEMENT", elementClass = StrategyElement.class, direction=Direction.OUTGOING)
	Set<StrategyElement> setOfStrategyElements;

	public StrategyElement getChosenStrategy() {
		return chosenStrategy;
	}

	public BiddingStrategy() {
		super();
	}
	
	
	
}
