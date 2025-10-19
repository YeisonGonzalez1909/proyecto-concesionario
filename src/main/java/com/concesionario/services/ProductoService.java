package com.concesionario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concesionario.models.Producto;
import com.concesionario.models.Categoria;
import com.concesionario.repositories.ProductoRepository;
import com.concesionario.repositories.CategoriaRepository;

@Service
public class ProductoService {

	private final ProductoRepository productoRepository;
	private final CategoriaRepository categoriaRepository;

	public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
		this.productoRepository = productoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	public Optional<Producto> findById(Integer id) {
		return productoRepository.findById(id);
	}

	@Transactional
	public Producto create(Producto producto) {
		if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
			Optional<Categoria> cat = categoriaRepository.findById(producto.getCategoria().getId());
			if (cat.isEmpty()) {
				throw new IllegalArgumentException("Categoria no encontrada");
			}
			producto.setCategoria(cat.get());
		}
		return productoRepository.save(producto);
	}

	@Transactional
	public Producto update(Integer id, Producto updated) {
		Producto p = productoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
		p.setMarca(updated.getMarca());
		p.setModelo(updated.getModelo());
		p.setAnio(updated.getAnio());
		p.setPrecio(updated.getPrecio());
		p.setStock(updated.getStock());
		if (updated.getCategoria() != null && updated.getCategoria().getId() != null) {
			Optional<Categoria> cat = categoriaRepository.findById(updated.getCategoria().getId());
			if (cat.isEmpty()) {
				throw new IllegalArgumentException("Categoria no encontrada");
			}
			p.setCategoria(cat.get());
		} else {
			p.setCategoria(null);
		}
		return productoRepository.save(p);
	}

	@Transactional
	public void delete(Integer id) {
		if (!productoRepository.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado");
		}
		productoRepository.deleteById(id);
	}
}