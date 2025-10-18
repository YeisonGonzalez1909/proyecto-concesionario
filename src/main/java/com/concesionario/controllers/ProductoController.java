package com.concesionario.controllers;

import com.concesionario.models.Producto;
import com.concesionario.models.Categoria;
import com.concesionario.repositories.ProductoRepository;
import com.concesionario.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public List<Producto> getAllProductos() {
		return productoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
		Optional<Producto> p = productoRepository.findById(id);
		return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> createProducto(@RequestBody Producto producto) {
		// If a category is provided, verify it exists and attach
		if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
			Optional<Categoria> cat = categoriaRepository.findById(producto.getCategoria().getId());
			if (cat.isEmpty()) {
				return ResponseEntity.badRequest().body("Categoria no encontrada");
			}
			producto.setCategoria(cat.get());
		}
		Producto created = productoRepository.save(producto);
		return ResponseEntity.ok(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProducto(@PathVariable Integer id, @RequestBody Producto updated) {
		Optional<Producto> existing = productoRepository.findById(id);
		if (existing.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Producto p = existing.get();
		// update fields
		p.setMarca(updated.getMarca());
		p.setModelo(updated.getModelo());
		p.setAnio(updated.getAnio());
		p.setPrecio(updated.getPrecio());
		p.setStock(updated.getStock());
		if (updated.getCategoria() != null && updated.getCategoria().getId() != null) {
			Optional<Categoria> cat = categoriaRepository.findById(updated.getCategoria().getId());
			if (cat.isEmpty()) {
				return ResponseEntity.badRequest().body("Categoria no encontrada");
			}
			p.setCategoria(cat.get());
		} else {
			p.setCategoria(null);
		}
		Producto saved = productoRepository.save(p);
		return ResponseEntity.ok(saved);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable Integer id) {
		Optional<Producto> existing = productoRepository.findById(id);
		if (existing.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		productoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
