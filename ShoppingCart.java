package StoreManager;
import java.util.HashMap;
public class ShoppingCart {
	private HashMap<String, Integer> cart = new HashMap<String, Integer>();
	private int totalItems = 0;
	public HashMap<String, Integer> getCart() {
		return cart;
	}
	public int getTotalItems() {
		return this.totalItems;
	}
	public void setItem(String id) {
		if(!cart.containsKey(id)){
			cart.put(id, 1);
		} 
		else {
			int value = cart.get(id);
			cart.put(id, ++value);
		}
		this.totalItems++;
	}
}
