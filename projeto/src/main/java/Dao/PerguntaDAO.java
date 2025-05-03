package Dao;

import Models.Perguntas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerguntaDAO {
    private Connection conn;

    public PerguntaDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvarPergunta(Perguntas pergunta) {
        String sql = "INSERT INTO pergunta (texto, tipo, pergunta_condicional_id, valor_condicional) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pergunta.getTexto());
            stmt.setString(2, pergunta.getTipo());
            if (pergunta.getPerguntaCondicionalId() != null) {
                stmt.setInt(3, pergunta.getPerguntaCondicionalId());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setString(4, pergunta.getValorCondicional());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Perguntas> listarPerguntas() {
        List<Perguntas> perguntas = new ArrayList<>();
        String sql = "SELECT * FROM pergunta";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Perguntas p = new Perguntas(
                    rs.getInt("id"),
                    rs.getString("texto"),
                    rs.getString("tipo"),
                    rs.getObject("pergunta_condicional_id") != null ? rs.getInt("pergunta_condicional_id") : null,
                    rs.getString("valor_condicional")
                );
                perguntas.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return perguntas;
    }
}