package com.mercadolibre.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TrakingListDTO {

    private Long id_status;
    private String nome;
    private LocalDate data;

}
