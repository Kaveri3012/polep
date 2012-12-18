package polep.role;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import polep.domain.agent.EnergyProducer;
import polep.domain.agent.Regulator;
import polep.domain.market.Bid;
import polep.domain.technology.Fuel;
import polep.domain.technology.PowerPlant;

import polep.repository.BidRepository;
import polep.repository.EnergyProducerRepository;
import polep.repository.PowerPlantRepository;

/**
 * @Prad
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/polep-test-context.xml"})
@Transactional
public class RegulatorRoleTest {

	@Autowired EnergyProducerRepository energyProducerRepository;

	@Autowired PowerPlantRepository plantRepository;

	@Autowired BidRepository bidRepository;
	Logger logger = Logger.getLogger(RegulatorRoleTest.class);
	
	@Autowired
	RegulatorRole regulatorRole;

	@Before
	@Transactional
	public void setUp() throws Exception {
	}

	@Test
	public void checkRegulatorRoleFunctionality(){


		// create variables that are required as input
		
		Regulator regulator = new Regulator();	
		regulator.setFine(1000);
		regulator.setProbabilityToCheck(1);
		regulator.setAcceptableWitholdment(0.1);
		
		EnergyProducer energyProducer1 = new EnergyProducer();
		energyProducer1.setCash(50000);
		
		PowerPlant plant1 = new PowerPlant();
    	PowerPlant plant2 = new PowerPlant();
    	PowerPlant plant3 = new PowerPlant();
    	
    	plant1.setCapacity(500);
    	plant2.setCapacity(500);
    	plant3.setCapacity(500);
    	
    	plant1.persist(); // Saves it to the database
    	plant2.persist();
    	plant3.persist();
    	
    	   	
    	Set<PowerPlant> powerPlants1 = new HashSet<PowerPlant>();
    	
    	powerPlants1.add(plant1);
    	powerPlants1.add(plant2);
    	powerPlants1.add(plant3);
    	
    	energyProducer1.setPowerPlantSet(powerPlants1);	
    	
		
		EnergyProducer energyProducer2 = new EnergyProducer();
		energyProducer2.setCash(50000);
		
		PowerPlant plant4 = new PowerPlant();
    	PowerPlant plant5 = new PowerPlant();
    	PowerPlant plant6 = new PowerPlant();
    	
    	plant4.setCapacity(200);
    	plant5.setCapacity(300);
    	plant6.setCapacity(100);
    	
    	plant4.persist(); // Saves it to the database
    	plant5.persist();
    	plant6.persist();
    	
    	   	
    	Set<PowerPlant> powerPlants2 = new HashSet<PowerPlant>();
    	
    	powerPlants2.add(plant4);
    	powerPlants2.add(plant5);
    	powerPlants2.add(plant6);
    	
    	energyProducer2.setPowerPlantSet(powerPlants2);	
		
		EnergyProducer energyProducer3 = new EnergyProducer();
		energyProducer3.setCash(50000);
		
		PowerPlant plant7 = new PowerPlant();
    	PowerPlant plant8 = new PowerPlant();
    	PowerPlant plant9 = new PowerPlant();
    	
    	plant7.setCapacity(700);
    	plant8.setCapacity(200);
    	plant9.setCapacity(400);
    	
    	plant7.persist(); // Saves it to the database
    	plant8.persist();
    	plant9.persist();
    	
    	Set<PowerPlant> powerPlants3 = new HashSet<PowerPlant>();
    	
    	powerPlants3.add(plant7);
    	powerPlants3.add(plant8);
    	powerPlants3.add(plant9);
    	
    	energyProducer3.setPowerPlantSet(powerPlants3);
    	
    	Bid bid1 = new Bid();
		bid1.setVolume(1000);
		bid1.setPrice(15);
		bid1.setTime(0);
		bid1.setBidder(energyProducer1);
		bid1.persist();

		Bid bid2 = new Bid();
		bid2.setVolume(500);
		bid2.setPrice(20);
		bid2.setTime(0);
		bid2.setBidder(energyProducer1);
		bid2.persist();

		Bid bid3 = new Bid();
		bid3.setVolume(200);
		bid3.setPrice(15);
		bid3.setTime(0);
		bid3.setBidder(energyProducer2);
		bid3.persist();

		Bid bid4 = new Bid();
		bid4.setVolume(100);
		bid4.setPrice(25);
		bid4.setTime(0);
		bid4.setBidder(energyProducer2);
		bid4.persist();
		
		Bid bid5 = new Bid();
		bid5.setVolume(700);
		bid5.setPrice(25);
		bid5.setTime(0);
		bid5.setBidder(energyProducer3);
		bid5.persist();
		
		Bid bid6 = new Bid();
		bid6.setVolume(550);
		bid6.setPrice(15);
		bid6.setTime(0);
		bid6.setBidder(energyProducer3);
		bid6.persist();
		
	
		energyProducer1.persist();
		energyProducer2.persist();
		energyProducer3.persist();
		


		// create and run role    	
		regulatorRole.act(regulator);

		// test using assert   	
		
		assertTrue("Check producer 1", energyProducer1.getCash()==50000);
		assertTrue("Check producer 2",energyProducer2.getCash()==49000);
		assertTrue("Check producer 3",energyProducer3.getCash()==50000);


	}




}


