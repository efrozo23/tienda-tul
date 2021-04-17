package com.tul.ecomerce.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tul.ecomerce.EcomerceApplication;
import com.tul.ecomerce.model.Carrito;
import com.tul.ecomerce.model.Producto;
import com.tul.ecomerce.repository.CarritoRepository;
import com.tul.ecomerce.repository.ProductoRepository;

@Repository
@Transactional
public class CarritoDAO implements ICarritoDAO{
	
	
	private CarritoRepository carritoRepository;
	private ProductoRepository productoRepository;
	
	public CarritoDAO(CarritoRepository carritoRepository,ProductoRepository productoRepository) {
		this.carritoRepository = carritoRepository;
		this.productoRepository = productoRepository;
		
	}

	@Override
	public List<Carrito> allCarrito() {
		
		return this.carritoRepository.findAll();
	}

	@Override
	public Carrito guardar(Carrito car) {
		Optional<Producto>  item = this.productoRepository.findById(car.getIdProducto().getId());
		if(item.isPresent()) {
			car.setIdProducto(item.get());
			return this.carritoRepository.save(car);
		}
		return null;
	}

	@Override
	public boolean existeCarrito(String uuid) {
		return this.carritoRepository.existsById(uuid);
		
	}

	@Override
	public Map<String, String> checkout() {
		
		Map<String, String> getTotal = this.carritoRepository.getValorTotal();
		int result = this.carritoRepository.updateCarrito();
		EcomerceApplication.logger.info("Respuesta:{}", getTotal.get("total"));
		EcomerceApplication.logger.info("Registros actualizados:{}", result);
		return getTotal;
	}

	@Override
	public boolean deleteProducto(String uuid) {
		Optional<Carrito>  item = this.carritoRepository.findById(uuid);
		if(item.isPresent()) {
			this.carritoRepository.deleteById(uuid);
			return true;
		}
		return false;
		
	}

}
