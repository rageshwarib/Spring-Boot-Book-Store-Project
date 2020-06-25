package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.OrderId;

@Repository
public interface OrderIdRepository extends JpaRepository<OrderId, Integer>{
	 OrderId findFirstByOrderByIdDesc();

}
