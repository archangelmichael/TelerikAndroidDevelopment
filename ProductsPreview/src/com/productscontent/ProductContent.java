package com.productscontent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.productscontent.Product;

public class ProductContent {
	/**
	 * An array of sample (dummy) items.
	 */
	public static List<Product> ITEMS = new ArrayList<Product>();

	/**
	 * A map of sample (product) items, by ID.
	 */
	public static Map<String, Product> ITEM_MAP = new HashMap<String, Product>();

	static {
		// Add 3 sample items.
		addItem(new Product("Banan", ProductCategory.Fruit, 10, 10.1f));
		addItem(new Product("Beef Steak ", ProductCategory.Meat, 10, 49.99f));
		addItem(new Product("Trout", ProductCategory.Fish, 20, 35.78f));
		addItem(new Product("Onion", ProductCategory.Vegetable, 10, 10.45f));
		addItem(new Product("Dill", ProductCategory.Spice, 50, 6.2f));
		addItem(new Product("Pepper", ProductCategory.Spice, 100, 6.23f));
		addItem(new Product("Biscuit", ProductCategory.Bakery, 20, 7.22f));
		addItem(new Product("Orange", ProductCategory.Fruit, 12, 10));
	}

	private static void addItem(Product item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}
}
