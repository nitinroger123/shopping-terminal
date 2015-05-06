package com.nitin.challenge.terminal.bo;

import java.util.List;

/**
 * Represents a Discount in our system
 * @author nitin
 *
 */
public interface Discount {
	
	/**
	 * Gets the product code for which this discount applies to
	 * @return
	 */
	public String getProductCode();
	
	/**
	 * Applies the discount on the list of products and returns the final price
	 * @param products
	 * @return
	 */
	public Double applyDiscount(List<Product> products);

}
