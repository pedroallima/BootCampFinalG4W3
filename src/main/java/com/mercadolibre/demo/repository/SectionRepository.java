package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.dto.response.StockByWareHouseDTO;
import com.mercadolibre.demo.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {

	@Query(value = " select new com.mercadolibre.demo.dto.response.StockByWareHouseDTO(sec.category, bs.currentQuantity, s.price, w.wareHouseName, p.name )" +
			" from WareHouse w, Section sec, InboundOrder i, BatchStock bs, SalesAd s,  Product p " +
			" where s.product.id = p.id " +
			" and bs.idSalesAd.id = s.id " +
			" and i.batchStock.idBatchNumber = bs.idBatchNumber " +
			" and i.section.idSection = sec.idSection " +
			" and sec.WareHouse.idWareHouse = w.idWareHouse" +
			" and sec.category like %?1%")
	List<StockByWareHouseDTO> categoryContaining(String name);


}