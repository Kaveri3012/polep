package polep.role;

import static org.junit.Assert.*;

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

import com.hp.hpl.jena.reasoner.rulesys.builtins.AssertDisjointPairs;

import polep.domain.agent.EnergyProducer;
import polep.domain.market.Bid;
import polep.domain.market.ClearingPoint;
import polep.domain.technology.PowerPlant;
import polep.repository.BidRepository;
import polep.repository.ClearingPointRepository;
import polep.repository.EnergyProducerRepository;
import polep.repository.PowerPlantRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/polep-test-context.xml"})
@Transactional

public class DispatchPowerPlantRoleTest {

	@Autowired Neo4jOperations template;
    @Autowired PowerPlantRepository powerPlantRepository;
    @Autowired EnergyProducerRepository energyProducerRepository;
    @Autowired BidRepository bidRepository; 
    @Autowired ClearingPointRepository clearingPointRepository;
    
    @Autowired DispatchPowerPlantRole dppr;
	
    Logger logger = Logger.getLogger(DispatchPowerPlantRoleTest.class);
	
    @Before
    @Transactional
    public void setUp() throws Exception {
    }
    
    
    public void testDispatchPowerPlantRole() {
	
    
    	    	
    	PowerPlant plant1 = new PowerPlant();
    	plant1.setCapacity(500);
    	plant1.setMarginalCost(5);
    	plant1.persist();
    	
    	PowerPlant plant2 = new PowerPlant();
    	plant2.setCapacity(600);
    	plant2.setMarginalCost(7);
    	plant2.persist();
    	
    	
    	PowerPlant plant3 = new PowerPlant();
    	plant3.setCapacity(300);
    	plant3.setMarginalCost(10);
    	plant3.persist();   	
    	
    	Set<PowerPlant> powerPlants1 = new HashSet<PowerPlant>();
    	
    	powerPlants1.add(plant1);
    	powerPlants1.add(plant2);
    	powerPlants1.add(plant3);
    	    		
    	
    	EnergyProducer producer = new EnergyProducer();
    	producer.setCash(500);
    	producer.setPrevCash(500);
    	producer.setRevenue(0);
    	producer.setPowerPlantSet(powerPlants1);
    	producer.persist();	
    	
    	
    
    	Bid bid1 = new Bid();
    	bid1.setBidder(producer);
    	bid1.setVolume(490);
    	bid1.setPrice(5);
    	bid1.setStatus(3); // accepted
    	bid1.setAcceptedVolume(490);
    	bid1.setTime(0);
    	bid1.persist();
    	
    	Bid bid2 = new Bid();
    	bid2.setBidder(producer);
    	bid2.setVolume(550);
    	bid2.setPrice(7);
    	bid2.setStatus(3); // accepted
    	bid2.setAcceptedVolume(550);
    	bid2.setTime(0);
    	bid2.persist();
       	   	
    	Bid bid3 = new Bid();
    	bid3.setBidder(producer);
    	bid3.setVolume(300);
    	bid3.setPrice(10);
    	bid3.setStatus(2); // partly accepted
    	bid3.setAcceptedVolume(100);
    	bid3.setTime(0);
    	bid3.persist();
    	       	
    	ClearingPoint clearingpoint = new ClearingPoint();
    	clearingpoint.setPrice(10);
    	clearingpoint.setTime(0);
    	clearingpoint.persist();
    	
    	dppr.act(producer);
    	
    	//logger.warn(clearingPointRepository.findTheOneClearingPriceForTime(0));
    	
    	//double price = clearingPointRepository.findClearingPointForMarketAndTime(0);
    	//logger.warn(price);
    	
		//logger.warn("Cash is " + producer.getCash());    	   
    	assertTrue("Checking producer has correct cash amount " + producer.getCash(), producer.getCash()==4300+producer.getPrevCash());
    	}
    
    @Test
    public void testDispatchPowerPlantRole2() {
	
    
    	    	
    	PowerPlant plant1 = new PowerPlant();
    	plant1.setCapacity(700);
    	plant1.setMarginalCost(9.0909);
    	plant1.persist();
    	
    	PowerPlant plant2 = new PowerPlant();
    	plant2.setCapacity(400);
    	plant2.setMarginalCost(15.3846);
    	plant2.persist();
    	
    	
    	PowerPlant plant3 = new PowerPlant();
    	plant3.setCapacity(150);
    	plant3.setMarginalCost(23.529);
    	plant3.persist();   	
    	
    	Set<PowerPlant> powerPlants1 = new HashSet<PowerPlant>();
    	
    	powerPlants1.add(plant1);
    	powerPlants1.add(plant2);
    	powerPlants1.add(plant3);
    	    		
    	
    	EnergyProducer producer = new EnergyProducer();
    	producer.setCash(0);
    	producer.setPrevCash(0);
    	producer.setRevenue(0);
    	producer.setPowerPlantSet(powerPlants1);
    	producer.persist();	
    	
    	
    
    	Bid bid1 = new Bid();
    	bid1.setBidder(producer);
    	bid1.setVolume(700);
    	bid1.setPrice(9.0909);
    	bid1.setStatus(3); // accepted
    	bid1.setAcceptedVolume(700);
    	bid1.setTime(0);
    	bid1.persist();
    	
    	Bid bid2 = new Bid();
    	bid2.setBidder(producer);
    	bid2.setVolume(400);
    	bid2.setPrice(15.38);
    	bid2.setStatus(-1); // accepted
    	bid2.setAcceptedVolume(0);
    	bid2.setTime(0);
    	bid2.persist();
       	   	
    	Bid bid3 = new Bid();
    	bid3.setBidder(producer);
    	bid3.setVolume(135);
    	bid3.setPrice(23.52);
    	bid3.setStatus(-1); // partly accepted
    	bid3.setAcceptedVolume(0);
    	bid3.setTime(0);
    	bid3.persist();
    	       	
    	ClearingPoint clearingpoint = new ClearingPoint();
    	clearingpoint.setPrice(9.756);
    	clearingpoint.setTime(0);
    	clearingpoint.persist();
    	
    	dppr.act(producer);
    	
    	//logger.warn(clearingPointRepository.findTheOneClearingPriceForTime(0));
    	
    	//double price = clearingPointRepository.findClearingPointForMarketAndTime(0);
    	//logger.warn(price);
    	
    	
    	assertEquals("Checking producer has correct cash amount " + producer.getCash(), 465.57, producer.getCash(), 0.1);
    	}
    
    }	
