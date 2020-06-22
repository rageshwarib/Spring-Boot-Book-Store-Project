package com.bridgelabz.bookstore.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
    private long userId;
    private long bookId;
    private int bookQuantity;
    
   public Cart() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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
