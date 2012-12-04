package polep.domain.market;

import org.springframework.data.neo4j.annotation.NodeEntity;

/*
 * Establishes a volume-price pair for every power plant owned by an energy producer.
 * The volume price pair is established based on the bidding strategy returned.  
 */
@NodeEntity//makes sure its integrated in the database
public class Bid {

	double volume;
	double price;
	double percentageWithholdment; 
	
	//call a method, 'computeChoice of strategy'?
	
}
