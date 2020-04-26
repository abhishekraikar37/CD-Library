package com.zensar.cdl.exception;

public class InvalidPriceException extends Exception{
	double price;
	public InvalidPriceException(double price) {
		this.price=price;
	}
	@Override
	public String toString() {
			return "Price cannot be Negative "+price;
	}
}
