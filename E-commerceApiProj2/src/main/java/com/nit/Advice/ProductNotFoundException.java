package com.nit.Advice;

public class ProductNotFoundException extends RuntimeException
{
	public ProductNotFoundException(String msg) {
		super(msg);
	}

}
