package Assignment3;

public class Clothing extends Item 
{
	public static final double SALESTAX = 1.10;				// Adds extra 10% to price of item
	// variables, constructors as necessary
	
	/**
	 * 
	 * @param itName
	 * @param itPrice
	 * @param itQuantity
	 * @param itWeight
	 */
	Clothing(String itName, double itPrice, int itQuantity, double itWeight){
		super(itName, itPrice, itQuantity, itWeight);
	}
	

}
