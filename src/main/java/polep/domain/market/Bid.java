package polep.domain.market;

import org.springframework.data.neo4j.annotation.NodeEntity;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.transaction.annotation.Transactional;

import polep.domain.agent.PolepAgent;


/*
 * Establishes a volume-price pair for every power plant owned by an energy producer.
 * The volume price pair is established based on the bidding strategy returned.  
 */

@NodeEntity
public class Bid {

	public static int FAILED = -1;
	public static int NOT_SUBMITTED = 0;
	public static int SUBMITTED = 1;
	public static int PARTLY_ACCEPTED = 2;
	public static int ACCEPTED = 3;

	@RelatedTo(type = "BIDDER", elementClass = PolepAgent.class, direction = Direction.INCOMING)
	private PolepAgent bidder;

	@RelatedTo(type = "BIDDINGMARKET", elementClass = EnergyMarket.class, direction = Direction.OUTGOING)
	private EnergyMarket biddingMarket;

	private double volume;
	private double acceptedVolume;
	private double price;
	@Indexed(indexName = "bidTime")
	private long time;
	private int status;
	private boolean supplyBid;
	private double percentageWithholdment;

	public PolepAgent getBidder() {
		return bidder;
	}

	public void setBidder(PolepAgent agent) {
		this.bidder = agent;
	}

	public EnergyMarket getBiddingMarket() {
		return biddingMarket;
	}

	public void setBiddingMarket(EnergyMarket market) {
		this.biddingMarket = market;
	}

	/**
	 * IMPORTANT this returns the capacity that was bid into the spot market,
	 * so for the depending class PPDP this means without the capacity reserved
	 * for long-term markets.
	 * 
	 * @return
	 */
	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getAcceptedVolume() {
		return acceptedVolume;
	}

	public void setAcceptedVolume(double acceptedVolume) {
		this.acceptedVolume = acceptedVolume;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public boolean isSupplyBid() {
		return supplyBid;
	}

	public void setSupplyBid(boolean supplyBid) {
		this.supplyBid = supplyBid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Changes the amount of a bid
	 * 
	 * @param bid
	 *            the bid to change
	 * @param amount
	 *            the new amount
	 */
	 @Transactional
	 public void updateVolume(double volume) {
		 setVolume(volume);
	 }

	 /**
	  * Changes the status of a bid
	  * 
	  * @param bid
	  *            the bid to change
	  * @param status
	  *            the new status
	  */
	 @Transactional
	 public void updateStatus(int status) {
		 setStatus(status);
	 }

	 @Override
	 public String toString() {
		 return "for " + getBiddingMarket() + " price: " + getPrice() + " amount: " + getVolume() + " isSupply: "
				 + isSupplyBid();
	 }
}






//call a method, 'computeChoice of strategy'?




