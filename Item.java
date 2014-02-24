package Assignment3;

public class Item {
	
	protected String name;
	protected double price;
	protected int quantity;
	protected double weight;			// pdf says weight is to be given in whole pounds


	Item(){				// Default constructor
		name = new String();
		price = 0.0;
		quantity = 0;
		weight = 0.0;
	}
	
	
	Item(String itName, double itPrice, int itQuantity, double itWeight){
		name = new String(itName);
		price = itPrice;
		quantity = itQuantity;
		weight = itWeight;
	}
	
	double calculatePrice () 
	{
		double final_price = 0.0;
		final_price = (20*weight)*quantity;
		return final_price;
	}
	

	void printItemAttributes () 
	{
		System.out.println("Item: " + name);
		System.out.println("Price: " + price);
		System.out.println("Quantity: " + quantity);
		System.out.println("Weight: " + weight + "\n");
	}
	
	boolean hasData(){
		if(! name.isEmpty()){
			return true;
		} else{return false;}
	}

}