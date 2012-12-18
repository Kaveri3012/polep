package polep.role;

import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.market.Bid;
import polep.domain.market.ClearingPoint;
import polep.domain.market.EnergyMarket;
import polep.repository.BidRepository;
import polep.repository.ClearingPointRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/polep-test-context.xml"})
@Transactional
public class ClearSpotMarketRoleTest {

	@Autowired BidRepository bidRepository;
	@Autowired ClearingPointRepository clearingPointRepository;

	Logger logger = Logger.getLogger(ClearSpotMarketRoleTest.class);
	
	@Autowired ClearSpotMarketRole cspmrClearSpotMarketRole;

	@Before
	@Transactional
	public void setUp() throws Exception {

		//Create stuff that is necessary
		/*EnergyMarket market = new EnergyMarket();
		market.persist();
		market.setDemand(600);*/

		//create number of bids and persist
		Bid bid1 = new Bid();
		bid1.setVolume(200);
		bid1.setPrice(10);
		bid1.setTime(0);
		bid1.persist();

		Bid bid2 = new Bid();
		bid2.setVolume(600);
		bid2.setPrice(20);
		bid2.setTime(0);
		bid2.persist();

		Bid bid3 = new Bid();
		bid3.setVolume(500);
		bid3.setPrice(5);
		bid3.setTime(0);
		bid3.persist();

		Bid bid4 = new Bid();
		bid4.setVolume(300);
		bid4.setPrice(15);
		bid4.setTime(0);
		bid4.persist();
		


	}


	@Test
	public void checkClearSpotMarketRolefunctionalitiyCase1()
	{
		

		// Case 1: Check bids
		EnergyMarket market = new EnergyMarket();
		market.setDemand(600);
		market.setValueOfLostLoad(5000);
		market.persist();
		cspmrClearSpotMarketRole.act(market);
		
		Iterable<Bid> allBids = bidRepository.findAllSortedBidsByPrice(0);
		int[] expectedStatuses = new int[4];
		expectedStatuses[0] = 3;
		expectedStatuses[1] = 2;
		expectedStatuses[2] = -1;
		expectedStatuses[3] = -1;
		
		for (int i = 0; i<4 ; i++){
			Bid currentBid = allBids.iterator().next();
			assertTrue("Checking for bid statuses of Bid " + i, currentBid.getStatus()== expectedStatuses[i]);
		}
		
		assertTrue("Check clearing price", clearingPointRepository.findAll().iterator().next().getPrice() == 10);
		assertTrue("Check clearing volume", clearingPointRepository.findAll().iterator().next().getVolume() == 600);

	}
	
	@Test
	public void checkClearSpotMarketRolefunctionalitiyCase2(){
		// Case 2: Check bids
		
		EnergyMarket market = new EnergyMarket();
		market.persist();
		market.setDemand(1600);
		market.setValueOfLostLoad(5000);
		cspmrClearSpotMarketRole.act(market);
		
		Iterable<Bid> allBids = bidRepository.findAllSortedBidsByPrice(0);
		int[] expectedStatuses = new int[4];
		allBids = bidRepository.findAllSortedBidsByPrice(0);
		expectedStatuses[0] = 3;
		expectedStatuses[1] = 3;
		expectedStatuses[2] = 3;
		expectedStatuses[3] = 3;
		
		for (int i = 0; i<4 ; i++){
			Bid currentBid = allBids.iterator().next();
			assertTrue("Checking for bid statuses of Bid " + i, currentBid.getStatus()== expectedStatuses[i]);
		}
		
		assertTrue("Check clearing price", clearingPointRepository.findAll().iterator().next().getPrice() == 20);
		assertTrue("Check clearing price", clearingPointRepository.findAll().iterator().next().getVolume() == 1600);
		
	}
	
	@Test
	public void checkClearSpotMarketRolefunctionalitiyCase3(){
		// Case 2: Check bids
		
		EnergyMarket market = new EnergyMarket();
		market.persist();
		market.setDemand(2000);
		market.setValueOfLostLoad(5000);
		cspmrClearSpotMarketRole.act(market);
		
		Iterable<Bid> allBids = bidRepository.findAllSortedBidsByPrice(0);
		int[] expectedStatuses = new int[4];
		allBids = bidRepository.findAllSortedBidsByPrice(0);
		expectedStatuses[0] = 3;
		expectedStatuses[1] = 3;
		expectedStatuses[2] = 3;
		expectedStatuses[3] = 3;
		
		for (int i = 0; i<4 ; i++){
			Bid currentBid = allBids.iterator().next();
			assertTrue("Checking for bid statuses of Bid " + i, currentBid.getStatus()== expectedStatuses[i]);
		}
		
		assertTrue("Check clearing price", clearingPointRepository.findAll().iterator().next().getPrice() == 5000);
		assertTrue("Check clearing price", clearingPointRepository.findAll().iterator().next().getVolume() == 1600);
		
	}
	
	@Test
	public void checkClearSpotMarketRolefunctionalitiyCase4(){
		// Case 2: Check bids
		
		EnergyMarket market = new EnergyMarket();
		market.persist();
		market.setDemand(500);
		market.setValueOfLostLoad(5000);
		cspmrClearSpotMarketRole.act(market);
		
		Iterable<Bid> allBids = bidRepository.findAllSortedBidsByPrice(0);
		int[] expectedStatuses = new int[4];
		allBids = bidRepository.findAllSortedBidsByPrice(0);
		expectedStatuses[0] = 3;
		expectedStatuses[1] = -1;
		expectedStatuses[2] = -1;
		expectedStatuses[3] = -1;
		
		for (int i = 0; i<4 ; i++){
			Bid currentBid = allBids.iterator().next();
			assertTrue("Checking for bid statuses of Bid " + i, currentBid.getStatus()== expectedStatuses[i]);
		}
		
		assertTrue("Check clearing price", clearingPointRepository.findAll().iterator().next().getPrice() == 5);
		assertTrue("Check clearing price", clearingPointRepository.findAll().iterator().next().getVolume() == 500);
		
	}

	

	@Test
	public void checkClearSpotMarketRolefunctionalitiyCase5(){
		// Case 2: Check bids
		
		EnergyMarket market = new EnergyMarket();
		market.persist();
		market.setDemand(0);
		market.setValueOfLostLoad(5000);
		cspmrClearSpotMarketRole.act(market);
		
		Iterable<Bid> allBids = bidRepository.findAllSortedBidsByPrice(0);
		int[] expectedStatuses = new int[4];
		allBids = bidRepository.findAllSortedBidsByPrice(0);
		expectedStatuses[0] = -1;
		expectedStatuses[1] = -1;
		expectedStatuses[2] = -1;
		expectedStatuses[3] = -1;
		
		for (int i = 0; i<4 ; i++){
			Bid currentBid = allBids.iterator().next();
			assertTrue("Checking for bid statuses of Bid " + i, currentBid.getStatus()== expectedStatuses[i]);
		}
		
		assertTrue("Check clearing price", clearingPointRepository.findAll().iterator().next().getPrice() == 0);
		assertTrue("Check clearing price", clearingPointRepository.findAll().iterator().next().getVolume() == 0);
		
	}

}