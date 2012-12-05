package polep.domain;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.technology.Fuel;
import polep.domain.technology.PowerPlant;
import polep.repository.EnergyProducerRepository;
import polep.repository.PowerPlantRepository;

/**
 * @author JCRichstein
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/polep-test-context.xml"})
@Transactional
public class PowerPlantTest {
    
    @Autowired Neo4jOperations template;
    @Autowired PowerPlantRepository powerPlantRepository;
    @Autowired EnergyProducerRepository energyProducerRepository;
	
    @Before
    @Transactional
    public void setUp() throws Exception {
    }
    
    @Test
    public void checkBasicFunctionalityOfPowerPlantClass(){
    	
    	PowerPlant plant = new PowerPlant();
    	Fuel fuel = new Fuel();
    	fuel.setPrice(20);
    	fuel.persist();
    	plant.setCapacity(500);
    	plant.setThisFuel(fuel);
    	plant.setEfficiency(0.33);
    	plant.persist();
    	
    	PowerPlant plantFromDB = powerPlantRepository.findAll().iterator().next();
    	
    	assertEquals("Check if plants are equal if template.createNodeAs: ", 1, template.count(PowerPlant.class));
    	assertTrue(plantFromDB.getCapacity() == 500);
    	assertTrue(plantFromDB.getThisFuel().getPrice() == fuel.getPrice());
    	assertTrue(plantFromDB.getEfficiency() == 0.33);
    	//Calculate marginal cost = 20/0.33 = 60.61
    	assertEquals(60.61, plantFromDB.getMarginalCost(), 0.05);
    }
    

}	
