package polep.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import polep.domain.agent.EnergyProducer;

public interface EnergyProducerRepository extends
		GraphRepository<EnergyProducer> {

}
