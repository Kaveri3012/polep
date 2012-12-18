package polep.role;

import static org.junit.Assert.assertTrue;

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

	@Before
	@Transactional
	public void setUp() throws Exception {
	}

	@Test
	public void checkRegulatorRoleFunctionality(){


		// create variables that are required as input
		
		Regulator regulator = new Regulator();	
		regulator.setFine(1000);
		
		EnergyProducer energyProducer1 = new EnergyProducer();
		energyProducer1.setCash(50000);
		PowerPlant plant1 = new PowerPlant();
    	PowerPlant plant2 = new PowerPlant();
    	PowerPlant plant3 = new PowerPlant();
    	    	
    	plant1.setCapacity(500);
    	plant2.setCapacity(500);
    	plant3.setCapacity(500);
    	
    	plant1.setEfficiency(0.2);
    	plant2.setEfficiency(0.2);
    	plant3.setEfficiency(0.2);
    	
    	plant1.persist(); // Saves it to the database
    	plant2.persist();
    	plant3.persist();
    	
    	energyProducer1.setPriceMarkUp(0.1);
		
		EnergyProducer energyProducer2 = new EnergyProducer();
		energyProducer2.setCash(50000);
		
		EnergyProducer energyProducer3 = new EnergyProducer();
		energyProducer3.setCash(50000);
		
		EnergyProducer energyProducer4 = new EnergyProducer();
		energyProducer4.setCash(50000);
		
		energyProducer1.persist();
		energyProducer2.persist();
		energyProducer3.persist();
		energyProducer4.persist();



		// create and run role    	

		RegulatorRole regulatorRole = new RegulatorRole();
		regulatorRole.act(regulator);

		// test using assert   	


	}




}


