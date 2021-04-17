package com.tul.ecomerce.dto;

import java.util.ArrayList;
import java.util.List;

public class Response {
	
	private String mensaje;
	private List<?> data = new ArrayList<>();
	private List<?> errores = new ArrayList<>();;
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public List<?> getErrores() {
		return errores;
	}
	public void setErrores(List<?> errores) {
		this.errores = errores;
	}
	
	

}
