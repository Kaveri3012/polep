package polep.repository;

import java.util.ArrayList;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryType;
import org.springframework.data.neo4j.repository.GraphRepository;


import polep.domain.agent.EnergyProducer;


public interface EnergyProducerRepository extends
GraphRepository<EnergyProducer> {

	/*@Query(value = "g.v(owner).in('POWERPLANT_OWNER')", type = QueryType.Gremlin)
	ArrayList<EnergyProducer> listofpowerplantowners();*/

	//@Query(value = "g.V.EnergyProducer", type = QueryType.Gremlin)
	//ArrayList<EnergyProducer> listofpowerplantowners();
}