package StoreManager;

public class HistoryBean {
	private int id;
	private String name;
	private int quantity;
	private double total;
	private int price;
	private String username;
	
	public HistoryBean(int id, String username, String name, int quantity, int price, double total) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.total = total;
		this.price = price;
		this.username=username;
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
