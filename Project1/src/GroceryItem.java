/**
 * This class implements a grocery item which is to be used in the ShoppingBag class.  
 * 
 * @author Dymytriy Zyunkin
 * @author Shuwei Cao
 * 
 */

public class GroceryItem { //Retrieve and process item names, prices, and if they are taxable
	
	private String name;
	private double price;
	private boolean taxable;
	
	/**
	 * Initializes an instance with given name, price, and tax exemption status.
	 * 
	 * @param name Name
	 * @param price Price
	 * @param taxable Tax exemption status
	 */
	public GroceryItem(String name, double price, boolean taxable) {
		this.name = name;
		this.price = price;
		this.taxable = taxable;
	}
	
	/**
	 * Determines equality between two instances of the GroceryItem class.
	 * @param obj Casted object (other member that comparison is made to).
	 * @return whether two members of the GroceryItem class are equal.
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * */
	public boolean equals(Object obj) {
		if(obj instanceof GroceryItem) {
			GroceryItem item = (GroceryItem) obj;
			return (this.name.equals(item.name) && 
					this.price == item.price && 
					this.taxable == item.taxable);
		}
		return false;
	}
	
	/**
	 * Gives a string representation of a GroceryItem object.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return String representation of the object in the following format:
	 * name: price : taxability
	 * */
	public String toString() {
		String taxableString = (taxable == true) ? "is taxable" : "tax free";
		return String.format("%s: %.02f : %s", name, price, taxableString);
	}
	
	/**
	 * Helper method accessible from other classes which shows if the item is taxable.
	 * @return true is object is taxable, false if it is not.
	 */
	public boolean isTaxable() {
		if(taxable == true) {
			return true;
		}
		return false;
	}
	
	/**
	 * Globally accessible helper method which returns the price of the object.
	 * @return price of the object.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Globally accessible helper method which returns the name attribute of the object.
	 * @return Name of the grocery item.
	 */
	public String getName() { //This way other classes can call the item name
		return name;
	}
}