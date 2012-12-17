/**
 * 
 */
package polep.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.EnergyProducer;

/**
 * @author JCRichstein
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/polep-test-context.xml"})
@Transactional
public class EnergyProducerRepositoryTest {
	
    @Autowired EnergyProducerRepository energyProducerRepository;

	@Test
	public void testRepositoryStandardFunctions() {
		EnergyProducer energyProducer1 = new EnergyProducer();
		energyProducer1.setName("Producer1");
		energyProducer1.persist();
		
		EnergyProducer energyProducerFromRepository = energyProducerRepository.findAll().iterator().next();
		assertTrue("Check if producer name is the same", energyProducerFromRepository.getName().equals(energyProducer1.getName()));
		
		for(int i = 2; i<=10; i++){
			EnergyProducer energyProducer = new EnergyProducer();
			energyProducer.setName("Producer"+(new Integer(i).toString()));
			energyProducer.persist();
		}
		
		assertTrue("Check if no of producers is correct", energyProducerRepository.count()==10);
	
		
	}

}
