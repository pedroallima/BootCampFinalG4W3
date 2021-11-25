package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.InboundOrderDTO;
import com.mercadolibre.demo.dto.ShippingHistoryDTO;
import com.mercadolibre.demo.dto.ShippingSDTO;
import com.mercadolibre.demo.dto.response.ShippingDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.ShippingHistoryRepository;
import com.mercadolibre.demo.repository.ShippingRepository;
import com.mercadolibre.demo.repository.ShippingSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingHistoryService {

	private ShippingHistoryRepository shippingHistoryRepository;
	private ShippingSRepository shippingSRepository;
	private ShippingRepository shippingRepository;

	@Autowired
	public ShippingHistoryService(ShippingHistoryRepository shippingHistoryRepository, ShippingSRepository shippingSRepository, ShippingRepository shippingRepository) {
		this.shippingHistoryRepository = shippingHistoryRepository;
		this.shippingSRepository = shippingSRepository;
		this.shippingRepository = shippingRepository;
	}

	public ShippingHistory save(ShippingHistoryDTO dto) throws Exception {
		ShippingHistory shippingHistory = convertShippingHistoryToDTO(dto);
		return shippingHistoryRepository.save(shippingHistory);
	}
	public Optional<ShippingS> obtemHStatus(ShippingHistoryDTO dto) throws Exception {
		Optional<ShippingS> shippingS = shippingSRepository.findById(dto.getId_status());
		if (shippingS.isPresent()) {
			return shippingS;
		} else {
			throw new Exception("Shipping Status não encontrado");
		}
	}

	public Optional<Shipping> obtemShipping(ShippingHistoryDTO dto) throws Exception {
		Optional<Shipping> shipping = shippingRepository.findById(dto.getId_shipping());
		if (shipping.isPresent()) {
			return shipping;
		} else {
			throw new Exception("Shipping não encontrada");
		}
	}

	public ShippingHistory convertShippingHistoryToDTO(ShippingHistoryDTO dto) throws Exception {
		return new ShippingHistory(obtemHStatus(dto), obtemShipping(dto));
	}


}

