package com.smartbiz.service.impl;

import com.smartbiz.dto.request.InvoiceRequestDto;
import com.smartbiz.dto.response.InvoiceResponseDto;
import com.smartbiz.entity.Invoice;
import com.smartbiz.entity.Sales;
import com.smartbiz.repo.InvoiceRepo;
import com.smartbiz.repo.SalesRepo;
import com.smartbiz.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final SalesRepo salesRepo;

    public InvoiceServiceImpl(InvoiceRepo invoiceRepo, SalesRepo salesRepo) {
        this.invoiceRepo = invoiceRepo;
        this.salesRepo = salesRepo;
    }

    @Override
    public InvoiceResponseDto create(InvoiceRequestDto dto) {
        Sales sales = salesRepo.findById(dto.getSalesId())
                .orElseThrow(() -> new RuntimeException("Sales not found"));

        Invoice invoice = Invoice.builder()
                .generatedDate(dto.getGeneratedDate())
                .sales(sales)
                .build();

        Invoice saved = invoiceRepo.save(invoice);
        return mapToResponse(saved);
    }

    @Override
    public List<InvoiceResponseDto> getAll() {
        return invoiceRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceResponseDto getById(UUID id) {
        Invoice invoice = invoiceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        return mapToResponse(invoice);
    }

    @Override
    public InvoiceResponseDto getBySalesId(UUID salesId) {
        Invoice invoice = invoiceRepo.findBySalesId(salesId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        return mapToResponse(invoice);
    }

    @Override
    public InvoiceResponseDto update(UUID id, InvoiceRequestDto dto) {
        Invoice invoice = invoiceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        Sales sales = salesRepo.findById(dto.getSalesId())
                .orElseThrow(() -> new RuntimeException("Sales not found"));

        invoice.setGeneratedDate(dto.getGeneratedDate());
        invoice.setSales(sales);

        return mapToResponse(invoiceRepo.save(invoice));
    }

    @Override
    public void delete(UUID id) {
        Invoice invoice = invoiceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoiceRepo.delete(invoice);
    }

    private InvoiceResponseDto mapToResponse(Invoice invoice) {
        return new InvoiceResponseDto(
                invoice.getId(),
                invoice.getGeneratedDate(),
                invoice.getSales().getId());
    }
}
