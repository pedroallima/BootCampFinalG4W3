package com.mercadolibre.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "buyer")
public class Buyer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera autoincrimento no banco de dados
    @Column(name = "idBuyer")
    private Long idBuyer;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;

    public Buyer(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
