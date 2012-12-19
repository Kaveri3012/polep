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
import scala.Double;

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
    @Autowired PickStrategyRole pickStrategyRole;
    
    
    
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
    	
    	biddingStrategy1.persist();
    	biddingStrategy2.persist();
    	biddingStrategy3.persist();
    	
    	biddingStrategy1.setPropensity(0.1);
    	biddingStrategy2.setPropensity(0.3);
    	biddingStrategy3.setPropensity(0.2);
    	//expected probability of strategy 1 = 1/6
    	//expected probability of strategy 2 = 0.5
    	//expected probability of strategy 3 = 0.33
    	
   
    	Set<BiddingStrategy> biddingStrategySet = new HashSet<BiddingStrategy>();
    	
    	biddingStrategySet.add(biddingStrategy1);
    	biddingStrategySet.add(biddingStrategy2);
    	biddingStrategySet.add(biddingStrategy3);
    	
    	producer.setBiddingStrategySet(biddingStrategySet); 
    	producer.persist();
    	
    	int countStrategy1 = 0;
    	int countStrategy2 = 0;
    	int countStrategy3 = 0;
    	
    	int i = 0;
    	for (i=0; i<=2000; i++){
    		pickStrategyRole.act(producer);
    		if (producer.getChosenStrategy().getPropensity() == biddingStrategy1.getPropensity()){ countStrategy1++; }
    		if (producer.getChosenStrategy().getPropensity() == biddingStrategy2.getPropensity()){ countStrategy2++; }
    		if (producer.getChosenStrategy().getPropensity() == biddingStrategy3.getPropensity()){ countStrategy3++; }
    		
    	}
    	
    	double  probabilityStrategy1 = countStrategy1/2000.0;
    	double  probabilityStrategy2 = countStrategy2/2000.0;
    	double  probabilityStrategy3 = countStrategy3/2000.0;
    	
    	//logger.warn("Chosen Strategy: " + producer.getChosenStrategy()); 
    	
    	//logger.warn("Probability of Strategy 1 being chosen: " + probabilityStrategy1);
    	//logger.warn("Probability of Strategy 2 being chosen: " + probabilityStrategy2);
    	//logger.warn("Probability of Strategy 3 being chosen: " + probabilityStrategy3);
    	
    	assertTrue("Check probability of strategy 1", probabilityStrategy1<=0.25 && probabilityStrategy1>=0.13);
    	assertTrue("Check probability of strategy 2", probabilityStrategy2<=0.60 && probabilityStrategy2>=0.40);
    	assertTrue("Check probability of strategy 3", probabilityStrategy3>=0.28 && probabilityStrategy3<=0.40);
    	    	
    	
    }
    

}	
