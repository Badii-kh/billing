package com.khila.billing.infrastructure.adapter.in.rest;

import com.khila.billing.application.service.OrderService;
import com.khila.billing.domain.pricing.model.Order;
import com.khila.billing.infrastructure.adapter.in.rest.dto.OrderDto;
import com.khila.billing.infrastructure.adapter.in.rest.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommandController {

	private final OrderService orderService;
	private final OrderMapper orderMapper;

	@GetMapping("/command/{id}")
	public OrderDto findCommandById(@PathVariable long id){
        Order order = orderService.retrieve(id);
        return orderMapper.apply(order);
	}
}
