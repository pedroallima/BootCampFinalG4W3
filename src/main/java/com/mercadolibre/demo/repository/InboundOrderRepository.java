package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.dto.response.IdDTO;
import com.mercadolibre.demo.model.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolibre.demo.model.InboundOrder;

import java.util.List;

@Repository
public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {

    @Query(nativeQuery = true, value = "SELECT io.* from sales_ad sa," +
            " batch_stock bs, inbound_order io, section s ,products p," +
            " ware_house wh where sa.idsales_ad = bs.idsales_ad AND" +
            " bs.idbatch_number = io.idbatch_number AND io.section_code = s.section_code AND" +
            " p.idproduct = sa.idproduct AND s.id_ware_house = wh.id_ware_house AND p.idproduct= ?1 AND  wh.id_ware_house = ?2")
    List<InboundOrder> buscarSessaoInboundOrder(Long id, Long idWarehouse);

    @Query(nativeQuery = true, value = "SELECT io.* from sales_ad sa," +
            " batch_stock bs, inbound_order io, section s ,products p," +
            " ware_house wh where sa.idsales_ad = bs.idsales_ad AND" +
            " bs.idbatch_number = io.idbatch_number AND io.section_code = s.section_code AND" +
            " p.idproduct = sa.idproduct AND s.id_ware_house = wh.id_ware_house AND p.idproduct= ?1")
    List<InboundOrder> buscarSessaoInboundOrder(Long id);

    @Query(nativeQuery = true, value = "SELECT wh.* from sales_ad sa, batch_stock bs, inbound_order io, section s ,products p, ware_house wh where sa.idsales_ad = bs.idsales_ad AND bs.idbatch_number = io.idbatch_number AND io.section_code = s.section_code AND p.idproduct = sa.idproduct AND s.id_ware_house = wh.id_ware_house AND p.idproduct = ?1 GROUP BY wh.id_ware_house")
    List<WareHouse> buscarSessaoWareHouse(Long id);
}
