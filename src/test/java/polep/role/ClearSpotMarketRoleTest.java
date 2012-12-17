package polep.role;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.market.Bid;
import polep.domain.market.EnergyMarket;
import polep.repository.BidRepository;
import polep.repository.ClearingPointRepository;

public class ClearSpotMarketRoleTest {

	@Autowired BidRepository bidRepository;
	@Autowired ClearingPointRepository clearingPointRepository;

	Logger logger = Logger.getLogger(ClearSpotMarketRoleTest.class);

	@Before
	@Transactional
	public void setUp() throws Exception {

	}


	@Test
	public void checkClearSpotMarketRolefunctionalitiy()
	{
		EnergyMarket market = new EnergyMarket();

		
	}
}