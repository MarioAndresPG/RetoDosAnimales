package com.example.animales.entity;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Animal {

    private int imagen;
    private String nombre;
    private String descripcion;
    private int sonido;
}
