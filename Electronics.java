package Assignment3;

public class Electronics extends Item {
	
	public static final double SALESTAX = 1.10;				// Adds extra 10% to price of item
	
	protected enum frag {F,NF};
	
	protected frag fragility;
	
	protected String shipState;
	
	
	
	Electronics(String itName, double itPrice, int itQuantity, double itWeight, String isFragile, String state){
		super(itName, itPrice, itQuantity, itWeight);
		if(isFragile.indexOf("F") != -1){
			fragility = frag.F;
		}else {
			fragility = frag.NF;
		}
		shipState = new String(state);
	}
	
	double calculatePrice () 
	{
		double final_price = 0;
		// Insert price calculation here
		return final_price;
	}
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this sub-class
	}

}
