package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.sale;

import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.sale.entity.SaleEntity;
import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.sale.mapper.SalePersistenceMapper;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.Sale;
import com.example.SaaSMultiTentBackEnd.domain.port.out.sale.SaleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleJpaRepositoryAdapter implements SaleRepository {

    private final JpaSaleRepositoy jpaSaleRepositoy;

    public SaleJpaRepositoryAdapter(JpaSaleRepositoy jpaSaleRepositoy) {
        this.jpaSaleRepositoy = jpaSaleRepositoy;
    }


    @Override
    public Sale save(Sale sale) {
        SaleEntity entity = SalePersistenceMapper.toEntity(sale);

        SaleEntity saved = jpaSaleRepositoy.save(entity);

        return SalePersistenceMapper.toDomain(saved);
    }

    @Override
    public List<Sale> getAllSales(Long companyId) {
        return jpaSaleRepositoy.findAllSaleByCompany(companyId)
                .stream()
                .map(SalePersistenceMapper::toDomain)
                .toList();
    }
}
