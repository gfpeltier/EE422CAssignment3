package Assignment3;

public class Grocery extends Item {
	
	protected enum perish{P,NP};
	
	protected perish perishable;
	
	Grocery(String itName, double itPrice, int itQuantity, double itWeight, String perishability){
		super(itName, itPrice, itQuantity, itWeight);
		if(perishability.contentEquals("P")){
			perishable = perish.P;
		}else if(perishability.contentEquals("NP")){
			perishable = perish.NP;
		}
	}
	
	void printItemAttributes () 
	{
		System.out.println("Item: " + name);
		System.out.println("Price: " + price);
		System.out.println("Quantity: " + quantity);
		System.out.println("Weight: " + weight);
		if(perishable == perish.P){
			System.out.println("Perishability: Perishable\n");
		}else if(perishable == perish.NP){
			System.out.println("Perishability: Not Perishable\n");
		}
	}
	
}
