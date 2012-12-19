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
public class PickStrategyRoleTest {
    
    @Autowired Neo4jOperations template;
    @Autowired PowerPlantRepository powerPlantRepository;
    @Autowired EnergyProducerRepository energyProducerRepository;
    @Autowired BidRepository bidRepository; 
    
    
    
    
    Logger logger = Logger.getLogger(PickStrategyRoleTest.class);
	
    @Before
    @Transactional
    public void setUp() throws Exception {
    }
    
    @Test
    public void checkPickStrategyFunctionality(){
    	
    	
    	EnergyProducer producer = new EnergyProducer();
    	
    	BiddingStrategy biddingStrategy1 = new BiddingStrategy();
    	BiddingStrategy biddingStrategy2 = new BiddingStrategy();
    	BiddingStrategy biddingStrategy3 = new BiddingStrategy();
    	
    	biddingStrategy1.setPropensity(0.1);
    	biddingStrategy2.setPropensity(0.2);
    	biddingStrategy3.setPropensity(0.3);
    	
    	Set<BiddingStrategy> biddingStrategySet = new HashSet<BiddingStrategy>();
    	
    	biddingStrategySet.add(biddingStrategy1);
    	biddingStrategySet.add(biddingStrategy2);
    	biddingStrategySet.add(biddingStrategy3);
    	
    	producer.setBiddingStrategySet(biddingStrategySet); 
    	
    	PickStrategyRole pickStrategyRole = new PickStrategyRole();
    	pickStrategyRole.act(producer); 
    	
    	logger.warn("Chosen Strategy: " + producer.getChosenStrategy()); 
    	 	
    	    	
    	
    }
    

}	
