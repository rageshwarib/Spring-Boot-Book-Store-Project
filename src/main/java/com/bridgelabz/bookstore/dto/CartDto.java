package com.bridgelabz.bookstore.dto;

import java.io.Serializable;

public class CartDto implements Serializable{
	private int userId;
    private long bookId;
    private int bookQuantity;
   public CartDto() {
		// TODO Auto-generated constructor stub
	}
    public CartDto(int userId, long bookId, int bookQuantity) {
       this.userId = userId;
        this.bookId = bookId;
        this.bookQuantity = bookQuantity;
    }
    
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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