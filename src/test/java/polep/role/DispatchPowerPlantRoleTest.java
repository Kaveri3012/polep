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
    
    @Test
    public void testDispatchPowerPlantRole() {
	
    
    	    	
    	PowerPlant plant1 = new PowerPlant();
    	plant1.setCapacity(10);
    	plant1.setMarginalCost(5);
    	plant1.persist();
    	
    	PowerPlant plant2 = new PowerPlant();
    	plant2.setCapacity(10);
    	plant2.setMarginalCost(7);
    	plant2.persist();
    	
    	
    	PowerPlant plant3 = new PowerPlant();
    	plant3.setCapacity(10);
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
    	bid1.setVolume(10);
    	bid1.setPrice(5);
    	bid1.setStatus(3); // accepted
    	bid1.setAcceptedVolume(10);
    	bid1.setTime(0);
    	bid1.persist();
    	
    	Bid bid2 = new Bid();
    	bid2.setBidder(producer);
    	bid2.setVolume(10);
    	bid2.setPrice(7);
    	bid2.setStatus(3); // accepted
    	bid2.setAcceptedVolume(10);
    	bid2.setTime(0);
    	bid2.persist();
       	   	
    	Bid bid3 = new Bid();
    	bid3.setBidder(producer);
    	bid3.setVolume(10);
    	bid3.setPrice(10);
    	bid3.setStatus(2); // partly accepted
    	bid3.setAcceptedVolume(5);
    	bid3.setTime(0);
    	bid3.persist();
    	       	
    	ClearingPoint clearingpoint = new ClearingPoint();
    	clearingpoint.setPrice(10);
    	clearingpoint.setTime(0);
    	clearingpoint.persist();
    	
    	logger.warn(clearingPointRepository.findTheOneClearingPriceForTime(0));
    	
    	//double price = clearingPointRepository.findClearingPointForMarketAndTime(0);
    	//logger.warn(price);
    	
		    		
    	dppr.act(producer);
    	
    	logger.warn(producer.getCash());
    
    	assertTrue("Checking producer has correct cash amount", producer.getCash()==630);
    	}
    
    }	
