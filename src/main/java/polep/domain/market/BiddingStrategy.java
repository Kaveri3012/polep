package polep.domain.market;

@NodeEntity
public class BiddingStrategy {

	private double propensity;
	
	@RelationshipTo(type = "STRATEGY_ELEMENT", elementClass = StrategyElement.class, direction=Direction.OUTGOING)
	Set<StrategyElement> setOfStrategyElements;
}
