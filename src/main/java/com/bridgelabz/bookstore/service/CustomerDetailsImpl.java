package com.bridgelabz.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.CustomerDetailsDto;
import com.bridgelabz.bookstore.model.CustomerDetails;
import com.bridgelabz.bookstore.repository.CustomerDetailsRepository;
import com.bridgelabz.bookstore.security.jwt.JwtUtils;
import com.bridgelabz.bookstore.utility.MapperService;

@Service
public class CustomerDetailsImpl implements ICustomerDetailsService {
	
	 	@Autowired
	    private CustomerDetailsRepository customerDetailsRepository;

	    @Autowired
	    private MapperService converterService;

	    @Autowired
	    private JwtUtils jwtUtils;

	    @Override
	    public String addCustomerDetails(CustomerDetailsDto customerDetailsDto, String token){
	        if (jwtUtils.validateJwtToken(token)) {
	            String userName = jwtUtils.getUserNameFromJwtToken(token);
	            CustomerDetails customerDetails = converterService.converToCustomerDetailsEntity(customerDetailsDto);
	            customerDetails.setUsername(userName);
	            customerDetailsRepository.save(customerDetails);
	            return "Customer details added";
	        } else
	            return "Token is not valid";
	    }

	    @Override
	    public Boolean isCustomerExisted(String token) {
	    	if (jwtUtils.validateJwtToken(token)) {
	            String username = jwtUtils.getUserNameFromJwtToken(token);
	            return customerDetailsRepository.existsByUsername(username);
	    	}else
	    		return false;
	    }
}
