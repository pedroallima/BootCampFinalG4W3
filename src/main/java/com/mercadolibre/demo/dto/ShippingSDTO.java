package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ShippingSDTO {
		//@NotBlank(message = "{name.not.blank}")
        private String name;

       // @NotBlank(message = "{order.not.null}")
        private Long order;

        //@NotBlank(message = "{status.not.blank}")
        private String status;
}
