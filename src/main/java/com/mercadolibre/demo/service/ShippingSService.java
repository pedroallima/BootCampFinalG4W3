package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ShippingSDTO;
import com.mercadolibre.demo.model.ShippingS;
import com.mercadolibre.demo.repository.ShippingSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingSService {

	private ShippingSRepository shippingSRepository;

	@Autowired
	public ShippingSService(ShippingSRepository shippingSRepository) {
		this.shippingSRepository = shippingSRepository;
	}


	public ShippingS salvar(ShippingSDTO dto) throws Exception {
		ShippingS shippingS = convertShippingStatusDTO(dto);
		if(shippingS.getIdsStatus() != null){
			return shippingSRepository.save(shippingS);
		}else{
			throw new Exception("Id não cadastrado");
		}
	}


	public List<ShippingS> list() {
		return shippingSRepository.findAll();
	}

	public Optional<ShippingS> findById(Long id) {
		return shippingSRepository.findById(id);
	}

	public ShippingS update(ShippingSDTO dto, Long id) throws Exception {
		Optional<ShippingS> existSaller = findById(id);
		if (existSaller.isPresent()) {
			ShippingS shippingS = convertShippingStatusDTO(dto);
			shippingS.setIdsStatus(id);
			return shippingSRepository.saveAndFlush(shippingS);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public void delete(Long id) throws Exception {
		Optional<ShippingS> shippingStatus = findById(id);
		if (shippingStatus.isPresent()) {
			shippingSRepository.deleteById(id);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public ShippingS convertShippingStatusDTO(ShippingSDTO dto) {
		ShippingS shippingS = new ShippingS();
		shippingS.setName(dto.getName());
		shippingS.setOrdem(dto.getOrder());
		shippingS.setAtivo(dto.getStatus());
		return  shippingS;
	}

}

