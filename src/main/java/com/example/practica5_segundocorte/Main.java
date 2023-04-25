package com.example.practica5_segundocorte;

import com.example.practica5_segundocorte.model.Categoria;
import com.example.practica5_segundocorte.model.Producto;
import com.example.practica5_segundocorte.service.Servicio;
import com.example.practica5_segundocorte.service.serviceImpl.CatalogoServicio;
import com.example.practica5_segundocorte.service.serviceImpl.ProductoRepositorioImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {




        Servicio servicio = new CatalogoServicio();
        System.out.println("============= listar =============");
        servicio.listar().forEach(System.out::println);
        Categoria categoria = new Categoria();
        categoria.setNombre("celular");

        //servicio.guardarCategoria(categoria);

        Producto producto = new Producto();

        producto.setNombre("phone 14");
        producto.setPrecio(2200000);
        producto.setCategory(categoria);
        producto.setFechaRegistro(LocalDate.now());
        producto.setSku("phone----");


        servicio.guardarProductoConCategoria(producto,categoria);
        servicio.guardarProductoConCategoria(producto, categoria);
        System.out.println("Producto guardado con éxito: " + producto.getId());
        servicio.listar().forEach(System.out::println);
        //System.out.println("Producto guardado con éxito: " + producto.getId());
        //servicio.listar().forEach(System.out::println);

        //System.out.println("Producto guardado con éxito: " + producto.getId());
      // servicio.listar().forEach(System.out::println);



    }

}
