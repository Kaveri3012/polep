/**
 * 
 */
package polep.domain.market;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * @author JCRichstein
 *
 */
public class Strategy {
	
	@RelatedTo(type="STRATEGY_STRATEGYELEMENT", elementClass=StrategyElement.class, direction=Direction.OUTGOING)
	private Set<StrategyElement> strategyElementSet;

}
