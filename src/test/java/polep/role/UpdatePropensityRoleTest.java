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
import polep.domain.technology.Fuel;
import polep.domain.technology.PowerPlant;
import polep.repository.BidRepository;
import polep.repository.EnergyProducerRepository;
import polep.repository.PowerPlantRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/polep-test-context.xml"})
@Transactional

public class UpdatePropensityRoleTest {

	@Autowired Neo4jOperations template;
    @Autowired PowerPlantRepository powerPlantRepository;
    @Autowired EnergyProducerRepository energyProducerRepository;
    @Autowired BidRepository bidRepository; 
    
    @Autowired UpdatePropensityRole upr;
	
    Logger logger = Logger.getLogger(UpdatePropensityRoleTest.class);
	
    @Before
    @Transactional
    public void setUp() throws Exception {
    }
    
    @Test
    public void testPropensityRole() {
    	
    	

    	BiddingStrategy strategy1 = new BiddingStrategy();
    	strategy1.setPropensity(10);
    	strategy1.persist();
    	
    	BiddingStrategy strategy2 = new BiddingStrategy();
    	strategy2.setPropensity(10);
    	strategy2.persist();
    	
    	BiddingStrategy strategy3 = new BiddingStrategy();
    	strategy3.setPropensity(10);
    	strategy3.persist();
    	
    	Set<BiddingStrategy> strategySet = new HashSet<BiddingStrategy>();
    	strategySet.add(strategy1);
    	strategySet.add(strategy2);
    	strategySet.add(strategy3);
	
    	EnergyProducer producer = new EnergyProducer();
    	producer.setBiddingStrategySet(strategySet);
    	producer.setExperimentationParameter(0.1);
    	producer.setPrevCash(500);
    	producer.setCash(600);
    	producer.setChosenStrategy(strategy1);
    	producer.setRecencyParameter(0.55);
    	producer.persist();
    	
    	upr.act(producer);	
    	
    	//logger.warn(strategy1.getPropensity());
    	//logger.warn(strategy2.getPropensity());
    	//logger.warn(strategy3.getPropensity());
		
    	
    	assertTrue(strategy1.getPropensity() == 94.5);
    	assertTrue(strategy2.getPropensity() == 9.5);
    	assertTrue(strategy3.getPropensity() == 9.5);
	}

}
