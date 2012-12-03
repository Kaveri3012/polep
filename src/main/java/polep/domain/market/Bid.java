package polep.domain.market;

import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity//makes sure its integrated in the database
public class Bid {

	double volume;
	double price;
	float percentageWithholdment; 
	
	//call BidIntoMarket.ComputeProbablityChoiceof strategy
	
}
