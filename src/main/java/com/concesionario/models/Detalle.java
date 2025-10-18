package com.concesionario.models; 

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "detalles")
public class Detalle{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    private String nombre;
    private double precio; 
     

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto; 




    
}
