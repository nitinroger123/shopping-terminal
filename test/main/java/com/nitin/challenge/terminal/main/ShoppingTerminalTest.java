package com.nitin.challenge.terminal.main;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nitin.challenge.terminal.bo.Discount;
import com.nitin.challenge.terminal.bo.Product;
import com.nitin.challenge.terminal.bo.VolumePriceDiscount;
import com.nitin.challenge.terminal.exceptions.InvalidDiscountException;
import com.nitin.challenge.terminal.exceptions.InvalidProductException;
import com.nitin.challenge.terminal.main.ShoppingTerminal;


public class ShoppingTerminalTest {

	private ShoppingTerminal terminal;
	
	@Before
	public void setUp() {
		terminal = new ShoppingTerminal();
	}
	
	@After
	public void tearDown() {
		terminal.reset();
		terminal = null;
	}
	
	@Test
	public void testSample1() throws InvalidDiscountException, InvalidProductException {
		terminal.setDiscounts(Arrays.asList(getDefaultDiscounts()));
		terminal.setProductPrices(Arrays.asList(getDefaultProducts()));
		
		terminal.scan("A");
		terminal.scan("B");
		terminal.scan("C");
		terminal.scan("D");
		terminal.scan("A");
		terminal.scan("B");
		terminal.scan("A");
		terminal.scan("A");
		
		assertEquals(new Double(32.40), terminal.calculateTotal());
		
	}
	
	@Test
	public void testSample2() throws InvalidDiscountException, InvalidProductException {
		terminal.setDiscounts(Arrays.asList(getDefaultDiscounts()));
		terminal.setProductPrices(Arrays.asList(getDefaultProducts()));
		
		terminal.scan("C");
		terminal.scan("C");
		terminal.scan("C");
		terminal.scan("C");
		terminal.scan("C");
		terminal.scan("C");
		terminal.scan("C");
		
		assertEquals(new Double(7.25), terminal.calculateTotal());
		
	}
	
	@Test
	public void testSample3() throws InvalidDiscountException, InvalidProductException {
		terminal.setDiscounts(Arrays.asList(getDefaultDiscounts()));
		terminal.setProductPrices(Arrays.asList(getDefaultProducts()));
		
		terminal.scan("A");
		terminal.scan("B");
		terminal.scan("C");
		terminal.scan("D");
		
		
		assertEquals(new Double(15.40), terminal.calculateTotal());
		
	}
	
	@Test(expected = InvalidProductException.class)
	public void invalidProductTest() throws InvalidProductException, InvalidDiscountException {
		terminal.setDiscounts(Arrays.asList(getDefaultDiscounts()));
		terminal.setProductPrices(Arrays.asList(getInvalidProducts()));
		
		terminal.scan("A");
		
		terminal.calculateTotal();
		
	}
	
	

	@Test(expected = InvalidProductException.class)
	public void addInvalidProductTest() throws InvalidProductException, InvalidDiscountException {
		terminal.setDiscounts(Arrays.asList(getDefaultDiscounts()));
		terminal.setProductPrices(null);
		
		terminal.scan("A");
		
		terminal.calculateTotal();
		
	}
	
	@Test(expected = InvalidDiscountException.class)
	public void testInvalidDiscount() throws InvalidProductException, InvalidDiscountException{
		terminal.setDiscounts(Arrays.asList(getInvalidDiscount()));
		terminal.setProductPrices(Arrays.asList(getDefaultProducts()));
		
		terminal.scan("A");
		
		terminal.calculateTotal();
	}

	
	private Discount[] getInvalidDiscount() {
		Discount[] discountArr = {null, null};
		return discountArr;
	}

	private Discount[] getDefaultDiscounts() {
		Discount[] discountArr = { new VolumePriceDiscount("A", 4, 7.00),
				new VolumePriceDiscount("C", 6, 6.00) };
		
		return discountArr;
		
	}
	
	private Product[] getInvalidProducts() {
		Product[] productsArr = { new Product("A", 2.0),
				new Product("B", 12.00), new Product("C", 1.25),
				null };
		 
		 return productsArr;
	}
	private Product[] getDefaultProducts() {
		 Product[] productsArr = { new Product("A", 2.0),
				new Product("B", 12.00), new Product("C", 1.25),
				new Product("D", 0.15) };
		 
		 return productsArr;
	}
}
