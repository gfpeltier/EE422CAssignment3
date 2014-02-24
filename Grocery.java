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
		//Print all applicable attributes of this sub-class
	}
	
}
