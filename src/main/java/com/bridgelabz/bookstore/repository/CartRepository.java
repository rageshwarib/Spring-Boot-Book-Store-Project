package com.bridgelabz.bookstore.repository;

import java.util.List;

import com.bridgelabz.bookstore.dto.CartDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	// boolean existsCartByUserId(Long userId);
    boolean existsCartByBookId(long bookId);
    List<Cart> findAllByUserId(long userId);
    
    @Modifying
    @Query("DELETE FROM Cart cart WHERE cart.bookId = :bookId AND cart.userId = :userId")
    void deleteCartByBookIdAndUserId(@Param("bookId") long bookId, @Param("userId") long userId);
   List<Cart> findByUserId(long userId);

    CartDto findByBookId(int userId);

    CartDto findByBookIdAndUserId(long bookId, long userId);
    
}

