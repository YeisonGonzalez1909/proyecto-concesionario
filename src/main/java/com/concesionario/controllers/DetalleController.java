package com.concesionario.controllers;

import com.concesionario.models.Detalle;
import com.concesionario.services.DetalleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalles")
public class DetalleController {

	private final DetalleService detalleService;

	public DetalleController(DetalleService detalleService) {
		this.detalleService = detalleService;
	}

	@GetMapping
	public List<Detalle> getAllDetalles() {
		return detalleService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Detalle> getDetalleById(@PathVariable Integer id) {
		Optional<Detalle> d = detalleService.findById(id);
		return d.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Object> createDetalle(@RequestBody Detalle detalle) {
		try {
			Detalle created = detalleService.create(detalle);
			return ResponseEntity.ok(created);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateDetalle(@PathVariable Integer id, @RequestBody Detalle updated) {
		try {
			Detalle saved = detalleService.update(id, updated);
			return ResponseEntity.ok(saved);
		} catch (IllegalArgumentException ex) {
			if ("Detalle no encontrado".equals(ex.getMessage())) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDetalle(@PathVariable Integer id) {
		try {
			detalleService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.notFound().build();
		}
	}
}
