package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.AdressSaveDTO;
import com.mercadolibre.demo.dto.response.AdressBuscaDTO;
import com.mercadolibre.demo.dto.response.AdressRestDTO;
import com.mercadolibre.demo.repository.ShippingSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShippingService {

	private ShippingSRepository shippingSRepository;

	@Autowired
	public ShippingService(ShippingSRepository shippingSRepository) {
		this.shippingSRepository = shippingSRepository;
	}


    public AdressBuscaDTO buscar(String code) {
		return null;
    }

	public AdressRestDTO save(AdressSaveDTO dto) {
		return null;
	}
}

