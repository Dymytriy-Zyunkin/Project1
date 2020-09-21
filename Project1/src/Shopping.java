/**
 * This class uses recursion to accept user input and calls the
 * ShoppingBag class to process all user inputs.
 * 
 * @author Dymytriy Zyunkin
 * @author Shuwei Cao
 *
 */

import java.util.Scanner;

public class Shopping {
	
	private ShoppingBag s = new ShoppingBag();
	
	public void run() {
		
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		int inputType = type(input);
		GroceryItem item;
		
		switch(inputType) {
			case 0:
				item = makeItem(input);
				s.add(item);
				System.out.println(String.format("%s added to the bag.", item.getName()));
				run();
				
			case 1:
				item = makeItem(input);
				s.remove(item); //Calls the remove method from ShoppingBag
				run();
				
			case 2:
				s.print(); //Calls the print method from ShoppingBag
				run();
				
			case 3:
				s.checkout(); //Calls the checkout method from ShoppingBag
				s.newCart();
				run();
				
			case 4:
				sc.close();//Terminates the scanner
				System.out.println("Thanks for shopping with us!");
				System.exit(1); //Execute all commands
				
			case -1:
				System.out.println("Invalid command!");
				run();
		}
	}
	/**
	 * helper method that assigns numerical values to each input
	 * 
	 * @param s Input string (regardless of correct format)
	 * @return:
	 * ADD -> 0
	 * REMOVE -> 1
	 * PRINT -> 2
	 * CHECK OUT -> 3
	 * QUIT -> 4
   	 * OTHER -> -1
	 */
	private int type(String s) {
		String tokens[] = s.split("\\s+");
		String t = tokens[0];
		if(t.equals("A")){
			return 0;
		} else if(t.equals("R")){
			return 1;
		} else if (t.equals("P")) {
			return 2;
		} else if(t.equals("C")) {
			return 3;
		} else if(t.equals("Q")) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Helper method that parses a valid string input and makes it an item
	 * @param s Input GroceryItem as a string (assumes correct formatting)
	 * @return  GroceryItem representation of input string
	 */
	private GroceryItem makeItem(String s) {
		String tokens[] = s.split("\\s+");
		String name = tokens[1];
		double price = Double.parseDouble(tokens[2]);
		boolean taxable = Boolean.parseBoolean(tokens[3]);  
		return new GroceryItem(name, price, taxable);
	}
}
