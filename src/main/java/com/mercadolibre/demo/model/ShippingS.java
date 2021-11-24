package com.mercadolibre.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shipping_status")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShippingS implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_shipping_status")
	private Long idsStatus;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "ordem", nullable = false)
	private long ordem;

	@Column(name = "ativo", nullable = false)
	private String ativo;

	public ShippingS(String name, long ordem, String ativo) {
		this.name = name;
		this.ordem = ordem;
		this.ativo = ativo;
	}
}
