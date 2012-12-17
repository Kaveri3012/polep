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
	public void testFindAll() {
		EnergyProducer energyProducer1 = new EnergyProducer();
		energyProducer1.setName("Producer1");
		energyProducer1.persist();
		
		EnergyProducer energyProducerFromRepository = energyProducerRepository.findAll().iterator().next();
		assertTrue("Check if producer name is the same", energyProducerFromRepository.getName().equals(energyProducer1.getName()));
		
	}

}
