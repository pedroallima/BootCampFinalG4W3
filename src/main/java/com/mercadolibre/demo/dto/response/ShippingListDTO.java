package com.mercadolibre.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShippingListDTO {

        private String code_traking;
        private String product;
        private Long id_product;
        private Long idPpurchase_order;

}
