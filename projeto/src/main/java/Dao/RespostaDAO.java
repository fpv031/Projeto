package Dao;
import Models.Resposta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RespostaDAO {
    private Connection conn;

    public RespostaDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvarResposta(Resposta resposta) {
        String sql = "INSERT INTO resposta (usuario_id, pergunta_id, resposta) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, resposta.getUsuarioId());
            stmt.setInt(2, resposta.getPerguntaId());
            stmt.setString(3, resposta.getResposta());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Resposta> listarPorUsuario(int usuarioId) {
        List<Resposta> respostas = new ArrayList<>();
        String sql = "SELECT * FROM resposta WHERE usuario_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Resposta r = new Resposta(
                    rs.getInt("id"),
                    rs.getInt("usuario_id"),
                    rs.getInt("pergunta_id"),
                    rs.getString("resposta")
                );
                respostas.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return respostas;
    }
}