package controller;

import Dao.PerguntaDAO;
import Models.Perguntas;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdminController {

    @FXML
    private TextField txtPergunta;

    @FXML
    private ChoiceBox<String> cbTipo;

    @FXML
    private TextField txtValorCondicional;

    @FXML
    private TextField txtIdCondicional;

    @FXML
    private Button btnSalvar;

    @FXML
    private Label lblMensagem;

    private Connection conn;

    @FXML
    public void initialize() {
        cbTipo.getItems().addAll("aberta", "fechada", "condicional");
        conectar();
    }

    private void conectar() {
        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/formulario_fiemg", "root", "sua_senha");
        } catch (Exception e) {
            lblMensagem.setText("Erro ao conectar ao banco.");
        }
    }

    @FXML
    void onSalvarPergunta() {
        String texto = txtPergunta.getText();
        String tipo = cbTipo.getValue();

        Integer idCondicional = null;
        if (!txtIdCondicional.getText().isEmpty()) {
            idCondicional = Integer.parseInt(txtIdCondicional.getText());
        }

        String valorCond = txtValorCondicional.getText().isEmpty() ? null : txtValorCondicional.getText();

        Perguntas pergunta = new Perguntas(0, texto, tipo, idCondicional, valorCond);

        PerguntaDAO dao = new PerguntaDAO(conn);
        dao.salvarPergunta(pergunta);

        lblMensagem.setText("Pergunta salva com sucesso!");
        limparCampos();
    }

    private void limparCampos() {
        txtPergunta.clear();
        cbTipo.setValue(null);
        txtValorCondicional.clear();
        txtIdCondicional.clear();
    }
}