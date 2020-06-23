package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.bookstore.model.CustomerDetails;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long>{
	
	 Boolean existsByUsername(String username);
}
