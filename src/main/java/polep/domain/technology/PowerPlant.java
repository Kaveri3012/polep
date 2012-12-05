package polep.domain.technology;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import polep.domain.agent.RelatedTo;

@NodeEntity
public class PowerPlant {

    @Indexed
    double price;
    // @Indexed
    String nameTechnology;
    double capacity;
    double efficiency; 
    
    @RelatedTo(type = "USES FUEL", elementClass = Fuel.class, direction = Direction.OUTGOING)
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

}
