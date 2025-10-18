package com.concesionario.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String marca;
    private String modelo;
    private Integer anio;
    private double precio;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonBackReference
    private Categoria categoria;

    public Producto() {}

    public Producto(Integer id, String marca, String modelo, Integer anio, double precio, int stock) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public Integer getAnio() {
        return anio;
    }
    public void setAnio(Integer anio) {
        this.anio = anio;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


}
