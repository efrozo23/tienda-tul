package com.tul.ecomerce.dao;

import java.util.List;
import java.util.Optional;

import com.tul.ecomerce.model.Producto;


public interface IProductoDAO {
	
	List<Producto> allProductos();
	
	Producto guardarProducto(Producto item);
	
	int editarProducto(Producto item);
	
	boolean deleteProducto(String uuid);
	
	
	Optional<Producto> buscarProductoById(String uuid);
	
	
}
