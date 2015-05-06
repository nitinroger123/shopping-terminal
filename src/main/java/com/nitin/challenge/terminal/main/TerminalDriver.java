package com.nitin.challenge.terminal.main;

import java.util.Arrays;

import com.nitin.challenge.terminal.bo.Discount;
import com.nitin.challenge.terminal.bo.Product;
import com.nitin.challenge.terminal.bo.VolumePriceDiscount;
import com.nitin.challenge.terminal.exceptions.InvalidDiscountException;
import com.nitin.challenge.terminal.exceptions.InvalidProductException;

/**
 * Driver program that just runs through the sample test cases
 * 
 * @author nitin
 *
 */
public class TerminalDriver {

	public static void main(String[] args) throws InvalidDiscountException,
			InvalidProductException {
		System.out.println("Starting terminal");

		Product[] productsArr = { new Product("A", 2.0),
				new Product("B", 12.00), new Product("C", 1.25),
				new Product("D", 0.15) };

		Discount[] discountArr = { new VolumePriceDiscount("A", 4, 7.00),
				new VolumePriceDiscount("C", 6, 6.00) };
		
		ShoppingTerminal terminal = new ShoppingTerminal();
		terminal.setDiscounts(Arrays.asList(discountArr));
		terminal.setProductPrices(Arrays.asList(productsArr));

		String[] checkouts = { "ABCDABAA", "CCCCCCC", "ABCD" };

		for (String checkout : checkouts) {
			terminal.reset();
			for(int i = 0; i < checkout.length(); ++i) {
				String item = String.valueOf(checkout.charAt(i));
				terminal.scan(item);
			}
			System.out.println(terminal.calculateTotal());
		}

	}

}
