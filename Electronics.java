package Assignment3;

public class Electronics extends Item {
	
	public static final double SALESTAX = 1.10;				// Adds extra 10% to price of item
	
	protected enum fragile {F,NF};
	
	Electronics(String itName, double itPrice, int itQuantity, int itWeight, String isFragile){
		super(itName, itPrice, itQuantity, itWeight);
	}
	
	//Implement calculate price/print methods as necessary

}
