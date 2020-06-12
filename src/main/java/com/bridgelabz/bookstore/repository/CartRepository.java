package com.bridgelabz.bookstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	boolean existsCartByUserId(int userId);
    boolean existsCartByBookId(int bookId);
    List<Cart> findAllByUserId(int userId);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Cart cart WHERE cart.bookId = :bookId AND cart.userId = :userId")
    void deleteCartsByBookIdAndUserId(@Param("bookId") int bookId, @Param("userId") int userId);
    
}

