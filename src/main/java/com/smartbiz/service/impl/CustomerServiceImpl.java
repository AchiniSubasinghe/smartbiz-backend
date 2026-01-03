package com.smartbiz.service.impl;

import com.smartbiz.dto.request.CustomerRequestDto;
import com.smartbiz.dto.response.CustomerResponseDto;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Customer;
import com.smartbiz.repo.BusinessRepo;
import com.smartbiz.repo.CustomerRepo;
import com.smartbiz.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final BusinessRepo businessRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo, BusinessRepo businessRepo) {
        this.customerRepo = customerRepo;
        this.businessRepo = businessRepo;
    }

    @Override
    public CustomerResponseDto create(CustomerRequestDto dto) {
        Business business = businessRepo.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Customer customer = Customer.builder()
                .customerName(dto.getCustomerName())
                .business(business)
                .build();

        Customer saved = customerRepo.save(customer);
        return mapToResponse(saved);
    }

    @Override
    public List<CustomerResponseDto> getAll() {
        return customerRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto getById(UUID id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapToResponse(customer);
    }

    @Override
    public CustomerResponseDto getByName(String name) {
        Customer customer = customerRepo.findByCustomerName(name)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapToResponse(customer);
    }

    @Override
    public List<CustomerResponseDto> getByBusinessId(UUID businessId) {
        return customerRepo.findByBusinessId(businessId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto update(UUID id, CustomerRequestDto dto) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Business business = businessRepo.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        customer.setCustomerName(dto.getCustomerName());
        customer.setBusiness(business);

        return mapToResponse(customerRepo.save(customer));
    }

    @Override
    public void delete(UUID id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customerRepo.delete(customer);
    }

    private CustomerResponseDto mapToResponse(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getCustomerName(),
                customer.getBusiness().getId());
    }
}
