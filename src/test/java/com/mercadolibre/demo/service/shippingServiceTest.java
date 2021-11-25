package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.TrakingListDTO;
import com.mercadolibre.demo.dto.response.ShippingDTO;
import com.mercadolibre.demo.dto.response.ShippingListDTO;
import com.mercadolibre.demo.dto.response.ShippingNativeDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import com.mercadolibre.demo.repository.ShippingHistoryRepository;
import com.mercadolibre.demo.repository.ShippingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mercadolibre.demo.model.OrderStatus.CARRINHO;
import static org.junit.Assert.assertNotNull;

public class shippingServiceTest {

    ShippingRepository mokShippingRepository = Mockito.mock(ShippingRepository.class);
    PurchaseOrderRepository mokPurchaseOrderRepository = Mockito.mock(PurchaseOrderRepository.class);
    ShippingHistoryRepository mokShippingHistoryRepository= Mockito.mock(ShippingHistoryRepository.class);
    ShippingService shippingService = new ShippingService(mokShippingRepository,mokPurchaseOrderRepository,mokShippingHistoryRepository);

    Shipping baseShipping(){
        Shipping shipping = new Shipping();
        shipping.setId_purchase_order(1L);
        shipping.setCode_track("ML3253220BR");
        shipping.setId_product(1L);
        shipping.setIdsShipping(1L);
        return shipping;
    }

    ShippingDTO baseShippingDTO(){
        ShippingDTO shippingDTO = new ShippingDTO();
        shippingDTO.setBuyer_name("Pedro Augusto");
        return shippingDTO;
    }

    List<ShippingListDTO> baseShippingListDTO(){
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

    List<TrakingListDTO> baseTrakingListDTO(){
        List<TrakingListDTO> trakingListDTOList = new ArrayList();
        TrakingListDTO trakingListDTO = new TrakingListDTO();
        trakingListDTO.setData(LocalDate.now());
        trakingListDTO.setId_status(1L);
        trakingListDTO.setNome("Pedido em stock");
        trakingListDTOList.add(trakingListDTO);
        TrakingListDTO trakingListDTO2 = new TrakingListDTO();
        trakingListDTO2.setData(LocalDate.now());
        trakingListDTO2.setId_status(1L);
        trakingListDTO2.setNome("Pedido em stock");
        trakingListDTOList.add(trakingListDTO2);
        return trakingListDTOList;
    }
        TrakingListDTO baseTrakingDTO(){
        TrakingListDTO trakingListDTO = new TrakingListDTO();
        trakingListDTO.setData(LocalDate.now());
        trakingListDTO.setId_status(1L);
        trakingListDTO.setNome("Pedido em stock");
        return trakingListDTO;
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

    ShippingS baseShippingS(){
        ShippingS shippingS = new ShippingS();
        shippingS.setIdsStatus(1L);
        shippingS.setName("EM viagem");
        shippingS.setOrdem(1L);
        shippingS.setIdsStatus(1L);
        return shippingS;
    }


    ShippingHistory baseShippingHistory(){
        ShippingHistory shippingHistory = new ShippingHistory();
        shippingHistory.setId_history_shipping(1L);
        shippingHistory.setDate(LocalDate.now());
        shippingHistory.setShippingS(baseShippingS());
        shippingHistory.setShipping(baseShipping());
        return shippingHistory;
    }



    @Test
    void testBuscarTrakingSuccess() throws Exception {
        Shipping shipping = baseShipping();
        List<TrakingListDTO>  trakingListDTOList = baseTrakingListDTO();
        TrakingListDTO trakingListDTO = baseTrakingDTO();
        Mockito.when(mokShippingRepository.listHistory(Mockito.any(String.class))).thenReturn(trakingListDTOList);
        shippingService.buscar(shipping.getCode_track());
        assertNotNull(trakingListDTO);

    }

    @Test
    void testSalvarTrakingSuccess() throws Exception {
        Long purchase_order = 1L;

        PurchaseOrder purchaseOrder = basePurchaseOrder();

        Mockito.when(mokPurchaseOrderRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(purchaseOrder));
        ShippingDTO salvar = shippingService.salvar(purchase_order, true);
        assertNotNull(salvar);

    }
    @Test
    void testSalvarTrakingFalse() throws Exception {
        Long purchase_order = 1L;
        PurchaseOrder purchaseOrder = basePurchaseOrder();


        Mockito.when(mokPurchaseOrderRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(purchaseOrder));
        shippingService.trakingProducts(purchaseOrder);
        ShippingDTO salvar = shippingService.salvar(purchase_order, false);
        assertNotNull(salvar);

    }

    @Test
    void testtrakingProductsFalse() throws Exception {
        Long purchase_order = 1L;
        PurchaseOrder purchaseOrder = basePurchaseOrder();
        ShippingHistory shippingHistory = baseShippingHistory();
        List<ShippingListDTO> shippingListDTOList = baseShippingListDTOS();
        List<ShippingNativeDTO> shippingNativeDTOS = baseShippingNativeDTOList();

        Mockito.when(mokPurchaseOrderRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(purchaseOrder));
        Mockito.when(mokShippingHistoryRepository.save(Mockito.any(ShippingHistory.class))).thenReturn(shippingHistory);
        ShippingDTO salvar = shippingService.salvar(purchase_order, false);
        assertNotNull(salvar);

    }

    @Test
    void testRramdomNumbersSucess() throws Exception {
        String validChar = "1234567890";
        String sequenceInit = "";
        String s = shippingService.ramdomNumbers();
        assertNotNull(s);

    }

}
