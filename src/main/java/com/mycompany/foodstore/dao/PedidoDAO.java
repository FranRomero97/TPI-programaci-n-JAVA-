package com.mycompany.foodstore.dao;

import com.mycompany.foodstore.config.ConexionDB;
import com.mycompany.foodstore.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // ---------------- GUARDAR PEDIDO ----------------
    public void guardar(Pedido p) throws Exception {

        String sql = "INSERT INTO pedidos (fecha, estado, forma_pago, total, eliminado, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, Date.valueOf(p.getFecha()));
            ps.setString(2, p.getEstado().name());
            ps.setString(3, p.getFormaPago() != null ? p.getFormaPago().name() : null);

            p.calcularTotal(); // 🔥 IMPORTANTE
            ps.setDouble(4, p.getTotal());

            ps.setBoolean(5, p.isEliminado());
            ps.setTimestamp(6, Timestamp.valueOf(p.getCreatedAt()));

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getLong(1));
                }
            }
        }
    }

    // ---------------- LISTAR ----------------
    public List<Pedido> listar() throws Exception {

        List<Pedido> lista = new ArrayList<>();

        String sql = "SELECT * FROM pedidos WHERE eliminado = false";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Pedido p = new Pedido();

                p.setId(rs.getLong("id"));
                p.setFecha(rs.getDate("fecha").toLocalDate());
                p.setEstado(Estado.valueOf(rs.getString("estado")));
                p.setFormaPago(
                        rs.getString("forma_pago") != null
                                ? FormaPago.valueOf(rs.getString("forma_pago"))
                                : null
                );
                p.setTotal(rs.getDouble("total"));
                p.setEliminado(rs.getBoolean("eliminado"));
                p.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                lista.add(p);
            }
        }

        return lista;
    }

    // ---------------- ELIMINAR LÓGICO ----------------
    public void eliminarLogico(Long id) throws Exception {

        String sql = "UPDATE pedidos SET eliminado = true WHERE id = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
}