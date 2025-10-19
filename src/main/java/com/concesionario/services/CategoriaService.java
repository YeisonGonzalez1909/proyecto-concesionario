package com.concesionario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concesionario.models.Categoria;
import com.concesionario.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Optional<Categoria> findById(Integer id) {
		return categoriaRepository.findById(id);
	}

	@Transactional
	public Categoria create(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
}