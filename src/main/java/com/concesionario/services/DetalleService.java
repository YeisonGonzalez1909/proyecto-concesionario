package com.concesionario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concesionario.models.Detalle;
import com.concesionario.models.Producto;
import com.concesionario.repositories.DetalleRepository;
import com.concesionario.repositories.ProductoRepository;

@Service
public class DetalleService {

	private final DetalleRepository detalleRepository;
	private final ProductoRepository productoRepository;

	public DetalleService(DetalleRepository detalleRepository, ProductoRepository productoRepository) {
		this.detalleRepository = detalleRepository;
		this.productoRepository = productoRepository;
	}

	public List<Detalle> findAll() {
		return detalleRepository.findAll();
	}

	public Optional<Detalle> findById(Integer id) {
		return detalleRepository.findById(id);
	}

	@Transactional
	public Detalle create(Detalle detalle) {
		if (detalle.getProducto() != null && detalle.getProducto().getId() != null) {
			Optional<Producto> prod = productoRepository.findById(detalle.getProducto().getId());
			if (prod.isEmpty()) {
				throw new IllegalArgumentException("Producto no encontrado");
			}
			detalle.setProducto(prod.get());
		}
		return detalleRepository.save(detalle);
	}

	@Transactional
	public Detalle update(Integer id, Detalle updated) {
		Detalle d = detalleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Detalle no encontrado"));
		d.setNombre(updated.getNombre());
		d.setPrecio(updated.getPrecio());
		if (updated.getProducto() != null && updated.getProducto().getId() != null) {
			Optional<Producto> prod = productoRepository.findById(updated.getProducto().getId());
			if (prod.isEmpty()) {
				throw new IllegalArgumentException("Producto no encontrado");
			}
			d.setProducto(prod.get());
		} else {
			d.setProducto(null);
		}
		return detalleRepository.save(d);
	}

	@Transactional
	public void delete(Integer id) {
		if (!detalleRepository.existsById(id)) {
			throw new IllegalArgumentException("Detalle no encontrado");
		}
		detalleRepository.deleteById(id);
	}
}