package polep.util;

import cern.jet.random.*;

/* This class creates a Weighed coin. The BiasedCoin contains a method flip within which the bias parameter 
 * is set.
 */

public class BiasedCoin {
	
	
	Uniform uni = new Uniform(0, 1, Uniform.makeDefaultGenerator());
	
/*	public BiasedCoin()
	{
		
		uni
	}*/
	double side;	
	public void flip(double biasParameter)
	{
		
		
		side = ((uni.nextDouble()- biasParameter));
	}

	public double getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}
	
	
//	Only for testing the Coin
	/*public static void main (String [] args)	{

		BiasedCoin coin = new BiasedCoin ();
		int counter = 0;
		int flips = 5;
		double bias=0;
		while (counter<flips)
		{
		coin.flip(bias);
		double result = coin.getSide();
		
		if ( result <= 0.5){
			System.out.println ("Control = " + result);
		}
		else 
			
		System.out.println ("NoControl = " + result);
		counter++;
		}
	}*/
}