package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.AdressDTO;
import com.mercadolibre.demo.dto.SellerDTO;
import com.mercadolibre.demo.dto.ShippingSDTO;
import com.mercadolibre.demo.model.Seller;
import com.mercadolibre.demo.model.ShippingS;
import com.mercadolibre.demo.repository.SellerRepository;
import com.mercadolibre.demo.repository.ShippingSRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;


public class shippingStatusServiceTest {

    ShippingSRepository mokShippingSRepository = Mockito.mock(ShippingSRepository.class);
    ShippingSService shippingSService = new ShippingSService(mokShippingSRepository);

    ShippingS baseShippingS(){
        ShippingS shippingS = new ShippingS();
        shippingS.setIdsStatus(1L);
        shippingS.setName("produdo saiu para entrega");
        shippingS.setAtivo("True");
        shippingS.setOrdem(1L);
        return shippingS;
    }

    ShippingSDTO baseShippingSDTO(){
        ShippingSDTO shippingSDTO = new ShippingSDTO();
        shippingSDTO.setName("produdo saiu para entrega");
        shippingSDTO.setStatus("True");
        shippingSDTO.setOrder(1L);
        return shippingSDTO;
    }

/*    @Test
    void testSaveShippingStatusNoSuccess() {
        ShippingS shippingS = baseShippingS();
        ShippingSDTO shippingSDTO = baseShippingSDTO();
        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            shippingSService.salvar(shippingSDTO);
        });

        assertTrue(exceptionThatWasThrown.getMessage(), true);
        assertNotNull(shippingS);

    }*/

    @Test
    void testSaveShippingStatusSuccess() throws Exception {
        ShippingS shippingS = baseShippingS();
        ShippingSDTO shippingSDTO = baseShippingSDTO();

        Mockito.when(mokShippingSRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(shippingS));
        Mockito.when(mokShippingSRepository.save(Mockito.any(ShippingS.class))).thenReturn(shippingS);
        ShippingS orders = shippingSService.convertShippingStatusDTO(shippingSDTO);
        mokShippingSRepository.save(orders);
        assertNotNull(shippingS);

    }

/*    @Test
    void testListShippingStatusSuccess() {
        List<ShippingS> shippingSList = new ArrayList<>();
        ShippingS shippingS = baseShippingS();
        shippingSList.add(shippingS);
        List<ShippingS> list = shippingSService.list();
        assertNotNull(list.get(0).getName());

    }*/
/*    @Test
    void testFindByidFhippingStatusSuccess() {
        List<ShippingS> shippingSList = new ArrayList<>();
        ShippingS shippingS = baseShippingS();
        shippingSList.add(shippingS);
        Optional<ShippingS> byId = shippingSService.findById(1L);
        assertNull( byId.get().getAtivo());
    }*/

/*    @Test
    void testUpdateShippingStatusNoSuccess() {
        ShippingS shippingS = baseShippingS();
        shippingS.setIdsStatus(null);
        ShippingSDTO shippingSDTO = baseShippingSDTO();
        when(mokShippingSRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(shippingS));
        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            shippingSService.update(shippingSDTO, 1L);

        });

        assertTrue(exceptionThatWasThrown.getMessage(), true);
    }*/

    @Test
    void testUpdateShippingStatusSuccess() throws Exception {
        ShippingS shippingS = baseShippingS();
        ShippingSDTO shippingSDTO = baseShippingSDTO();

        Mockito.when(mokShippingSRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(shippingS));
        Mockito.when(mokShippingSRepository.saveAndFlush(Mockito.any(ShippingS.class))).thenReturn(shippingS);
        ShippingS orders = shippingSService.convertShippingStatusDTO(shippingSDTO);
        mokShippingSRepository.saveAndFlush(shippingS);
        shippingSService.update(shippingSDTO, 1L);
        assertNotNull(shippingS);

    }

    @Test
    void tesDeleteShippingStatusSuccess() throws Exception {
        ShippingS shippingS = baseShippingS();
        ShippingSDTO shippingSDTO = baseShippingSDTO();

        Mockito.when(mokShippingSRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.of(shippingS));

        shippingSService.delete(1L);
        Mockito.verify(mokShippingSRepository).deleteById(1L);

    }


}
