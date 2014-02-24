package Assignment3;

import java.text.DecimalFormat;

public class Item {
	
	public static final double SALESTAX = 1.10;	
	protected String name;
	protected double price;
	protected int quantity;
	protected double weight;			// pdf says weight is to be given in whole pounds... but double just in case for now


	/**
	 * 
	 */
	Item(){				// Default constructor
		name = new String();
		price = 0.0;
		quantity = 0;
		weight = 0.0;
	}
	
	/**
	 * 
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
	 * 
	 * @return
	 */
	double calculatePrice () 
	{
		double final_price = 0.0;
		final_price = (20*weight)*quantity;
		final_price += ((price*quantity)*SALESTAX);
		return final_price;
	}
	
	/**
	 * 
	 */
	void printItemAttributes () 
	{
		DecimalFormat df = new DecimalFormat("0.00"); 
		System.out.println("Item: " + name);
		System.out.println("Price: $" + df.format(price));
		System.out.println("Quantity: " + quantity);
		System.out.println("Weight: " + weight);
		System.out.println("TOTAL ITEM COST: $" + df.format(calculatePrice()) + "\n");
	}
	
	/**
	 * 
	 * @return
	 */
	boolean hasData(){
		if(! name.isEmpty()){
			return true;
		} else{return false;}
	}

}