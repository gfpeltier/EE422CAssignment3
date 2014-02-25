package Assignment3;

import java.text.DecimalFormat;

public class Electronics extends Item {
	
	public static final double SALESTAX = 1.10;				// Adds extra 10% to price of item
	public static final double PREMIUM = 1.20;
	
	protected enum frag {F,NF};
	
	protected frag fragility;
	
	protected String shipState;
	
	
	/**
	 * Constructor for electronics item
	 * @param itName
	 * @param itPrice
	 * @param itQuantity
	 * @param itWeight
	 * @param isFragile
	 * @param state
	 */
	Electronics(String itName, double itPrice, int itQuantity, double itWeight, String isFragile, String state){
		super(itName, itPrice, itQuantity, itWeight);
		if(isFragile.indexOf("F") != -1){
			fragility = frag.F;
		}else {
			fragility = frag.NF;
		}
		shipState = new String(state);
	}
	
	/**
	 * Calculates price with shipping and taxes
	 */
	double calculatePrice () 
	{
		double final_price = 0.0;
		final_price = (20*weight)*quantity;
		if(fragility == frag.F){
			final_price *= PREMIUM;			// 1.2 scalar multiplier for premium shipping
		}
		if(!(shipState.contentEquals("TX") || shipState.contentEquals("NM") || shipState.contentEquals("VA") || shipState.contentEquals("AZ") || shipState.contentEquals("AK"))){
			final_price += ((price*quantity)*SALESTAX);			// If shipping to someplace other than TX, NM, VA, AZ, or AK then must add taxes
		} else{
			final_price += price*quantity;
		}
		return final_price;
	}
	
	/**
	 * Prints all important data for the particular item.
	 */
	void printItemAttributes () 
	{
		DecimalFormat df = new DecimalFormat("0.00"); 		// Decimal format to truncate all dollar amounts to 2 places after the decimal
		System.out.println("Item: " + name);
		System.out.println("Price: $" + df.format(price));
		System.out.println("Quantity: " + quantity);
		System.out.println("Weight: " + weight);
		if(fragility == frag.F){
			System.out.println("Fragility: Fragile");
		}else if(fragility == frag.NF){
			System.out.println("Fragility: Not Fragile");
		}
		System.out.println("Ship to: " + shipState);
		System.out.println("TOTAL ITEM COST: $" + df.format(calculatePrice()) + "\n");
	}

}
