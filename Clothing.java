package Assignment3;

public class Clothing extends Item 
{
	
	/*
	 * Clothing is the most basic of the item classes. It only has the basic
	 * attributes which all items have. Additionally, it cannot be shipped
	 * using premium shipping.
	 */
	
	/**
	 * Constructor for articles of clothing.
	 * @param itName
	 * @param itPrice
	 * @param itQuantity
	 * @param itWeight
	 */
	Clothing(String itName, double itPrice, int itQuantity, double itWeight){
		super(itName, itPrice, itQuantity, itWeight);
	}
	

}
