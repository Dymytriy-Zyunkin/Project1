/**
 * This class creates a shopping bag which is used in Shopping.java. It also has a public helper method
 * checkout() and newCart(), which makes the checkout method in Shopping.java easier.
 * 
 * @author Dymytriy Zyunkin
 * @author Shuwei Cao
 */

public class ShoppingBag {
	
	/**
	 * Initializing bag - an empty array of GroceryItem objects and size - amount of items currently in bag
	 */
	private GroceryItem[] bag;
	private int size;
	
	/**
	 * Initializes an instance with preset size and capacity. Creates an empty bag of 5 elements.
	 */
	public ShoppingBag() {
		size = 0;
		int capacity = 5;
		bag = new GroceryItem[capacity];
	}
	
	/**
	 * Public helper method which resets the size, capacity, and empties the bag array.
	 */
	public void newCart() {
		size = 0;
		int capacity = 5;
		bag = new GroceryItem[capacity];
	}
	
	/**
	 * Local helper method that returns the index of the desired item. Returns -1 if item is not in bag.
	 *  
	 * @param item GroceryItem to be found in the bag array.
	 * @return Index of GroceryItem in the bag array. Returns -1 is the item is not present.
	 */
	private int find(GroceryItem item) {
		for(int i = 0; i<size; i++) {
			if(item.equals(bag[i])) {
				return i;
			}
		}
		return -1; //item is not found
	}
	
	/**
	 * Method which increments capacity by 5 units when needed. 
	 */
	private void grow() { 
		int capacity = bag.length + 5;
		GroceryItem[] tempBag = new GroceryItem[capacity];
		for(int i = 0; i<size; i++) {
			tempBag[i] = bag[i];
		}
		bag = tempBag;
	}
	
	/**
	 * Adds item and calls grow method when bag is full when trying to add more items
	 * 
	 * @param item GroecryItem to be input (user defined)
	 */
	public void add(GroceryItem item) {
		if(size == bag.length) {
			grow();
		}
		bag[size] = item;
		size++;
	}
	
	/**
	 * Remove an item in bag. If item is not present in bag, return false.
	 * 
	 * @param item GroceryItem to be removed.
	 * @return boolean true if item was successfully removed, false if it was not present in bag.
	 */
	public boolean remove(GroceryItem item) {
		int index = find(item);
		
		if(index == -1) {
			System.out.println("Unable to remove, this item is not in the bag.");
			return false;
		}
		
		bag[index] = bag[size-1];
		bag[size] = null;
		size--;
		System.out.println(String.format("%s %.02f removed", item.getName(), item.getPrice()));
		
		return true;
	}
	
	/**
	 * Reads the price from input and returns it for other methods.
	 * 
	 * @return double value of the final sales price.
	 */
	public double salesPrice(){ 
		double total = 0;
		for(int i = 0; i<size; i++) {
			total += bag[i].getPrice();
		}
		return total;
	}
	
	/**
	 * Calculates 6.625 percent sales tax on all taxable items.
	 * 
	 * @return double value of the final tax applied.
	 */
	public double salesTax() {

		double salesTaxValue = 0;
		double salesTaxPct = 0.06625;
		
		for(int i = 0; i<size; i++) {
			if(bag[i].isTaxable()) {
				salesTaxValue += bag[i].getPrice()*salesTaxPct;
			}
		}
		return salesTaxValue;
	}
	
	/**
	 * Unless empty, prints out all items in the shopping bag
	 */
	public void print() {
		if(size == 0) {
			System.out.println("The bag is empty!");
			return;
		}
		
		System.out.println(String.format("**You have %d items in the bag.",size));
		for(int i = 0; i<size; i++) {
			System.out.println("•"+bag[i].toString());
		}
		System.out.println("**End of list");
	}
	
	/**
	 * Unless bag is empty, prints out all items along with price, tax, and total
	 */
	public void checkout() {
		if(size == 0) {
			System.out.println("The bag is empty!");
			return;
		}
		System.out.println(String.format("**Checking out %d items", size));
		for(int i = 0; i<size; i++) {
			System.out.println("•"+bag[i].toString());
		}
		System.out.println(String.format("*Sales total: %.02f", salesPrice()));
		System.out.println(String.format("*Sales tax: %.2f", salesTax()));
		System.out.println(String.format("*Total amount paid: %.02f", salesPrice() + salesTax()));		
	}
	
	/**
	 * Testbed main for the ShoppingBag class. 
	 * @param args standard input for a Java main method.
	 */
	public static void main (String[] args) {
		System.out.println("---TESTING ENVIRONMENT---");
		System.out.println();

		GroceryItem g1 = new GroceryItem("a", 1, true);
		GroceryItem g2 = new GroceryItem("b", 2, false);
		GroceryItem g3 = new GroceryItem("c", 3, true);
		GroceryItem g4 = new GroceryItem("d", 4, false);
		GroceryItem g5 = new GroceryItem("e", 5, true);
		GroceryItem g6 = new GroceryItem("f", 6, false);

		ShoppingBag s = new ShoppingBag();
		s.add(g1);
		s.add(g2);
		s.add(g3);
		s.add(g4);
		s.add(g5);
		
		System.out.println("starting list:");
		s.print();
		System.out.println(String.format("size = %d",s.size));
		System.out.println(String.format("sales tax = %.2f",s.salesTax()));
		System.out.println(String.format("sales price = %.2f",s.salesPrice()));
		System.out.println();
		
		System.out.println("finding 'c'");
		System.out.println("Index of f is" +Integer.toString(s.find(g3)));
		System.out.println();

		System.out.println("adding 'f'");
		s.add(g6);
		s.print();
		System.out.println(String.format("size = %d",s.size));
		System.out.println(String.format("sales tax = %.2f",s.salesTax()));
		System.out.println(String.format("sales price = %.2f",s.salesPrice()));
		System.out.println();

		System.out.println("removing 'c'");
		s.remove(g3);
		s.print();
		System.out.println(String.format("size = %d",s.size));
		System.out.println(String.format("sales tax = %.2f",s.salesTax()));
		System.out.println(String.format("sales price = %.2f",s.salesPrice()));
		System.out.println();

		System.out.println("removing 'a'");
		s.remove(g1);
		s.print();
		System.out.println(String.format("size = %d",s.size));
		System.out.println(String.format("sales tax = %.2f",s.salesTax()));
		System.out.println(String.format("sales price = %.2f",s.salesPrice()));
		System.out.println();

		System.out.println("removing 'f'");
		s.remove(g6);
		s.print();
		System.out.println(String.format("sales tax = %.2f",s.salesTax()));
		System.out.println(String.format("sales price = %.2f",s.salesPrice()));
		System.out.println(String.format("sales price = %.8f",s.salesPrice()));
		System.out.println();
		
		System.out.println("checking out");
		s.checkout();
	}
}