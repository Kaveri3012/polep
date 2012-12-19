package polep.role;

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
import polep.repository.BidRepository;
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
	
    Logger logger = Logger.getLogger(DispatchPowerPlantRoleTest.class);
	
    @Before
    @Transactional
    public void setUp() throws Exception {
    }
    
    @Test
    public void testDispatchPowerPlantRole() {
	
    
    	EnergyProducer producer = new EnergyProducer();
    	producer.persist();
    	
    	Bid bid1 = new Bid();
    	bid1.setBidder(producer);
    	bid1.setVolume(10);
    	bid1.setPrice(5);
    	bid1.setStatus(3); // accepted
    	bid1.setAcceptedVolume(10);
    	bid1.persist();
    	
    	Bid bid2 = new Bid();
    	bid2.setBidder(producer);
    	bid2.setVolume(10);
    	bid2.setPrice(7);
    	bid2.setStatus(3); // accepted
    	bid2.setAcceptedVolume(10);
    	bid2.persist();
   
    	   	
    	Bid bid3 = new Bid();
    	bid3.setBidder(producer);
    	bid3.setVolume(10);
    	bid3.setPrice(10);
    	bid3.setStatus(2); // partly accepted
    	bid3.setAcceptedVolume(5);
    	bid3.persist();
    	
    	Bid[] allPlants;
    	
    	double clearingPrice = 10;
    	double revenue;
    	double prevCash = 500;
		double cash = 500;
		    		
    	  	
    	DispatchPowerPlantRole dppr = new DispatchPowerPlantRole();
    	dppr.act(producer);
    
    	   	
    	}
    
    }	
