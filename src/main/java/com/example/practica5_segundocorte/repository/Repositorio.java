package com.example.practica5_segundocorte.repository;

import com.example.practica5_segundocorte.model.Categoria;
import com.example.practica5_segundocorte.model.Producto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {

    T porId(Long id) throws SQLException;
    void setConn(Connection conn);

    List<T> listar() throws SQLException;

    T guardar(T producto) throws SQLException;

    void eliminar(Long id) throws SQLException;
//




}