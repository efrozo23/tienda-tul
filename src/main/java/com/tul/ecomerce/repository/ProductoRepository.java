package com.tul.ecomerce.repository;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tul.ecomerce.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, String>, CrudRepository<Producto, String> {

	@Modifying
	@Query(" update Producto p set p.nombre = :nombre,p.descripcion = :descripcion, p.sku = :sku,p.precio = :precio,p.tipoProducto = :tipoProducto where p.id = :id ")
	int updateProducto(@PathParam("nombre") String nombre,
			@PathParam("descripcion") String descripcion, @PathParam("sku") String sku,
			@PathParam("precio") Double precio, @PathParam("tipoProducto") String tipoProducto, @PathParam("id") String id);

}
