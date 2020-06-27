package com.bridgelabz.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wishlist {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private long userId;
<<<<<<< HEAD
    private long bookId;
=======
    private int bookId;
>>>>>>> 8bffe02e19b00124c650bcdd0c93dd50963771f2
    
    public Wishlist() {
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
    

}
