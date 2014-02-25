/**
 * @author Grant Peltier & John Nelson
 * EID: gfp237 & jkn387
 * EE422C Spring 2014 Assignment3
 */
package Assignment3;

import java.text.DecimalFormat;

public class Grocery extends Item {
	
	public static final double PREMIUM = 1.20;
	
	protected enum perish{P,NP};
	
	protected perish perishable;
	
	/**
	 * Constructor for grocery items
	 * @param itName
	 * @param itPrice
	 * @param itQuantity
	 * @param itWeight
	 * @param perishability
	 */
	Grocery(String itName, double itPrice, int itQuantity, double itWeight, String perishability){
		super(itName, itPrice, itQuantity, itWeight);
		if(perishability.contentEquals("P")){
			perishable = perish.P;
		}else if(perishability.contentEquals("NP")){
			perishable = perish.NP;
		}
	}
	
	/**
	 * Calculates price of item with shipping and taxes
	 */
	double calculatePrice () 
	{
		double final_price = 0.0;
		final_price = (20*weight)*quantity;
		if(perishable == perish.P){		// if grocery item is perishable, must add premium shipping upcharge
			final_price *= PREMIUM;
		}
		final_price += (quantity*price);
		return final_price;
	}
	
	/**
	 * Prints all important data for particular item
	 */
	void printItemAttributes () 
	{
		DecimalFormat df = new DecimalFormat("0.00"); 			// Decimal format to truncate all dollar amounts to 2 places after the decimal
		System.out.println("Item: " + name);
		System.out.println("Price: $" + df.format(price));
		System.out.println("Quantity: " + quantity);
		System.out.println("Weight: " + weight);
		if(perishable == perish.P){
			System.out.println("Perishability: Perishable");
		}else if(perishable == perish.NP){
			System.out.println("Perishability: Not Perishable");
		}
		System.out.println("TOTAL ITEM COST: $" + df.format(calculatePrice()) + "\n");
	}
	
}
