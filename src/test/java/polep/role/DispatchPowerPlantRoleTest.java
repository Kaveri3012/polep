package polep.role;

import static org.junit.Assert.assertTrue;

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
import polep.domain.technology.Fuel;
import polep.domain.technology.PowerPlant;
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
    	bid1.setVolume(20);
    	bid1.setPrice(price)
    	bid1.persist();
    	Bid bid2 = new Bid();
    	bid2.persist();
    	bid2.setBidder(producer);
    	Bid bid3 = new Bid();
    	bid3.persist();
    	bid3.setBidder(producer);
    	    	    	
    	double clearingPrice = 10.0;
    		
    	DispatchPowerPlantRole dppr = new DispatchPowerPlantRole();
    	dppr.act(producer);
    }	

}
