package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.InboundOrderDTO;
import com.mercadolibre.demo.dto.ShippingHistoryDTO;
import com.mercadolibre.demo.dto.response.ShippingDTO;
import com.mercadolibre.demo.model.InboundOrder;
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


/*	@PostMapping(value = "/save/{id_status}/{id_shipping}")
	public ResponseEntity<ShippingHistory> litShipping(@Valid @PathVariable Long id_status, @PathVariable Long id_status) throws Exception {
		try {
			ShippingHistory shippingHistory = shippingHistoryService.save1(id_status,id_status);
			return new ResponseEntity<>(shippingHistory, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}*/

	@PostMapping(value ="/save")
	public ResponseEntity<ShippingHistory> saveInboundOrder(@Valid @RequestBody ShippingHistoryDTO dto) throws Exception {
		ShippingHistory shippingHistory = shippingHistoryService.save(dto);
		return new ResponseEntity<>(shippingHistory, HttpStatus.CREATED);
	}
}
