package com.bridgelabz.bookstore.dto;

public class CartDto {
    private long bookId;
    private int bookQuantity;
    public CartDto() {
		// TODO Auto-generated constructor stub
	}
    public CartDto(long bookId, int bookQuantity) {
      // this.userId = userId;
        this.bookId = bookId;
        this.bookQuantity = bookQuantity;
    }
    
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public int getBookQuantity() {
		return bookQuantity;
	}
	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}
    
    

}
