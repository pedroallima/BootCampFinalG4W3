package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.*;
import com.mercadolibre.demo.dto.response.WareHouseProductItensDTO;
import com.mercadolibre.demo.dto.response.WareHouseProductListDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SectionService {


	private SectionRepository sectionRepository;
	private WareHouseRepository wareHouseRepository;
	private InboundOrderRepository inboundOrderRepository;

	@Autowired
	public SectionService(SectionRepository sectionRepository, WareHouseRepository wareHouseRepository,
			InboundOrderRepository inboundOrderRepository, BatchStockRepository batchStockRepository,
			SalesAdRepository salesAdRepository) {
		this.sectionRepository = sectionRepository;
		this.wareHouseRepository = wareHouseRepository;
		this.inboundOrderRepository = inboundOrderRepository;
	}

	public Section save(SectionDTO dto) throws Exception {
		Section section = convertSectionToDTO(dto);
		return sectionRepository.save(section);
	}

	public List<Section> list() {
		return sectionRepository.findAll();
	}

	public List<SectionTypeDTO>findSectionCategories(String category){
		List<SectionRepository.SectionByProducts>  sectionByProducts = sectionRepository.CategoryContaining(category);
		List<SectionTypeDTO> sectionTypeDTOS = new ArrayList<>();
		for (SectionRepository.SectionByProducts section: sectionByProducts) {
			SectionTypeDTO dto = SectionTypeDTO.builder()
					.name(section.getCategory())
					.quantity(section.getCurrent_quantity())
					.price(section.getPrice())
					.wareHouse(section.getWare_house_name())
					.nameProduct(section.getName())
					.build();
			sectionTypeDTOS.add(dto);
		}
		return sectionTypeDTOS;
	}

	public WareHouseProductItensDTO listProduct(Long idProduct) {
		List<WareHouse> idWarehouse = wareHouseRepository.findAll();

		WareHouseProductItensDTO requisiteFour = new WareHouseProductItensDTO();
		List<WareHouseProductListDTO> wareHouseProductListDTO = new ArrayList<>();
		requisiteFour.setIdProduct(idProduct);

		for (WareHouse itemWareHOuse : idWarehouse) {
			WareHouseProductListDTO wareHouseProductDTO = new WareHouseProductListDTO();
			List<InboundOrderRepository.StockByWareHouse> stockByWareHouses = inboundOrderRepository.buscaCanseira(idProduct,itemWareHOuse.getIdWareHouse());
			wareHouseProductDTO.setQuantity(0L);
			for (InboundOrderRepository.StockByWareHouse item : stockByWareHouses) {
				wareHouseProductDTO.setWareHouseName(item.getWare_house_name());
				wareHouseProductDTO.setQuantity(wareHouseProductDTO.getQuantity() + item.getCurrent_quantity());
			}
			if(stockByWareHouses.size()>0) {
				wareHouseProductListDTO.add(wareHouseProductDTO);
			}
		}
		requisiteFour.setList(wareHouseProductListDTO);
		return requisiteFour;

	}

	public Optional<WareHouse> getWareHouse(SectionDTO dto){
		Optional<WareHouse> wareHouse = wareHouseRepository.findById(dto.getIdWareHouse());
		return wareHouse;
	}
	
	public Optional<Section> findById(Long id) {
		return sectionRepository.findById(id);
	}

	public Section update(SectionDTO dto, Long id) throws Exception {

		Optional<Section> existSection = findById(id);
		if (existSection.isPresent()) {
			Section section = convertSectionToDTO(dto);
			section.setIdSection(id);
			return sectionRepository.saveAndFlush(section);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public void delete(Long id) {
		sectionRepository.deleteById(id);
	}

	public Section convertSectionToDTO(SectionDTO dto) throws Exception {
		if (getWareHouse(dto).isPresent()) {
			return new Section(dto.getCapacity(), dto.getCategory(), getWareHouse(dto).get());
		} else {
			throw new Exception("Id não cadastrado");
		}
	}


/*	public List<SectionRepository.SectionByProducts> sectionByProducts (String name) {
		List<SectionRepository.SectionByProducts>  sectionByProducts = sectionRepository.CategoryContaining(name);
		return sectionByProducts;
	}*/


/*	public SectionTypeDTO setSectionTypeDTO(String category,Long quantity, Double price, String wareHouseName, String productName){
		SectionTypeDTO sectionTypeDTO = new SectionTypeDTO();
		sectionTypeDTO.setName(category);
		sectionTypeDTO.setQuantity(quantity);
		sectionTypeDTO.setPrice(price);
		sectionTypeDTO.setWareHouse(wareHouseName);
		sectionTypeDTO.setNameProduct(productName);

		return sectionTypeDTO;
	}*/
}