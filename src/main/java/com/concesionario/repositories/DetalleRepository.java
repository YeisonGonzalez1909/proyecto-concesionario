package com.concesionario.repositories; 


import org.springframework.data.jpa.repository.JpaRepository;
import com.concesionario.models.Detalle; 

public interface DetalleRepository extends JpaRepository<Detalle, Integer> {
    
}