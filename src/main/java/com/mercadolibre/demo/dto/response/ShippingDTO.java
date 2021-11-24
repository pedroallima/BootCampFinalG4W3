package com.mercadolibre.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShippingDTO {

        private String buyer_name;
        private List<ShippingListDTO> shippingListDTOList;

}
