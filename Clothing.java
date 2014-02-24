package Assignment3;

public class Clothing extends Item 
{
	public static final double SALESTAX = 1.10;				// Adds extra 10% to price of item
	// variables, constructors as necessary
	
	Clothing(String itName, double itPrice, int itQuantity, double itWeight){
		super(itName, itPrice, itQuantity, itWeight);
	}
	
	double calculatePrice () 
	{
		double final_price = 0;
		// Insert price calculation here
		return final_price;
	}
	

}
