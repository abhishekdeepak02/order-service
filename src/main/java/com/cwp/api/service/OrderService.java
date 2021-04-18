package com.cwp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cwp.api.common.Payment;
import com.cwp.api.entity.Order;
import com.cwp.api.repository.OrderRepository;
import com.cwp.api.request.TransactionRequest;
import com.cwp.api.response.TransactionResponse;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RestTemplate template;
	
	public TransactionResponse saveOrder(TransactionRequest request) {
		String message = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		//REST call to payment Service
		Payment paymentRes = template.postForObject("http://localhost:8060/payment/pay", payment, Payment.class);
		
		message = paymentRes.getPaymentStatus().equalsIgnoreCase("success") ? "Payment Successfull, Order is placed!!" : "Payment faild, order saved to cart!!";
		
		Order ord = orderRepository.save(order);
		return new TransactionResponse(ord, paymentRes.getAmount(), paymentRes.getTransactionId(), message);
		
	}
}
