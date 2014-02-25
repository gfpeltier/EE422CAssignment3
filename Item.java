package Assignment3;

import java.text.DecimalFormat;

public class Item {
	
	public static final double SALESTAX = 1.10;	
	protected String name;
	protected double price;
	protected int quantity;
	protected double weight;			// pdf says weight is to be given in whole pounds... but double just in case for now


	/**
	 * Default Item constructor
	 */
	Item(){				// Default constructor
		name = new String();
		price = 0.0;
		quantity = 0;
		weight = 0.0;
	}
	
	/**
	 * Item constructor with all parameters
	 * @param itName
	 * @param itPrice
	 * @param itQuantity
	 * @param itWeight
	 */
	Item(String itName, double itPrice, int itQuantity, double itWeight){
		name = new String(itName);
		price = itPrice;
		quantity = itQuantity;
		weight = itWeight;
	}
	
	/**
	 * Determine price for the item including shipping and taxes.
	 * @return Complete price for item as a double precision floating point
	 */
	double calculatePrice () 
	{
		double final_price = 0.0;
		final_price = (20*weight)*quantity;
		final_price += ((price*quantity)*SALESTAX);
		return final_price;
	}
	
	/**
	 * Print all important data for the item.
	 */
	void printItemAttributes () 
	{
		DecimalFormat df = new DecimalFormat("0.00"); 		// Decimal format to truncate all dollar amounts to 2 places after the decimal
		System.out.println("Item: " + name);
		System.out.println("Price: $" + df.format(price));
		System.out.println("Quantity: " + quantity);
		System.out.println("Weight: " + weight);
		System.out.println("TOTAL ITEM COST: $" + df.format(calculatePrice()) + "\n");
	}
	
	/**
	 * Determine whether or not item is empty
	 * @return boolean. True if not empty. False if it is empty.
	 */
	boolean hasData(){
		if(! name.isEmpty()){
			return true;
		} else{return false;}
	}

}