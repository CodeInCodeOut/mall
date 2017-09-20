package com.dayuanit.shop.exception;

public class ShopException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ShopException() {
		
	}
	
	public ShopException(String errorInfo) {
		super(errorInfo);
	}
	
	public ShopException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ShopException (Throwable cause) {
		super(cause);
	}

}
