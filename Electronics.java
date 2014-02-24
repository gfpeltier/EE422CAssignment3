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
		System.out.println("Item: " + name);
		System.out.println("Price: " + price);
		System.out.println("Quantity: " + quantity);
		System.out.println("Weight: " + weight);
		if(fragility == frag.F){
			System.out.println("Fragility: Fragile");
		}else if(fragility == frag.NF){
			System.out.println("Fragility: Not Fragile");
		}
		System.out.println("Ship to: " + shipState + "\n");
	}

}
