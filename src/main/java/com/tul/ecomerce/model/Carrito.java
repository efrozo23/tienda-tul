package com.tul.ecomerce.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.tul.ecomerce.util.EnumName;
import com.tul.ecomerce.util.TipoProducto;

@Entity
public class Carrito {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producto",referencedColumnName = "id")
	private Producto idProducto;
	
	@Min(value = 1)
	private Integer cantidad;
	
	@NotBlank
	@EnumName(enumClass =  TipoProducto.class)
	private  String estado;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public Producto getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Producto idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	

}
