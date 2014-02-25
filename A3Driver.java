package Assignment3;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;			// To be used to format prices properly

public class A3Driver 
	{

	public static ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
	
	/**
	 * Main function for Assignment3. Takes in input file from the command line.  
	 * @param args
	 */
	public static void main(String[] args) 
	  {
		  if (args.length != 1) 
			{
				System.err.println ("Error: Incorrect number of command line arguments");
				System.exit(-1);
			}
		
		//Open file; file name specified in args (command line)
		  File file = new File(args[0]);
		  try{
			  FileReader fileread = new FileReader(file);
			  BufferedReader reader = new BufferedReader(fileread);
			  
			  for (String s = reader.readLine(); s != null; s = reader.readLine()){
					String process = A3Driver.processInput(s);
					System.out.println(process);
				}
		  }catch (FileNotFoundException e) {
	            // File not found
	            e.printStackTrace();

	      }catch (IOException e) {
	            // Not able to read line
	            e.printStackTrace();
	      }
		
	  }

	  /**
	   * This method parses the input taken in by the file reader and parses it into
	   * its seperate parts. Then passes those parts to completeAction()
	   * @param input
	   * @return Output string to main
	   */
	  public static String processInput(String input){
		  int off = 0;
		  String operation = new String(getWord(input, off));
		  off += operation.length() + 1;
		  String category = new String(getWord(input, off));
		  off += category.length() + 1;
		  String name = new String(getWord(input, off));
		  off += name.length() + 1;
		  String price = new String(getWord(input, off));
		  off += price.length() + 1;
		  String quantity = new String(getWord(input, off));
		  off += quantity.length() + 1;
		  String weight = new String(getWord(input, off));
		  off += weight.length() + 1;
		  String op1 = new String();
		  String op2 = new String();
		  if(off < input.length()){
			  while(off < input.length() && input.charAt(off) != ' '){
				  op1 += input.charAt(off);
				  off++;
			  }
			  off++;
		  } 
		  if(off < input.length()){
			  while(off < input.length()){
				  op2 += input.charAt(off);
				  off++;
			  }
		  }
		  String output = new String(completeAction(operation, category, name, price, quantity, weight, op1, op2));
		  if(output.contentEquals("categoryInsertError")){
			  return "Invalid Item Category for Insert Function.";
		  }
		  if(output.contentEquals("invalidAmount")){
			  return "Quantities for insert and update cannot be less than 0.";
		  }
		  return output;
	  }
	  
	  /**
	   * This method is called by processInput(). Depending on the operation part of the input, it 
	   * passes along the necessary parameters to the different operation methods.
	   * @param operate
	   * @param category
	   * @param itemName
	   * @param itemPrice
	   * @param itemQuantity
	   * @param itemWeight
	   * @param op1
	   * @param op2
	   * @return Output string to pass up the line as output. Or error string to be dealt with in processInput()
	   */
	  public static String completeAction(String operate, String category, String itemName, String itemPrice, String itemQuantity, String itemWeight, String op1, String op2){
		  if(operate.contentEquals("insert")){
			  Item newItem = new Item();
			  double itPrice = Double.parseDouble(itemPrice);
			  double itWeight = Double.parseDouble(itemWeight);
			  int itAmount = Integer.parseInt(itemQuantity);
			  if(itAmount < 0){return "invalidAmount";}
			  if(category.contentEquals("electronics")){
				  newItem = new Electronics(itemName, itPrice, itAmount, itWeight, op1, op2);
			  }else if(category.contentEquals("clothing")){
				  newItem = new Clothing(itemName, itPrice, itAmount, itWeight);
			  }else if(category.contentEquals("groceries")){
				  newItem = new Grocery(itemName, itPrice, itAmount, itWeight, op1);
			  }else {return "categoryInsertError";}
			  String output = new String(operationInsert(newItem));  
			  return output;
		  }else if(operate.contentEquals("search")){
			  itemName = category;
			  String output = new String(operationSearch(itemName));  
			  return output;
		  }else if(operate.contentEquals("delete")){
			  itemName = category;
			  String output = new String(operationDelete(itemName));  
			  return output;
		  }else if(operate.contentEquals("update")){
			  itemQuantity = itemName;
			  itemName = category;
			  int itAmount = Integer.parseInt(itemQuantity);
			  if(itAmount < 0){return "invalidAmount";}
			  String output = new String(operationUpdate(itemName, itemQuantity));
			  return output;
		  }else if(operate.contentEquals("print")){
			  String output = new String(operationPrint());  
			  return output;
		  }else{return "\"" + operate + "\"" + " is not a valid operation.";}
	  }
	  
	  /**
	   * This method inserts items into the shoppingCart ArrayList. Does not insert alphabetically. 
	   * Use the iterator to do that separately when necessary.
	   * @param newItem
	   * @return Output string for either a successful or error add.
	   */
	  public static String operationInsert(Item newItem){
		  if(newItem.quantity == 0){return "Cannot add a quantity of 0 " + newItem.name + " to your cart.";}
		  shoppingCart.add(newItem);
		  if(newItem.quantity != 1){
		  return newItem.quantity + " " + newItem.name + "s have been added to your cart.";
		  }else{return newItem.quantity + " " + newItem.name + " has been added to your cart.";}
	  }
	  
	  
	  /**
	   * Iterates through the ArrayList to find Items matching the itemName.
	   * @param itemName
	   * @return Output string for either a successfully found Item, or not found one. Include number found if successful.
	   */
	  public static String operationSearch(String itemName){
		  Iterator<Item> i = shoppingCart.iterator();
		  Item found = new Item();
		  int numFound = 0;
		  while(i.hasNext()){
			  Item temp = i.next();
			  if(temp.name.equalsIgnoreCase(itemName)){
				  found = temp;
				  numFound += found.quantity;
			  }
		  }if(found.hasData()){
			  if(numFound != 1){
			  return "There are currently " + numFound + " items matching the name " +"\"" +  itemName + "\"" + " in your cart.";
			  } else{return "There is currently 1 item matching the name " + "\"" + itemName + "\"" + " in your cart.";}
			  }else{return "Item" + " \""+ itemName + "\" " + "not found in cart.";}
	  }

	  /**
	   * Iterates through shoppingCart ArrayList and removes any items matching the input string
	   * @param itemName
	   * @return Output string. If matching item found, tells how many were removed.
	   */
	  public static String operationDelete(String itemName){
		  Iterator<Item> i = shoppingCart.iterator();
		  Item found = new Item();
		  int quant = 0;
		  while(i.hasNext()){
			  Item temp = i.next();
			  if(temp.name.equalsIgnoreCase(itemName)){
				  found = temp;
				  quant += found.quantity;
				  i.remove();
			  }
		  }if(found.hasData()){
			  if(quant != 1){
			  return quant + " " + found.name + "s have been removed from your cart.";
			  }else{return quant + " " + found.name + " has been removed from your cart.";}
		  }else{return "There are no items matching " + "\"" +itemName + "\"" + " in your cart.";}
	  }

	  /**
	   * Iterates through shoppingCart ArrayList and updates any Items matching the item name to the new quantity.
	   * @param itemName
	   * @param amount
	   * @return Output string. If Item is found and updated. Tells the new quantity of the item.
	   */
	  public static String operationUpdate(String itemName, String amount){
		  Iterator<Item> i = shoppingCart.iterator();
		  int amt = Integer.parseInt(amount);
		  Item found = new Item();
		  if(amt > 0){
			  while(i.hasNext()){
			  	Item temp = i.next();
			  	if(temp.name.equalsIgnoreCase(itemName)){
				  temp.quantity = amt;
				  found = temp;
				  if(found.quantity != 1){
					  return "There are now " + found.quantity + " " + found.name + "s in your cart.";
				  	} else{return "There is now 1 " + found.name + " in your cart.";}
			  	}
		  	}
			  return "Item" + " \"" +  itemName + "\" " + "not found in cart";
		  }else{
			  while(i.hasNext()){
				  	Item temp = i.next();
				  	if(temp.name.equalsIgnoreCase(itemName)){
					  found = temp;
					  i.remove();
					  return found.name + " has been removed from your cart since it was updated to a quantity of 0.";
				  	}
			  }
			return "Item" + " \"" +  itemName + "\" " + "not found in cart";  
		  }
	  }

	  /**
	   * Sorts the shoppingCart ArrayList and then iterates through to print all important data. 
	   * Also determines and prints final prices for each individual item as well as price for entire
	   * cart.
	   * @return null string
	   */
	  public static String operationPrint(){
		  DecimalFormat df = new DecimalFormat("0.00"); 
		  Iterator<Item> i = shoppingCart.iterator();
		  double cartPrice = 0.0;
		  Collections.sort(shoppingCart, new Comparator<Item>(){
				  public int compare(Item item1, Item item2){
			  return item1.name.compareToIgnoreCase(item2.name);
		  }
				  });
		  System.out.println("\nPrinting cart contents:\n");
		  if(shoppingCart.isEmpty()){return "Your shopping cart is empty.\n\n";}
		  while(i.hasNext()){
			  Item temp = i.next();
			  temp.printItemAttributes();
			  cartPrice += temp.calculatePrice();
		  }
		  System.out.println("\nTOTAL CART PRICE: $" + df.format(cartPrice));
		  return "";
	  }
	  
	  /**
	   * Parses single parts from input string based on current index within the string.
	   * @param input
	   * @param offset
	   * @return String parsed from input line. Comprised of all characters between spaces.
	   */
	  public static String getWord(String input, int offset){
		  String word = new String();
		  if(offset >= input.length()){return word;}
		  while(offset < input.length() && input.charAt(offset) != ' '){
			  word += input.charAt(offset);
			  offset += 1;
		  }
		  return word;
	  }
}