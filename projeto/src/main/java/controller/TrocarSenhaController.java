package controller;

import Dao.UsuarioDAO;
import Models.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class TrocarSenhaController {

    @FXML
    private PasswordField novaSenhaField;

    @FXML
    private PasswordField confirmarSenhaField;

    @FXML
    private Button btnSalvar;

    @FXML
    private Label lblMensagem;

    private Usuario usuario;
    private Connection conn;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        conectarBanco();
    }

    private void conectarBanco() {
        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/formulario_fiemg", "root", "sua_senha");
        } catch (Exception e) {
            lblMensagem.setText("Erro de conexão com o banco.");
        }
    }

    @FXML
    void onSalvar(ActionEvent event) {
        String novaSenha = novaSenhaField.getText();
        String confirmar = confirmarSenhaField.getText();

        if (!novaSenha.equals(confirmar)) {
            lblMensagem.setText("As senhas não coincidem.");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
        usuarioDAO.alterarSenha(usuario.getId(), novaSenha);

        lblMensagem.setText("Senha atualizada com sucesso!");

        // Opcional: redirecionar
        ((Stage) btnSalvar.getScene().getWindow()).close();
    }
}