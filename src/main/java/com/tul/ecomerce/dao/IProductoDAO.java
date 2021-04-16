package com.tul.ecomerce.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tul.ecomerce.model.Producto;

@Service
public interface IProductoDAO {
	
	List<Producto> allProductos();
	
	Producto guardarProducto(Producto item);
	
	Producto editarProducto(Producto item);

}
