package com.concesionario.controllers;

import com.concesionario.models.Detalle;
import com.concesionario.models.Producto;
import com.concesionario.repositories.DetalleRepository;
import com.concesionario.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalles")
public class DetalleController {

	@Autowired
	private DetalleRepository detalleRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@GetMapping
	public List<Detalle> getAllDetalles() {
		return detalleRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Detalle> getDetalleById(@PathVariable Integer id) {
		Optional<Detalle> d = detalleRepository.findById(id);
		return d.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> createDetalle(@RequestBody Detalle detalle) {
		if (detalle.getProducto() != null && detalle.getProducto().getId() != null) {
			Optional<Producto> prod = productoRepository.findById(detalle.getProducto().getId());
			if (prod.isEmpty()) {
				return ResponseEntity.badRequest().body("Producto no encontrado");
			}
			detalle.setProducto(prod.get());
		}
		Detalle created = detalleRepository.save(detalle);
		return ResponseEntity.ok(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateDetalle(@PathVariable Integer id, @RequestBody Detalle updated) {
		Optional<Detalle> existing = detalleRepository.findById(id);
		if (existing.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Detalle d = existing.get();
		d.setNombre(updated.getNombre());
		d.setPrecio(updated.getPrecio());
		if (updated.getProducto() != null && updated.getProducto().getId() != null) {
			Optional<Producto> prod = productoRepository.findById(updated.getProducto().getId());
			if (prod.isEmpty()) {
				return ResponseEntity.badRequest().body("Producto no encontrado");
			}
			d.setProducto(prod.get());
		} else {
			d.setProducto(null);
		}
		Detalle saved = detalleRepository.save(d);
		return ResponseEntity.ok(saved);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDetalle(@PathVariable Integer id) {
		Optional<Detalle> existing = detalleRepository.findById(id);
		if (existing.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		detalleRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
