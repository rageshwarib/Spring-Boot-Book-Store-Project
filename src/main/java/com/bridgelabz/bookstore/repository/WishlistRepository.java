package com.bridgelabz.bookstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
	// boolean existsWishlistByUserId(Long userId);
    boolean existsWishlistByBookId(long bookId);
    List<Wishlist> findAllByUserId(long userId);


    @Modifying
    @Query("DELETE FROM Wishlist wishlist WHERE wishlist.bookId = :bookId AND wishlist.userId = :userId")
    void deleteWishlistByBookIdAndUserId(@Param("bookId") long bookId, @Param("userId") long userId);

}
