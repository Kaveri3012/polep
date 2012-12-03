package polep.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import polep.domain.technology.PowerPlant;

public interface PowerPlantRepository extends GraphRepository<PowerPlant> {

}
