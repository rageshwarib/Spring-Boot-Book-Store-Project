package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.CustomerDetailsDto;
import com.bridgelabz.bookstore.service.ICustomerDetailsService;

@RestController
@RequestMapping("/customer-details")
public class CustomerDetailController {
	 @Autowired
	    private ICustomerDetailsService iCustomerDetailsService;

	    @PostMapping("/adddetails")
	    public ResponseEntity<String> addCustomerDetails(@RequestBody CustomerDetailsDto customerDetailsDto, @RequestHeader String token) {
	        return new ResponseEntity<String>(iCustomerDetailsService.addCustomerDetails(customerDetailsDto, token), HttpStatus.OK);
	    }

	    @GetMapping("/isexisted")
	    public ResponseEntity<Boolean> isCustomerExisted(@RequestHeader String token) {
	        return new ResponseEntity<Boolean>(iCustomerDetailsService.isCustomerExisted(token), HttpStatus.OK);
	    }

}
