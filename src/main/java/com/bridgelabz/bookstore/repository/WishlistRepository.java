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
<<<<<<< HEAD
	// boolean existsWishlistByUserId(Long userId);
    boolean existsWishlistByBookId(long bookId);
=======
	boolean existsWishlistByUserId(int userId);
    boolean existsWishlistByBookId(int bookId);
>>>>>>> 8bffe02e19b00124c650bcdd0c93dd50963771f2
    List<Wishlist> findAllByUserId(long userId);


    @Modifying
    @Query("DELETE FROM Wishlist wishlist WHERE wishlist.bookId = :bookId AND wishlist.userId = :userId")
    void deleteWishlistByBookIdAndUserId(@Param("bookId") long bookId, @Param("userId") long userId);

}
