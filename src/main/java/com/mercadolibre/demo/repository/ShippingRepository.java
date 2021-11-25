package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.dto.TrakingListDTO;
import com.mercadolibre.demo.dto.response.ShippingNativeDTO;
import com.mercadolibre.demo.model.ItemOfProduct;
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

    // lista de rastreio
    @Query(nativeQuery = true, value = "SELECT s.* from  shipping s where upper(trim(s.id_shipping)) = ?1")
    Shipping listTrank (String name);

/*    @Query(nativeQuery = true, value = "SELECT s.* from  shipping_status s where upper(trim(s.id_shipping_status)) = ?1")
    Shipping listHistory (String name);*/

    @Query(value = "SELECT new com.mercadolibre.demo.dto.TrakingListDTO(sh.id_history_shipping, sh.shippingS.name, sh.date) from  Shipping s, ShippingHistory sh, ShippingS ss where  sh.shipping.idsShipping = s.idsShipping AND ss.idsStatus = sh.shippingS.idsStatus AND s.code_track = ?1")
    List<TrakingListDTO> listHistory (String name);




}
