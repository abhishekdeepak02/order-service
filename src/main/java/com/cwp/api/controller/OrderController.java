package com.cwp.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwp.api.request.TransactionRequest;
import com.cwp.api.response.TransactionResponse;
import com.cwp.api.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/orders")
	public ResponseEntity<TransactionResponse> submitOrder(@RequestBody TransactionRequest request){
		
		if(!ObjectUtils.isEmpty(request)) {
			
			TransactionResponse txnResponse = orderService.saveOrder(request);
			return new ResponseEntity<>(txnResponse, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
	}
}
