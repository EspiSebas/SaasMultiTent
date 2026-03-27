package com.example.SaaSMultiTentBackEnd.domain.model.sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Sale {
    private Long id;
    private Long companyId;
    private LocalDateTime date;
    private BigDecimal subtotal;
    private  BigDecimal discount;
    private BigDecimal total;
    private BigDecimal tax;
    private PaymentMethod paymentMethod;

    private List<DetailSale> details;

    public Sale(Long id, Long companyId, LocalDateTime date, BigDecimal subtotal, BigDecimal discount, BigDecimal total, BigDecimal tax, PaymentMethod paymentMethod, List<DetailSale> details) {
        this.id = id;
        this.companyId = companyId;
        this.date = date;
        this.subtotal = subtotal;
        this.discount = discount;
        this.total = total;
        this.tax = tax;
        this.paymentMethod = paymentMethod;
        this.details = details;
    }

    public void calculateTotals() {
        this.subtotal = details.stream()
                .map(DetailSale::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.tax = subtotal.multiply(new BigDecimal("0.19"));

        this.total = subtotal.add(tax)
                .subtract(discount != null ? discount : BigDecimal.ZERO);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<DetailSale> getDetails() {
        return details;
    }

    public void setDetails(List<DetailSale> details) {
        this.details = details;
    }
}
