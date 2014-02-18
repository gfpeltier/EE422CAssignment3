package Assignment3;

public class Item {
	
	protected String name;
	protected double price;
	protected int quantity;
	protected int weight;			// pdf says weight is to be given in whole pounds


	Item(){				// Default constructor
		name = new String();
		price = 0.0;
		quantity = 0;
		weight = 0;
	}
	
	
	Item(String itName, double itPrice, int itQuantity, int itWeight){
		name = itName;
		price = itPrice;
		quantity = itQuantity;
		weight = itWeight;
	}
	
	double calculatePrice () 
	{
		double final_price = 0.0;
		// Insert price calculation here
		return final_price;
	}
	

	void printItemAttributes () 
	{
		//Print all applicable attributes of this class
	}

}