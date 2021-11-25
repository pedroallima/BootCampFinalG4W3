package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.TrakingDTO;
import com.mercadolibre.demo.dto.response.ShippingDTO;
import com.mercadolibre.demo.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/shippingcode")
public class ShippingController implements SecurityController {

	@Autowired
	private ShippingService shippingService;


	@GetMapping(value = "/list/{code}")
	public ResponseEntity<TrakingDTO> litShipping(@Valid @PathVariable String code) throws Exception {
			TrakingDTO trakingDTO = shippingService.buscar(code);
			return new ResponseEntity<>(trakingDTO, HttpStatus.OK);
	}


	@GetMapping(value = "/save/{purchase_order}/{general}")
	@ResponseBody
	public ResponseEntity<ShippingDTO> saveShipping(@PathVariable Long purchase_order, @PathVariable boolean general) {
			ShippingDTO shippingDTO = shippingService.salvar(purchase_order,general);
			return new ResponseEntity<>(shippingDTO, HttpStatus.OK);

	}
}
