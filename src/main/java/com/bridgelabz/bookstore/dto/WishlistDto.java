package com.bridgelabz.bookstore.dto;

public class WishlistDto {
	 private long bookId;
	 public WishlistDto() {
		// TODO Auto-generated constructor stub
	}
	 public WishlistDto(long bookId) {
	        this.bookId = bookId;
	    }
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	 
}
