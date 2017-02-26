package StoreManager;

public class StoreItems {

		int id;
		String description;
		String name;
		int quantity;
		double price;
		
		public StoreItems(int id, String name, String description, int quantity , double price) {
			super();
			this.id = id;
			this.description = description;
			this.price=price;
			this.quantity = quantity;
			this.name=name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
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

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public Integer getId() {
			return id;
		}
	}
