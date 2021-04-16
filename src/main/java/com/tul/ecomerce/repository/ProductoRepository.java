package com.tul.ecomerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tul.ecomerce.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto	, String> {

}
