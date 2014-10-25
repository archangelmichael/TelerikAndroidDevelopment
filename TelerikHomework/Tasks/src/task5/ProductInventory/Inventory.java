package task5.ProductInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inventory {
	private static Logger logger = Logger.getLogger("InventoryLogger");
	private HashMap<Integer, Product> products;
	
	public Inventory(HashMap<Integer, Product> productsList) {
		this.setProducts(productsList);
	}
	
	public Inventory() {
		this.setProducts(new HashMap<Integer, Product>());
	}
	
	public void addProduct (Product product) {
		this.getProducts().put(product.getId(), product);
		logger.log(Level.INFO, "Product with id: {0} added successfully!", product.getId());
	}

	public HashMap<Integer, Product> getProducts() {
		return this.products;
	}

	private void setProducts(HashMap<Integer, Product> products) {
		this.products = products;
	}
	
	public ArrayList<Product> getListOfProducts() {
		ArrayList<Product> list = new ArrayList<Product>();
		for (Product value : this.getProducts().values()) {
		    list.add(value);
		}
		
		return list;
	}
	
	public double getInventoryValue() {
		double inventorySum = 0;
		ArrayList<Product> list = getListOfProducts();
		for (Product product : list) {
			inventorySum += product.getQuantity() * product.getPrice();
		}
		
		return inventorySum;
	}
	
	public Product getProductById(int productId) {
		Product product = null;
		if (!this.getProducts().containsKey(productId)) {
			logger.log(Level.WARNING, "No product with id: {0} in market!", productId);
			return product;
		}
		
		product = this.getProducts().get(productId);
		return product;
	}
	
	public void sellProduct(int productId, int quantity) {
				
		Product product = this.getProductById(productId);
		if (product == null) {
			logger.log(Level.INFO, String.format("Cant sell product with id: %d!", productId));
			return;
		}
		
		if (product.getQuantity() < quantity) {
			logger.log(Level.INFO, String.format("Insufficient quantity for product with id: %d!", productId));
			return;
		}
		
		double productPrice = product.getPrice();
		double sellValue = quantity * productPrice;
		product.setQuantity(product.getQuantity() - quantity);
		logger.log(Level.INFO, String.format("Sold %d pieces of product with id: %d for: %f!", quantity, productId, sellValue));
		this.products.put(productId, product);		
	}
}
