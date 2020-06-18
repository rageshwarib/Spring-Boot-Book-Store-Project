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
	
    private int userId;
    private long bookId;
    private int bookQuantity;
    
   public Cart() {
    }
//    public Cart(int userId, long bookId, int bookQuantity) {
//        this.userId = userId;
//        this.bookId = bookId;
//        this.bookQuantity = bookQuantity;
//    }
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
