package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.sale;

import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.sale.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface JpaSaleRepositoy extends JpaRepository<SaleEntity,Long> {
    List<SaleEntity> findAllSaleByCompany(Long companyId);


}
