package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.ResetPasswdToken;

@Repository
public interface ResetPasswdTokenRepository  extends JpaRepository<ResetPasswdToken, Long>{
	 ResetPasswdToken findByToken(String token);

}
