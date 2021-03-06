package com.tul.ecomerce.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
import com.tul.ecomerce.dao.IProductoDAO;
import com.tul.ecomerce.dto.Response;
import com.tul.ecomerce.model.Producto;

@RestController
@RequestMapping("/producto")
public class ProductoRestController {
	
	
	private IProductoDAO iProductoDAO;
	
	public ProductoRestController(IProductoDAO iProductoDAO) {
		
		this.iProductoDAO = iProductoDAO;
		
	}
	
	@GetMapping("/listar")
	public ResponseEntity<Response> listarProductos(){
		Response r = new Response();
		r.setMensaje("OK");
		r.setErrores(Arrays.asList());
		r.setData(this.iProductoDAO.allProductos());
		return new ResponseEntity<>(r, HttpStatus.OK);
		
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Response> guardar(@Valid @RequestBody Producto item){
		Response r = new Response();
		Producto rP = this.iProductoDAO.guardarProducto(item);
		r.setMensaje("OK");
		r.setErrores(Arrays.asList());
		r.setData(Arrays.asList(rP));
		return new ResponseEntity<>(r, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Response> editar(@Valid @RequestBody Producto item,@PathVariable("id") String uid){
		item.setId(uid);
		Response r = new Response();
		EcomerceApplication.logger.info("id ingresado: {}" , uid);
		EcomerceApplication.logger.info("id ingresado dto: {}" , item.getId());
		int result = this.iProductoDAO.editarProducto(item);
		
		EcomerceApplication.logger.info("Modificado: {}" , result);
		r.setData(Arrays.asList());
		r.setMensaje("OK");
		r.setData(Arrays.asList(item));
		r.setErrores(Arrays.asList());
		if(result == 0) {
			r.setMensaje("ERROR");
			r.setData(Arrays.asList());
			r.setErrores(Arrays.asList("NO SE ENCONTRO EL PRODUCTO"));
			return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(r, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Response> editar(@PathVariable("id") String uid){
		
		Response r = new Response();
		EcomerceApplication.logger.info("id ingresado: {}" , uid);
		r.setData(Arrays.asList());
		boolean isDelete = this.iProductoDAO.deleteProducto(uid);
		
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
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Response handleValidationExceptions(MethodArgumentNotValidException ex){
		EcomerceApplication.logger.info("INGRESO A VALIDAR EXECPCION");
		Response r = new Response();
		r.setMensaje("ERROR");
		r.setData(Arrays.asList());
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
