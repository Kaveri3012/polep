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
	
    Logger logger = Logger.getLogger(UpdatePropensityRoleTest.class);
	
    @Before
    @Transactional
    public void setUp() throws Exception {
    }
    
    @Test
    public void testPropensityRole() {

	
    	EnergyProducer producer = new EnergyProducer();
    	producer.persist();
    	
    	double experimentationParameter = 0.01;
    	double recencyParameter = 0.95;	
    	double cash = 550;
    	double experiencefunction = 0;
    	double prevCash = 500;
    	producer.persist();
    	
    	
    	
    	Set<BiddingStrategy> strategySet;
    	BiddingStrategy chosenstrategy;
		    	
    	  	
    	UpdatePropensityRole upr = new UpdatePropensityRole();
    	upr.act(producer);	
		
	}

}
