package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ShippingHistoryDTO;
import com.mercadolibre.demo.dto.TrakingListDTO;
import com.mercadolibre.demo.dto.response.ShippingDTO;
import com.mercadolibre.demo.dto.response.ShippingListDTO;
import com.mercadolibre.demo.dto.response.ShippingNativeDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import com.mercadolibre.demo.repository.ShippingHistoryRepository;
import com.mercadolibre.demo.repository.ShippingRepository;
import com.mercadolibre.demo.repository.ShippingSRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mercadolibre.demo.model.OrderStatus.CARRINHO;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class shippingHistoryTest {

    ShippingRepository mokShippingRepository = Mockito.mock(ShippingRepository.class);
    ShippingSRepository mokShippingSRepositoryRepository = Mockito.mock(ShippingSRepository.class);
    ShippingHistoryRepository mokShippingHistoryRepository= Mockito.mock(ShippingHistoryRepository.class);
    ShippingHistoryService shippingHistoryService = new ShippingHistoryService(mokShippingHistoryRepository,mokShippingSRepositoryRepository,mokShippingRepository);

    List<ShippingListDTO> baseShippingListDTOS(){
        List<ShippingListDTO> shippingListDTOS = new ArrayList();
        ShippingListDTO shippingListDTO = new ShippingListDTO();
        shippingListDTO.setCode_traking("ML3253220BR");
        shippingListDTO.setId_product(1L);
        shippingListDTO.setIdPpurchase_order(1L);
        shippingListDTOS.add(shippingListDTO);
        ShippingListDTO shippingListDTO2 = new ShippingListDTO();
        shippingListDTO2.setCode_traking("ML3253220BR");
        shippingListDTO2.setId_product(2L);
        shippingListDTO2.setIdPpurchase_order(1L);
        shippingListDTOS.add(shippingListDTO2);
        return shippingListDTOS;
    }

    PurchaseOrder basePurchaseOrder(){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(1L);
        purchaseOrder.setIdBuyer(baseBuyer());
        purchaseOrder.setItemOfProduct(baseItemOfProductList());
        purchaseOrder.setOrderStatus(CARRINHO);
        purchaseOrder.setDate(LocalDate.now());
        return purchaseOrder;
    }

    Buyer baseBuyer() {
        Buyer buyer = new Buyer();
        buyer.setIdBuyer(1L);
        buyer.setName("Sakura");
        buyer.setLastName("Haruno");
        return buyer;
    }
    SalesAd baseSalesAD() {
        SalesAd salesAd = new SalesAd();
        salesAd.setId(1L);
        salesAd.setProduct(baseProduct());
        salesAd.setPrice(22.00);
        return salesAd;
    }
    Product baseProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setDescription("Pizza congelada");
        product.setName("Pizza Calabresa");
        return product;
    }

    ItemOfProduct baseItemOfProduct() {
        ItemOfProduct itemOfProduct = new ItemOfProduct();
        itemOfProduct.setId(1L);
        itemOfProduct.setQuantity(1L);
        itemOfProduct.setSalesAd(baseSalesAD());
        return itemOfProduct;
    }

    List<ItemOfProduct> baseItemOfProductList() {
        List<ItemOfProduct> itemOfProductList = new ArrayList();
        ItemOfProduct registro1 = baseItemOfProduct();
        itemOfProductList.add(registro1);
        ItemOfProduct registro2= baseItemOfProduct();
        registro2.setId(2L);
        itemOfProductList.add(registro2);
        return itemOfProductList;
    }

    List<PurchaseOrder> basePurchaseOrderList() {
        List<PurchaseOrder> purchaseOrderList = new ArrayList();
        PurchaseOrder registro1 = basePurchaseOrder();
        purchaseOrderList.add(registro1);
        PurchaseOrder registro2 = basePurchaseOrder();
        registro2.setId(2L);
        purchaseOrderList.add(registro2);
        return purchaseOrderList;
    }

    ShippingNativeDTO baseShippingNativeDTO() {
        ShippingNativeDTO shippingNativeDTO = new ShippingNativeDTO();
        shippingNativeDTO.setId_product(1L);
        shippingNativeDTO.setProduct_name("Pizza congelada");
        shippingNativeDTO.setId_product(1L);
        return shippingNativeDTO;
    }


    List<ShippingNativeDTO> baseShippingNativeDTOList() {
        List<ShippingNativeDTO> shippingListDTOList = new ArrayList();
        ShippingNativeDTO registro = baseShippingNativeDTO();
        shippingListDTOList.add(registro);
        ShippingNativeDTO registro2 = baseShippingNativeDTO();
        registro2.setId_product(2L);
        shippingListDTOList.add(registro2);
        return shippingListDTOList;
    }

    ShippingHistory baseShippingHistory() {
        ShippingHistory shippingHistory = new ShippingHistory();
        shippingHistory.setId_history_shipping(1L);
        shippingHistory.setShippingS(baseShippingS());
        shippingHistory.setShipping(baseShipping());
        return shippingHistory;
    }

    ShippingHistoryDTO baseShippingHistoryDTO(){
        ShippingHistoryDTO shippingHistoryDTO = new ShippingHistoryDTO();
        shippingHistoryDTO.setId_shipping(1L);
        shippingHistoryDTO.setId_status(1L);
        return shippingHistoryDTO;
    }

    ShippingS baseShippingS(){
        ShippingS shippingS = new ShippingS();
        shippingS.setIdsStatus(1L);
        shippingS.setName("EM viagem");
        shippingS.setOrdem(1L);
        shippingS.setIdsStatus(1L);
        return shippingS;
    }

    Shipping baseShipping(){
        Shipping shipping = new Shipping();
        shipping.setIdsShipping(1L);
        shipping.setCode_track("ML3253220BR");
        shipping.setId_product(1L);
        shipping.setIdsShipping(1L);
        shipping.setIdsShipping(1L);

        return shipping;
    }


/*    @Test
    void testSaveTrakingSuccess() throws Exception {
        ShippingHistoryDTO shippingHistoryDTO = baseShippingHistoryDTO();
        ShippingHistory shippingHistory = baseShippingHistory();

        shippingHistoryService.convertShippingHistoryToDTO(shippingHistoryDTO);
        Mockito.when(shippingHistoryService.convertShippingHistoryToDTO(Mockito.any(ShippingHistoryDTO.class))).thenReturn(shippingHistory);

        ShippingHistory salvar = shippingHistoryService.salvar(shippingHistoryDTO);

        assertEquals(1, salvar.getId_history_shipping());

    }*/

}
