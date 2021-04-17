package com.tul.ecomerce.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tul.ecomerce.model.Producto;
import com.tul.ecomerce.repository.ProductoRepository;
import com.tul.ecomerce.util.TipoProducto;

@Repository
@Transactional
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
	public int editarProducto(Producto item) {
		
		return this.productoRepository.updateProducto(item.getNombre(), item.getDescripcion(),item.getSku(),item.getPrecio(),item.getTipoProducto(), item.getId());
		
	}

	@Override
	public boolean deleteProducto(String uuid) {
		Optional<Producto>  item = this.productoRepository.findById(uuid);
		if(item.isPresent()) {
			this.productoRepository.deleteById(uuid);	
			return true;
		}
		return false;
		
	}

	@Override
	public Optional<Producto> buscarProductoById(String uuid) {
		return this.productoRepository.findById(uuid);
	}

	

}
