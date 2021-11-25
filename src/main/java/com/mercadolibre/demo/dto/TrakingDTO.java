package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrakingDTO {

    private String Code_traking;
    private List<TrakingListDTO> shippingListDTOList;

}
