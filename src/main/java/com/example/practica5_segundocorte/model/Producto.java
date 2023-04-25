package com.example.practica5_segundocorte.model;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Producto {
    private  Long id;
    private String nombre;
    private int precio;
    private LocalDate fechaRegistro;

    private Categoria category;
    private String sku;


    public void setCategoria(Categoria categoria) {
    }
}
