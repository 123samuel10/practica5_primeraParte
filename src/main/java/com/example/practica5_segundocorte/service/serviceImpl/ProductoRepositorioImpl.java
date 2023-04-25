package com.example.practica5_segundocorte.service.serviceImpl;

import com.example.practica5_segundocorte.model.Categoria;
import com.example.practica5_segundocorte.model.Producto;
import com.example.practica5_segundocorte.repository.Repositorio;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {

    private Connection conn;
    public ProductoRepositorioImpl(Connection conn) {
        this.conn = conn;
    }
    public ProductoRepositorioImpl() {
    }
    public void setConn(Connection conn) {
        this.conn = conn;
    }




    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.categoria as categoria FROM productos as p " + "inner join categorias as c ON (p.categoria_id = c.id)"))
        {
            while (rs.next()) {
                Producto p = crearProducto(rs);
                productos.add(p);
            }
        }
        return productos;

    }
    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p " + "inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")) {
stmt.setLong(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                producto = crearProducto(rs);
            }
        }
    }
return producto;

}

    @Override
    public Producto guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getId() != null && producto.getId()>0) {
            sql = "UPDATE productos SET nombre=?, precio=?, categoria_id=?, sku=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos(nombre, precio,fecha_registro,sku,categoria_id ) VALUES(?,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, producto.getNombre());
            stmt.setLong(2, producto.getPrecio());
            stmt.setDate(3, Date.valueOf(producto.getFechaRegistro()));
            stmt.setString(4, producto.getSku());
            stmt.setLong(5, producto.getCategory().getId());
            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(1, producto.getId());
            } else {
                stmt.setDate(3, Date.valueOf(producto.getFechaRegistro()));
            }
            stmt.executeUpdate();
            if (producto.getId() == null) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        producto.setId(rs.getLong(1));
                    }
                }
            }

            return producto;
        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM productos WHERE id=?")) {
stmt.setLong(1, id);
        stmt.executeUpdate();
    }

    }
    private Producto crearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        p.setSku(rs.getString("sku"));
        Categoria categoria = new Categoria();
        categoria.setId((int) rs.getLong("id"));
        categoria.setNombre(rs.getString("categoria"));
        p.setCategoria(categoria);
        return p;

    }



}