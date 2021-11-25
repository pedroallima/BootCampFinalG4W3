package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.Shipping;
import com.mercadolibre.demo.model.ShippingHistory;
import com.mercadolibre.demo.model.ShippingS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingHistoryRepository extends JpaRepository<ShippingHistory, Long> {

    @Query(nativeQuery = true, value = "SELECT s.* from  shipping s where upper(trim(s.id_shipping)) = ?1")
    Shipping listShipping(Long name);
    @Query(nativeQuery = true, value = "SELECT s.* from  shipping_status s where upper(trim(s.id_shipping_status)) = ?1")
    ShippingS listStatus (Long name);

}
