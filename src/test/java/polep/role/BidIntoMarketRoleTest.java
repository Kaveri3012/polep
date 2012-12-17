package polep.role;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.EnergyProducer;
import polep.domain.market.Bid;
import polep.domain.market.BiddingStrategy;
import polep.domain.market.PowerPlantWithholdment;
import polep.domain.technology.Fuel;
import polep.domain.technology.PowerPlant;
import polep.repository.BidRepository;
import polep.repository.EnergyProducerRepository;
import polep.repository.PowerPlantRepository;

/**
 * @author Kaveri
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/polep-test-context.xml"})
@Transactional
public class BidIntoMarketRoleTest {
    
    @Autowired Neo4jOperations template;
    @Autowired PowerPlantRepository powerPlantRepository;
    @Autowired EnergyProducerRepository energyProducerRepository;
    @Autowired BidRepository bidRepository; 
    
    Logger logger = Logger.getLogger(BidIntoMarketRoleTest.class);
	
    @Before
    @Transactional
    public void setUp() throws Exception {
    }
    
    @Test
    public void checkBidIntoMarketFunctionality(){
    	
    	EnergyProducer producer = new EnergyProducer();
    	PowerPlant plant1 = new PowerPlant();
    	PowerPlant plant2 = new PowerPlant();
    	PowerPlant plant3 = new PowerPlant();
    	Fuel fuel1 = new Fuel();
    	Fuel fuel2 = new Fuel();
    	Fuel fuel3 = new Fuel();
    	fuel1.setPrice(20);
    	fuel2.setPrice(30);
    	fuel3.setPrice(40);
    	fuel1.persist(); // Saves it to the database
    	fuel2.persist();
    	fuel3.persist();
    	plant1.setCapacity(500);
    	plant2.setCapacity(500);
    	plant3.setCapacity(500);
    	plant1.setFuel(fuel1);
    	plant2.setFuel(fuel2);
    	plant3.setFuel(fuel3);
    	plant1.setEfficiency(0.2);
    	plant2.setEfficiency(0.2);
    	plant3.setEfficiency(0.2);
    	plant1.setMarginalCost(plant1.calculateMarginalCost());
    	plant2.setMarginalCost(plant2.calculateMarginalCost());
    	plant3.setMarginalCost(plant3.calculateMarginalCost());
    	plant1.persist(); // Saves it to the database
    	plant2.persist();
    	plant3.persist();
    	
    	producer.setPriceMarkUp(0.1);
    	
    	BiddingStrategy chosenStrategy = new BiddingStrategy();
    	PowerPlantWithholdment powerWithholdment1 = new PowerPlantWithholdment(); 
    	PowerPlantWithholdment powerWithholdment2 = new PowerPlantWithholdment(); 
    	PowerPlantWithholdment powerWithholdment3 = new PowerPlantWithholdment(); 
    	
    	powerWithholdment1.setWithholdment(0.05); 
    	powerWithholdment2.setWithholdment(0.1); 
    	powerWithholdment3.setWithholdment(0.15); 
    	
    	powerWithholdment1.setPowerplant(plant1); 
    	powerWithholdment2.setPowerplant(plant2); 
    	powerWithholdment3.setPowerplant(plant3); 
    	
    	
    	
    	producer.setChosenStrategy(chosenStrategy);
    	Set<PowerPlantWithholdment> setOfPowerPlantWithholdments = new HashSet<PowerPlantWithholdment>();
    	
    	setOfPowerPlantWithholdments.add(powerWithholdment1);
    	setOfPowerPlantWithholdments.add(powerWithholdment2);
    	setOfPowerPlantWithholdments.add(powerWithholdment3);
    	
    	  	    	
    	chosenStrategy.setSetOfPowerPlantWithholdments(setOfPowerPlantWithholdments); 
    	
    	Set<PowerPlant> setPowerPlant = producer.getPowerPlantSet();
    	//setPowerPlant.add(plant1);
    	//setPowerPlant.add(plant2);
    	//setPowerPlant.add(plant3);
    	producer.persist();
    	
    	BidIntoMarketRole bidIntoMarketRole = new BidIntoMarketRole();
    	bidIntoMarketRole.act(producer);
    	
    	
    	Iterable<Bid> bids = bidRepository.findAll();
    	
    	for (Bid bid: bids){
    		logger.warn("Price: " + bid.getPrice());
        	logger.warn("Volume: " + bid.getVolume());
    	}
    	
    
    	
    	//assertTrue(bid.getPrice()==20);
    	//assertTrue(bid.getVolume()==500);
    	
    	
    }
    

}	
