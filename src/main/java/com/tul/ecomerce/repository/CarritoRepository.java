package com.tul.ecomerce.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tul.ecomerce.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, String>{
	
	@Query(value = "select sum(val) as total from (\r\n" + 
			"select p.id,p.precio,count(p.id),(p.precio*sum(c.cantidad)) val ,sum(c.cantidad)  from producto p \r\n" + 
			"inner join carrito c on c.id_producto=p.id\r\n" + 
			"where c.estado = 'PENDIENTE'\r\n" + 
			"group by  p.id,c.id_producto\r\n" + 
			"\r\n" + 
			")  t",nativeQuery = true)
	Map<String,String> getValorTotal();
	
	@Modifying
	@Query(value = "update carrito set estado = 'COMPLETADO' ", nativeQuery = true)
	public int updateCarrito();

}
