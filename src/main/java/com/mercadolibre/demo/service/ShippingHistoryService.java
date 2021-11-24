package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ShippingSDTO;
import com.mercadolibre.demo.model.ShippingS;
import com.mercadolibre.demo.repository.ShippingHistoryRepository;
import com.mercadolibre.demo.repository.ShippingSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingHistoryService {

	private ShippingHistoryRepository shippingHistoryRepository;

	@Autowired
	public ShippingHistoryService(ShippingHistoryRepository shippingHistoryRepository) {
		this.shippingHistoryRepository = shippingHistoryRepository;
	}


/*	public ShippingS save(ShippingSDTO dto) {
		ShippingS shippingS = convertShippingStatusDTO(dto);
		return shippingHistoryRepository.save(shippingS);
	}


	public List<ShippingS> list() {
		return shippingHistoryRepository.findAll();
	}

	public Optional<ShippingS> findById(Long id) {
		return shippingHistoryRepository.findById(id);
	}

	public ShippingS update(ShippingSDTO dto, Long id) throws Exception {
		Optional<ShippingS> existSaller = findById(id);
		if (existSaller.isPresent()) {
			ShippingS shippingS = convertShippingStatusDTO(dto);
			shippingS.setIdsStatus(id);
			return shippingHistoryRepository.saveAndFlush(shippingS);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public void delete(Long id) throws Exception {
		Optional<ShippingS> shippingStatus = findById(id);
		if (shippingStatus.isPresent()) {
			shippingHistoryRepository.deleteById(id);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public ShippingS convertShippingStatusDTO(ShippingSDTO dto) {
		return new ShippingS(dto.getName(),dto.getOrder(),dto.getStatus());
	}*/

}

