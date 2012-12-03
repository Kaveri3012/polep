package polep.domain.market;

@NodeEntity
public class StrategyElement {

	private double witholdment;
	
	@RelationshipTo(type = "STRATEGY_POWERPLANT", elementClass=PowerPlant.class, direction=Direction.OUTGOING)
	private PowerPlant powerplant;
	
}
