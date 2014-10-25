package task5.ProductInventory;

public class Product {
	private int id;
	private String name;
	private String description;
	private double price; 
	private int quantity;
	private static int idCounter = 0;
	
	public Product(double price, int quantity) {
		this("No name", "No description", price, quantity);
	}
	
	public Product(String name, double price, int quantity) {
		this(name, "No description", price, quantity);
	}
	
	public Product(String name, String description, double price, int quantity) {
		this.setId(idCounter);
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setQuantity(quantity);
		idCounter++;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			name = "No name";
		}
		
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		if (description == null || description.isEmpty()) {
			description = "No description";
		}
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		if (price < 0) {
			price = 0d;
		}
		
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		if (quantity < 0) {
			quantity = 0;
		}
		
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return String.format(
				"Id: %d, Name: %s, Description: %s, Price: %.2f, Quantity: %d",
				this.id, 
				this.name, 
				this.description, 
				this.price, 
				this.quantity);
	}
}
