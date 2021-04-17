package com.tul.ecomerce.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tul.ecomerce.EcomerceApplication;
import com.tul.ecomerce.dao.ICarritoDAO;
import com.tul.ecomerce.dao.IProductoDAO;
import com.tul.ecomerce.dto.Response;
import com.tul.ecomerce.model.Carrito;
import com.tul.ecomerce.model.Producto;

@RestController
@RequestMapping("/carrito")
public class CarritoRestController {
	
	private ICarritoDAO iCarritoDAO;
	private IProductoDAO iProductoDAO;
	
	public CarritoRestController(ICarritoDAO iCarritoDAO,IProductoDAO iProductoDAO) {
		this.iCarritoDAO = iCarritoDAO;
		this.iProductoDAO = iProductoDAO;
	}
	
	@GetMapping("/listar")
	public ResponseEntity<Response> listarCarrito(){
		Response r = new Response();
		r.setMensaje("OK");
		r.setData(this.iCarritoDAO.allCarrito());
		return new ResponseEntity<>(r, HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Response> guardar(@Valid @RequestBody Carrito car){
		Response r = new Response();
		
		Optional<Carrito> carO = Optional.ofNullable(this.iCarritoDAO.guardar(car));
		
		if(carO.isPresent()) {
			r.setMensaje("OK");
			r.setData(Arrays.asList(this.iCarritoDAO.guardar(car)));
			return new ResponseEntity<>(r, HttpStatus.CREATED);
		}
		r.setMensaje("ERROR");
		r.setErrores(List.of("PRODUCTO NO ENCONTRADO"));
		return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
		
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Response> editar(@PathVariable("id") String uuid,@Valid @RequestBody Carrito car){
		Response r = new Response();
		
		
		boolean existeCarrito = this.iCarritoDAO.existeCarrito(uuid);
		
		if(!existeCarrito) {
			r.setMensaje("ERROR");
			r.setErrores(List.of("PRODUCTO NO ENCONTRADO"));
			return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
		}
		
		Optional<Producto> producto = this.iProductoDAO.buscarProductoById(car.getIdProducto().getId());
		
		if(!producto.isPresent()) {
			r.setMensaje("ERROR");
			r.setErrores(List.of("PRODUCTO NO ENCONTRADO"));
			return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
		}
		
		
		Optional<Carrito> carO = Optional.ofNullable(this.iCarritoDAO.guardar(car));
		
		r.setMensaje("OK");
		r.setData(Arrays.asList(carO.get()));
		return new ResponseEntity<>(r, HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Response> editar(@PathVariable("id") String uid){
		
		Response r = new Response();
		EcomerceApplication.logger.info("id ingresado: {}" , uid);
		r.setData(Arrays.asList());
		boolean isDelete = this.iCarritoDAO.deleteProducto(uid);
		
		if(isDelete) {
			
			r.setMensaje("ELIMINADO");
			r.setData(Arrays.asList(uid));
			r.setErrores(Arrays.asList());
					
			return new ResponseEntity<>(r, HttpStatus.OK);
		}
		r.setMensaje("ERROR");
		r.setErrores(Arrays.asList("NO SE ENCONTRO EL PRODUCTO"));
		return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/checkout")
	public ResponseEntity<Response> getCheckout(){
		Response r = new Response();
		r.setMensaje("OK");
		Map<String, String> mapTotal= this.iCarritoDAO.checkout();
		EcomerceApplication.logger.info("TOTAL OBTENIDO:{}" , mapTotal.size());
		r.setData(Arrays.asList(mapTotal));
		if(mapTotal.isEmpty()) {
			r.setData(Arrays.asList("NO HAY DATOS"));
			
		}
		return new ResponseEntity<>(r,HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Response handleValidationExceptions(MethodArgumentNotValidException ex){
		EcomerceApplication.logger.info("INGRESO A VALIDAR EXECPCION");
		Response r = new Response();
		r.setMensaje("ERROR");
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
		r.setErrores(Arrays.asList(errors));
		return r;
		
	}

}
