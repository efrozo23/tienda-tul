package com.tul.ecomerce.dao;

import java.util.List;
import java.util.Map;

import com.tul.ecomerce.model.Carrito;

public interface ICarritoDAO {
	
	List<Carrito> allCarrito();
	
	Carrito guardar(Carrito car);
	
	boolean existeCarrito(String uuid);
	
	Map<String, String> checkout();
	
	boolean deleteProducto(String uuid);

}
