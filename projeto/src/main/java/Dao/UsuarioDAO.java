package Dao;

import Models.Usuario;

import java.sql.*;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }

    public Usuario autenticar(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getBoolean("primeira_vez"),
                    rs.getBoolean("is_admin")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void alterarSenha(int idUsuario, String novaSenha) {
        String sql = "UPDATE usuario SET senha = ?, primeira_vez = false WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novaSenha);
            stmt.setInt(2, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean emailExiste(String email) {
        String sql = "SELECT id FROM usuario WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void registrarNovoUsuario(String email) {
        String sql = "INSERT INTO usuario (email, senha, primeira_vez, is_admin) VALUES (?, '1234', true, false)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}