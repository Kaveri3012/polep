package polep.domain.market;

@NodeEntity//makes sure its integrated in the database
public class Bid {

	double volume;
	double price;
	float percentageWithholdment; 
	
	//call BidIntoMarket.ComputeProbablityChoiceof strategy
	
}
