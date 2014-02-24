package Assignment3;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;			// To be used to format prices properly

public class A3Driver 
	{

	public static ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
	
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
		
		
		// General code example for how to iterate an array list. You will have to modify this heavily, to suit your needs.
		
		//while (i.hasNext()) 
		//{
			//Item temp = i.next();
			//temp.calculatePrice(); 
			//temp.printItemAttributes();
			//This (above) works because of polymorphism: a determination is made at runtime, 
			//based on the inherited class type, as to which method is to be invoked. Eg: If it is an instance
			// of Grocery, it will invoke the calculatePrice () method defined in Grocery.
		//}		
	  }

	  /**
	   * 
	   *
	   * @param input
	   * @return
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
		  return output;
	  }
	  
	  /**
	   * 
	   * @param operate
	   * @param category
	   * @param itemName
	   * @param itemPrice
	   * @param itemQuantity
	   * @param itemWeight
	   * @param op1
	   * @param op2
	   * @return
	   */
	  public static String completeAction(String operate, String category, String itemName, String itemPrice, String itemQuantity, String itemWeight, String op1, String op2){
		  if(operate.contentEquals("insert")){
			  Item newItem = new Item();
			  double itPrice = Double.parseDouble(itemPrice);
			  int itAmount = Integer.parseInt(itemQuantity);
			  double itWeight = Double.parseDouble(itemWeight);
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
			  String output = new String(operationUpdate(itemName, itemQuantity));  
			  return output;
		  }else if(operate.contentEquals("print")){
			  String output = new String(operationPrint());  
			  return output;
		  }else{return "operationError";}
	  }
	  /**
	   * Does not insert alphabetically. Use the iterator to do that separately when necessary.
	   * @param newItem
	   * @return
	   */
	  public static String operationInsert(Item newItem){
		  shoppingCart.add(newItem);
		  return newItem.quantity + " " + newItem.name + "(s) has been added to your cart.";
	  }
	  
	  
	  //TODO: there is currently an issue with the way the iterator is implemented in the search operation
	  public static String operationSearch(String itemName){
		  Iterator<Item> i = shoppingCart.iterator();
		  Item found = new Item();
		  while(i.hasNext()){
			  Item temp = i.next();
			  if(temp.name.contentEquals(itemName)){
				  found = temp;
			  }
		  }if(found.hasData()){
			  return "There are currently " + found.quantity + " items matching the name " + itemName + " in your cart.";
			  }else{return "Item" + " \""+ itemName + "\" " + "not found in cart.";}
	  }

	  public static String operationDelete(String itemName){
		  Iterator<Item> i = shoppingCart.iterator();
		  Item found = new Item();
		  while(i.hasNext()){
			  Item temp = i.next();
			  if(temp.name.contentEquals(itemName)){
				  found = temp;
				  i.remove();
			  }
		  }if(found.hasData()){
			  return found.quantity + " " + found.name + "(s) have been removed from your cart.";
		  }else{return "There are no items matching " + itemName + " in your cart.";}
	  }

	  public static String operationUpdate(String itemName, String amount){
		  Iterator<Item> i = shoppingCart.iterator();
		  int amt = Integer.parseInt(amount);
		  Item found = new Item();
		  while(i.hasNext()){
			  Item temp = i.next();
			  if(temp.name.contentEquals(itemName)){
				  temp.quantity = amt;
				  found = temp;
			  }
		  }if(found.hasData()){
			  return "There are now " + found.quantity + " " + found.name + "(s) in your cart.";
		  }else{return "Item" + " \"" +  itemName + "\" " + "not found in cart";}
		  
	  }

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
		  while(i.hasNext()){
			  Item temp = i.next();
			  temp.printItemAttributes();
			  cartPrice += temp.calculatePrice();
		  }
		  System.out.println("\nTOTAL CART PRICE: $" + df.format(cartPrice));
		  return "";
	  }
	  
	  
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