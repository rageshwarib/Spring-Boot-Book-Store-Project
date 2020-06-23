package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CustomerDetailsDto;

public interface ICustomerDetailsService {
	
	String addCustomerDetails(CustomerDetailsDto customerDetailsDto, String token) ;

    Boolean isCustomerExisted(String token);

}
