package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.response.ShippingDTO;
import com.mercadolibre.demo.dto.response.ShippingListDTO;
import com.mercadolibre.demo.dto.response.ShippingNativeDTO;
import com.mercadolibre.demo.model.PurchaseOrder;
import com.mercadolibre.demo.model.Shipping;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import com.mercadolibre.demo.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ShippingService {

	private ShippingRepository shippingRepository;
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	public ShippingService(ShippingRepository shippingRepository, PurchaseOrderRepository purchaseOrderRepository) {
		this.shippingRepository = shippingRepository;
		this.purchaseOrderRepository = purchaseOrderRepository;
	}


    public Shipping buscar(String code) {
		return null;
    }

	public ShippingDTO save(Long purchase_order, Boolean general) {

		Shipping shipping = new Shipping();
		ShippingDTO shippingDTO = new ShippingDTO();
		Optional<PurchaseOrder> purchaseOrder = this.findById(purchase_order);
		shippingDTO.setBuyer_name(purchaseOrder.get().getIdBuyer().getName()+" "+purchaseOrder.get().getIdBuyer().getLastName());

		if(general == true){
			List<ShippingListDTO> shippingListDTOList = this.trakingPack(purchaseOrder.get());
			shippingDTO.setShippingListDTOList(shippingListDTOList);
			shipping.setId_product(0L);
			shipping.setCode_track(shippingListDTOList.get(0).getCode_traking());
			shipping.setId_purchase_order(shippingListDTOList.get(0).getIdPpurchase_order());
			shippingRepository.save(shipping);
			return shippingDTO;
		}else{
			List<ShippingListDTO> shippingListDTOList = this.trakingProducts(purchaseOrder.get());
			shippingDTO.setShippingListDTOList(shippingListDTOList);

			for (ShippingListDTO item: shippingListDTOList) {
				shipping.setId_product(item.getId_product());
				shipping.setCode_track(item.getCode_traking());
				shipping.setId_purchase_order(item.getIdPpurchase_order());
				shippingRepository.save(shipping);
			}
				return shippingDTO;
		}
	}

	public List<ShippingListDTO> trakingPack(PurchaseOrder purchase_order) {

		ShippingListDTO shippingListDTO = new ShippingListDTO();
		List<ShippingListDTO> shippingListDTOList = new ArrayList();

		String tracking =  this.ramdomNumbers();
		shippingListDTO.setCode_traking(tracking);
		shippingListDTO.setProduct("Rastreio para carrinho Completo");
		shippingListDTO.setId_product(0L);
		shippingListDTO.setIdPpurchase_order(purchase_order.getId());
		shippingListDTOList.add(shippingListDTO);

		return shippingListDTOList;

	}

	public List<ShippingListDTO> trakingProducts(PurchaseOrder purchase_order) {

		List<ShippingListDTO> shippingListDTOList = new ArrayList();
		List<ShippingNativeDTO> purchase = shippingRepository.findPurchase(purchase_order.getId());

		for (ShippingNativeDTO item: purchase) {
			ShippingListDTO shippingListDTO = new ShippingListDTO();
			String tracking =  this.ramdomNumbers();
			shippingListDTO.setCode_traking(tracking);
			shippingListDTO.setProduct(item.getProduct_name());
			shippingListDTO.setId_product(item.getId_product());
			shippingListDTO.setIdPpurchase_order(purchase_order.getId());
			shippingListDTOList.add(shippingListDTO);
		}

		return shippingListDTOList;

	}

	public String ramdomNumbers(){
		String validChar = "1234567890";
		String sequenceInit = "";
		char finalSequence;
		for (int k = 0;k<7;k++){
			finalSequence=validChar.charAt((int) Math.floor(Math.random()*validChar.length()));
			sequenceInit+=Character.toString(finalSequence);
		}
		return "ML"+sequenceInit+"BR";
	}

	private Optional<PurchaseOrder> findById(Long purchase_order) {
		Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(purchase_order);
		return purchaseOrder;
	}

	private void save(Shipping shipping) {
	}


}

