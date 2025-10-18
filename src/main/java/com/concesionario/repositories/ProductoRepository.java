package com.concesionario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.concesionario.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}

