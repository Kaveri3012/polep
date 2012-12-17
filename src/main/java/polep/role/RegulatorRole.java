package polep.role;

import java.lang.reflect.Array;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;



import agentspring.role.AbstractRole;
import agentspring.role.Role;
import polep.domain.agent.EnergyProducer;
import polep.domain.agent.Regulator;
import polep.domain.technology.PowerPlant;
import polep.repository.BidRepository;
import polep.repository.EnergyProducerRepository;

/*<--- RegulatorRole: Prad ----->
Regulator controls Powerplant owner:
	Throw a weighted coin (probability to control) to select owners for control (this is to simulate imperfect knowledge of the regulator)
	Compare Bid volume with installed capacity
	If Bid volume is less than installed capacity = Fine
	Fine is property of regulator
	Update Cash = Cash - Fine */


public abstract class RegulatorRole extends AbstractRole<Regulator> implements Role<Regulator> {


	@Autowired
	EnergyProducerRepository energyProducerRepository;

	//TODO Some of this shouldn't be here.
	@Autowired
	BidRepository bidRepository;
	@RelatedTo(type = "OWNS", elementClass = PowerPlant.class, direction = Direction.OUTGOING)
	private Set<PowerPlant> powerPlantSet;

	
	@Transactional
	public void act(Regulator regulator){


		int counter = 0;
		/* TODO check if this works with a JUNit test. There also some predefined
		 * methods in every repository which might be useful.
		 */
		int x = energyProducerRepository.listofpowerplantowners().size();
		int probability[] = new int[x];
		double fine = regulator.getFine();
		double Capacity;

		for (counter = 0; counter <= x; counter++){
			//TODO We need to substitute this with a real function.
			BiasedCoin coin = new BiasedCoin();
			energyProducerRepository.listofpowerplantowners().get(counter);

			coin.flip(probability[counter]);
			double result = coin.getSide();

			if (result > 0.5){

				break;
			}
			else if (result <= 0.5){
				/* TODO: In principal correct logic, but the for-loop needs to be adjusted. 
				 * If you have a List of something (powerplants, energyproducers) you can iterate through them with
				 * a for loop. An example is in BidIntoMarketRole.
				 */
				for (int i = 0; i<= energyProducerRepository.listofpowerplantowners().get(counter).getPowerPlantSet().size();i++){
					Capacity = Capacity + energyProducerRepository.listofpowerplantowners().get(counter).getPowerPlantSet().getCapacity(i);

				}

				//  if Bid Volume [counter] < Capacity

				// producer.setCash() = producer.getCash() - Fine

				//probability[counter] += 0.1


			}


		}
	}
}
