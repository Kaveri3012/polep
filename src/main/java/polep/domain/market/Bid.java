package polep.domain.market;

import org.springframework.data.neo4j.annotation.NodeEntity;

/*
 * Establishes a volume-price pair for every power plant owned by an energy producer.
 * The volume price pair is established based on the bidding strategy returned.  
 */
@NodeEntity//makes sure its integrated in the database
public class Bid {

	private double volume;
	private double price;
	private double percentageWithholdment;
	
	
	
	
	public double getVolume() {
		return volume;
	}




	public void setVolume(double volume) {
		this.volume = volume;
	}




	public double getPrice() {
		return price;
	}




	public void setPrice(double price) {
		this.price = price;
	}




	public Bid() {
		super();
	}
	
	
	
	
	
	
	
	//call a method, 'computeChoice of strategy'?
	
	
	
}
