package com.nitin.challenge.terminal.bo;

/**
 * Represents a single Product
 * @author nitin
 *
 */
public class Product {
	
	private final String code;
	
	private final Double price;

	public String getCode() {
		return code;
	}

	public Double getPrice() {
		return price;
	}

	public Product(String code, Double price) {
		super();
		this.code = code;
		this.price = price;
	}
	
	
	
	
}
