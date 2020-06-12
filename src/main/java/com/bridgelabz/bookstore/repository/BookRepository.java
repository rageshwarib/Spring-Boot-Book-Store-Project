package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, PagingAndSortingRepository<Book, Long> {
   // Page<Book> findAllBy(Pageable pageable);
    Page<Book> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Book> findAllByOrderByPriceDesc(Pageable pageable);
    Page<Book> findAllByOrderByPublicationDateDesc(Pageable pageable);
    Book findById(int bookId);
}
