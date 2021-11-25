package com.mercadolibre.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shipping_history")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShippingHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_history_shipping")
	private Long id_history_shipping;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_shipping_status", nullable = false)
	private ShippingS shippingS;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_shipping", nullable = false)
	private Shipping shipping;

	private LocalDate date = LocalDate.now();

	public ShippingHistory(Optional<ShippingS> shippingS, Optional<Shipping> shipping) {
		this.shippingS = shippingS.get();
		this.shipping = shipping.get();
	}
}
