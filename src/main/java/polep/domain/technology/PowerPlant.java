package polep.domain.technology;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class PowerPlant {

    @Indexed
    private double price;
    // @Indexed
    private String nameTechnology;
    private double capacity;
    private double efficiency; 
    
    @RelatedTo(type = "USES_FUEL", elementClass = Fuel.class, direction = Direction.OUTGOING)
    public Fuel thisFuel;
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return nameTechnology;
    }

    public void setName(String name) {
        this.nameTechnology = name;
    }

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	public Fuel getThisFuel() {
		return thisFuel;
	}

	public void setThisFuel(Fuel thisFuel) {
		this.thisFuel = thisFuel;
	}
	
	public double getMarginalCost(){
		return this.getThisFuel().getPrice()/this.efficiency;
	}

}
