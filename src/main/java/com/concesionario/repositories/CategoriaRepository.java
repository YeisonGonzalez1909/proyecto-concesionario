package com.concesionario.repositories; 


import org.springframework.data.jpa.repository.JpaRepository;
import com.concesionario.models.Categoria; 

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}