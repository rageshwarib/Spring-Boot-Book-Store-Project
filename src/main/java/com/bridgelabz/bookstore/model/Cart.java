package com.bridgelabz.bookstore.model;

import java.io.Serializable;

import javax.persistence.Column;
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

    public Cart(int id, long userId, long bookId, int bookQuantity) {
	super();
	this.id = id;
	this.userId = userId;
	this.bookId = bookId;
	this.bookQuantity = bookQuantity;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
