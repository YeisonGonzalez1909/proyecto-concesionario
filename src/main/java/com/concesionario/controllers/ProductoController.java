package com.concesionario.controllers;

import com.concesionario.models.Producto;
import com.concesionario.services.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	private final ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping
	public List<Producto> getAllProductos() {
		return productoService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
		Optional<Producto> p = productoService.findById(id);
		return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Object> createProducto(@RequestBody Producto producto) {
		try {
			Producto created = productoService.create(producto);
			return ResponseEntity.ok(created);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProducto(@PathVariable Integer id, @RequestBody Producto updated) {
		try {
			Producto saved = productoService.update(id, updated);
			return ResponseEntity.ok(saved);
		} catch (IllegalArgumentException ex) {
			if ("Producto no encontrado".equals(ex.getMessage())) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProducto(@PathVariable Integer id) {
		try {
			productoService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.notFound().build();
		}
	}
}
