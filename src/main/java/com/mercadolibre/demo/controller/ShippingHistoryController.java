package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.model.Shipping;
import com.mercadolibre.demo.model.ShippingHistory;
import com.mercadolibre.demo.service.ShippingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/shippingHistory")
public class ShippingHistoryController implements SecurityController {

	@Autowired
	private ShippingHistoryService shippingHistoryService;


	@GetMapping(value = "/tracking/{code}")
	public ResponseEntity<ShippingHistory> litShipping(@Valid @PathVariable String code) throws Exception {
		try {
			ShippingHistory shippingHistory = shippingHistoryService.add(code);
			return new ResponseEntity<>(shippingHistory, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value = "/save")
	public ResponseEntity<Shipping> saveShipping(@RequestBody Shipping dto) throws Exception {
		try {
			Shipping shipping = shippingHistoryService.save(dto);
			return new ResponseEntity<>(shipping, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}
