package Assignment3;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;			// To be used to format prices properly

public class A3Driver 
	{

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
		  
		
		//Parse input, take appropriate actions.
		
		//Stub for arraylist.
		ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
		
		// General code example for how to iterate an array list. You will have to modify this heavily, to suit your needs.
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) 
		{
			Item temp = i.next();
			temp.calculatePrice(); 
			temp.printItemAttributes();
			//This (above) works because of polymorphism: a determination is made at runtime, 
			//based on the inherited class type, as to which method is to be invoked. Eg: If it is an instance
			// of Grocery, it will invoke the calculatePrice () method defined in Grocery.
		}		
	  }

	  public static String processInput(String input){
		  String output = new String();
		  return output;
	  }
}