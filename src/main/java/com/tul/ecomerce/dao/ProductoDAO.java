package com.tul.ecomerce.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tul.ecomerce.model.Producto;
import com.tul.ecomerce.repository.ProductoRepository;
import com.tul.ecomerce.util.TipoProducto;

@Repository
public class ProductoDAO implements IProductoDAO {

	private ProductoRepository productoRepository;

	public ProductoDAO(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Override
	public List<Producto> allProductos() {
		return productoRepository.findAll();
	}

	@Override
	public Producto guardarProducto(Producto item) {
		if (item.getTipoProducto().equals(TipoProducto.DESCUENTO.toString())) {
			item.setPrecio((item.getPrecio() / 2));
		}
		return this.productoRepository.save(item);
	}

	@Override
	public Producto editarProducto(Producto item) {
		return this.productoRepository.save(item);
	}

}
