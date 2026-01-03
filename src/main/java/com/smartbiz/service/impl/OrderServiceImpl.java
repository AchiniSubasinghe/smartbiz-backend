package com.smartbiz.service.impl;

import com.smartbiz.dto.request.OrderRequestDto;
import com.smartbiz.dto.response.OrderResponseDto;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Customer;
import com.smartbiz.entity.Order;
import com.smartbiz.repo.BusinessRepo;
import com.smartbiz.repo.CustomerRepo;
import com.smartbiz.repo.OrderRepo;
import com.smartbiz.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final BusinessRepo businessRepo;

    public OrderServiceImpl(OrderRepo orderRepo, CustomerRepo customerRepo, BusinessRepo businessRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.businessRepo = businessRepo;
    }

    @Override
    public OrderResponseDto create(OrderRequestDto dto) {
        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Business business = businessRepo.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Order order = Order.builder()
                .orderDate(dto.getOrderDate())
                .customer(customer)
                .business(business)
                .build();

        Order saved = orderRepo.save(order);
        return mapToResponse(saved);
    }

    @Override
    public List<OrderResponseDto> getAll() {
        return orderRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto getById(UUID id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToResponse(order);
    }

    @Override
    public List<OrderResponseDto> getByCustomerId(UUID customerId) {
        return orderRepo.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getByBusinessId(UUID businessId) {
        return orderRepo.findByBusinessId(businessId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto update(UUID id, OrderRequestDto dto) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Business business = businessRepo.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        order.setOrderDate(dto.getOrderDate());
        order.setCustomer(customer);
        order.setBusiness(business);

        return mapToResponse(orderRepo.save(order));
    }

    @Override
    public void delete(UUID id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepo.delete(order);
    }

    private OrderResponseDto mapToResponse(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getOrderDate(),
                order.getCustomer().getId(),
                order.getBusiness().getId());
    }
}
