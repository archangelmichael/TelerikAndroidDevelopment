package task5.ProductInventory;

import java.util.ArrayList;
import java.util.Iterator;

public class Market {

	public static void main(String[] args) {
		Inventory invertory = new Inventory();
		invertory.addProduct(new Product(22.4, 20));

		invertory.addProduct(new Product("Salad", 5.4, 200));
		invertory.addProduct(new Product("Potato", 2.2, 2000));
		invertory.addProduct(new Product("Tomato", 2.1, 3000));
		invertory.addProduct(new Product("Milk", "1 litre bottle of milk", 4.55, 300));
		invertory.addProduct(new Product("Rakia", 52.3, 20));
		
		System.out.println(String.format("Current total value of inventory: %s USD", String.valueOf(invertory.getInventoryValue())));
		
		invertory.sellProduct(2, 20);
		System.out.println(String.format("Current total value of inventory: %s USD", String.valueOf(invertory.getInventoryValue())));
	
		ArrayList<Product> allProducts = invertory.getListOfProducts();
		System.out.println("List of all products in invertory: ");
		for (Product product : allProducts) {
			System.out.println(product.toString());
		}
		
		invertory.sellProduct(2, 2000);
		invertory.sellProduct(20, 20);
	}

}
