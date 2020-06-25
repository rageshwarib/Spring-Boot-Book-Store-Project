package com.bridgelabz.bookstore.exception;

public class UserException extends RuntimeException{
	 public enum ExceptionType {
	        CUSTOMER_DETAILS_EXISTED, JWT_NOT_VALID, QUANTITY_EXCEEDED
	    }

	    public ExceptionType type;

	    public UserException(ExceptionType type, String message) {
	        super(message);
	        this.type = type;
	    }

}
