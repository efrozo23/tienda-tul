package com.tul.ecomerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.tul.ecomerce.util.EnumName;
import com.tul.ecomerce.util.TipoProducto;

@Entity
public class Producto {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@NotBlank
	private String nombre;
	
	@NotBlank
	private String sku;
	
	@NotBlank
	private String descripcion;
	
	@Min(value = 1)
	private Double precio;
	
	
	@Column(name = "tipo_producto")
	@EnumName(enumClass =  TipoProducto.class)
	private  String tipoProducto;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	

	

	
	
	
	

}
