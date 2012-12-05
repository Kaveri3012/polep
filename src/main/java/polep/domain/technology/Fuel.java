package polep.domain.technology;

import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Fuel {
	
	public double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
