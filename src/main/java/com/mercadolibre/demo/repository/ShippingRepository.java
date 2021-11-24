package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.dto.response.ShippingNativeDTO;
import com.mercadolibre.demo.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
    // lista de produto

    @Query( value = "SELECT new com.mercadolibre.demo.dto.response.ShippingNativeDTO(iop.id, sa.price, p.name ) " +
            "from Product p, SalesAd sa, InboundOrder io, BatchStock bs, ItemOfProduct iop,   Section s , PurchaseOrder po where " +
            "p.id = sa.product.id AND " +
            "sa.id = bs.idSalesAd.id AND " +
            "bs.idBatchNumber = io.batchStock.idBatchNumber AND " +
            "io.section.idSection = s.idSection AND " +
            "iop.salesAd.id = sa.id AND " +
            "po.id = iop.purchaseOrder.id AND " +
            "iop.purchaseOrder.id = ?1")
    List<ShippingNativeDTO> findPurchase(Long purchase_order);
}
