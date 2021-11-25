package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingHistoryDTO {
		//@NotBlank(message = "{name.not.blank}")
        private Long id_status;
       // @NotBlank(message = "{order.not.null}")
        private Long id_shipping;
}
