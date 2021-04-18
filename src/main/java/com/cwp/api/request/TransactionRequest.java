package com.cwp.api.request;

import com.cwp.api.common.Payment;
import com.cwp.api.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

	private Order order;
	private Payment payment;
	
}
