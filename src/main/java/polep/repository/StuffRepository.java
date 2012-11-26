package polep.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryType;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import polep.domain.things.Stuff;
import polep.domain.agent.polepAgent;


public interface StuffRepository extends GraphRepository<Stuff> {

    @Query(value = "g.v(agent).out('OWN')", type = QueryType.Gremlin)
    public Iterable<Stuff> findMyStuff(@Param("agent") polepAgent agent);

}
