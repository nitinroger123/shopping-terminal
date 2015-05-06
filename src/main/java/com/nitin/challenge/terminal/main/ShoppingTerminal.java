package com.nitin.challenge.terminal.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nitin.challenge.terminal.bo.Discount;
import com.nitin.challenge.terminal.bo.Product;
import com.nitin.challenge.terminal.exceptions.InvalidDiscountException;
import com.nitin.challenge.terminal.exceptions.InvalidProductException;

/**
 * Represents the shopping terminal
 * @author nitin
 *
 */
public class ShoppingTerminal {
	
	private final Map<String, Product> productMap;
	
	private final Map<String, Discount> discountMap;
	
	/** I'd refactor this to be a multimap using Guava Collections**/
	private final Map<String, List<Product>> shoppingCart;
	
	public ShoppingTerminal() {
		productMap = new HashMap<>();
		
		discountMap = new HashMap<>();
		
		shoppingCart = new HashMap<>();
		
	}
	
	/**
	 * Sets the product prices in the terminal. Runs through the list and
	 * sets an entry in the map with key -> product code and value of product
	 * @param products
	 * @throws InvalidProductException
	 */
	public void setProductPrices(List<Product> products) throws InvalidProductException {
		/** Can be replaced with CollectionUtils if we import Apache collections jar**/
		if(products == null || products.size() <= 0)
			return;
		
		for(Product p: products) {
			if(p == null)
				throw new InvalidProductException();
			else
				productMap.put(p.getCode(), p);
		}
	}
	
	/**
	 * Sets the discounts. Runs through the input list and sets an entry with
	 * key = product code and value = Discount
	 * @param discounts
	 * @throws InvalidDiscountException 
	 */
	public void setDiscounts(List<Discount> discounts) throws InvalidDiscountException {
		if(discounts == null || discounts.size() <= 0)
			return;
		
		for(Discount d: discounts) {
			if(d == null)
				throw new InvalidDiscountException();
			else
				discountMap.put(d.getProductCode(), d);
		}
	}
	
	/**
	 * Scans a single product and adds it to our shopping cart.
	 * @param code
	 * @throws InvalidProductException 
	 */
	public void scan(String code) throws InvalidProductException {
		Product currentProduct = productMap.get(code); 
		
		if(currentProduct == null)
			throw new InvalidProductException();
		
		List<Product> productsInCartForCode = shoppingCart.get(code);
		
		if(productsInCartForCode == null) {
			productsInCartForCode = new ArrayList<Product>();
		}
		productsInCartForCode.add(currentProduct);
		shoppingCart.put(code, productsInCartForCode);
	}

	/**
	 * Calculates the total price of the shopping cart after applying discounts
	 * @return
	 */
	public Double calculateTotal() {
		Double total = new Double(0);
		
		for(String code: shoppingCart.keySet()) {
			Double currentPrice = productMap.get(code).getPrice();
			List<Product> allItemsForCode = shoppingCart.get(code);
			int totalItems = allItemsForCode.size();
			
			/** We have no discount to apply, so just get the price and multiply 
			 * the num of items
			 **/
			if(!discountMap.containsKey(code)) {
				
				total = total + (currentPrice * totalItems);
			}
			
			else {
				Discount discount = discountMap.get(code);
				total = total + discount.applyDiscount(allItemsForCode);
			}
		}
		
		return total;
	}
	
	/**
	 * Resets the terminal to start a new order
	 */
	public void reset() {
		this.shoppingCart.clear();
	}
	
}
