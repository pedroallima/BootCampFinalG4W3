package com.mercadolibre.demo.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShippingNativeDTO {
    Long id_product;
    Double price;
    String product_name;
}