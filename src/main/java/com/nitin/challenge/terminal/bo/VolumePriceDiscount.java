package com.nitin.challenge.terminal.bo;

import java.util.List;

/**
 * Represents a VolumeDiscount.
 * 
 * This object contains the price for a particular product for a certain volume
 * 
 * @author nitin
 *
 */
public class VolumePriceDiscount implements Discount{

	private final String code;
	
	private final Double discountPrice;
	
	private final Integer volume;
	
	public VolumePriceDiscount(String code, Integer volume, Double price) {
	
		this.code = code;
	
		this.discountPrice = price;
		
		this.volume = volume;
	}
	
	@Override
	public String getProductCode() {
		return code;
	}

	/**
	 * Applies the discount to a list of products of the same code
	 */
	@Override
	public Double applyDiscount(List<Product> products) {
		if(products == null || products.size() <= 0) {
			return new Double(0);
		}
		
		Double originalPrice = products.get(0).getPrice();
		Integer totalSize = products.size();
		
		/** Discount doesn't apply **/
		if(totalSize < volume)
			return totalSize * originalPrice;
		
		/**
		 * If the volume is 3 and we have 10 elements we have
		 * 3 groups of products that can be purchased at a discount
		 * and 1 group at the original price.
		 */
		int discountGroup = totalSize / volume;
		int nonDiscountGroup = totalSize % volume;
		Double totalPrice = new Double(nonDiscountGroup * originalPrice) + (discountGroup * discountPrice);
		return totalPrice;
	}

}
