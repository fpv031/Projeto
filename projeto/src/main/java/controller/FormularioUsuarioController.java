package controller;

import Dao.PerguntaDAO;
import Dao.RespostaDAO;
import Models.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class FormularioUsuarioController {

    @FXML
    private VBox vboxPerguntas;

    private Usuario usuario;
    private Connection conn;
    private Map<Integer, Control> campoRespostas = new HashMap<>();
    private List<Perguntas> perguntas;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        carregarFormulario();
    }

    private void carregarFormulario() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/formulario_fiemg", "root", "sua_senha");
            PerguntaDAO dao = new PerguntaDAO(conn);
            perguntas = dao.listarPerguntas();

            for (Perguntas p : perguntas) {
                if (p.getPerguntaCondicionalId() != null) continue; // será tratada depois

                adicionarPergunta(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionarPergunta(Perguntas p) {
        Label lbl = new Label(p.getTexto());
        vboxPerguntas.getChildren().add(lbl);

        if ("fechada".equalsIgnoreCase(p.getTipo())) {
            ChoiceBox<String> cb = new ChoiceBox<>();
            cb.getItems().addAll("Sim", "Não");
            cb.setOnAction(e -> verificarCondicionais(p, cb.getValue()));
            campoRespostas.put(p.getId(), cb);
            vboxPerguntas.getChildren().add(cb);
        } else {
            TextField tf = new TextField();
            campoRespostas.put(p.getId(), tf);
            vboxPerguntas.getChildren().add(tf);
        }
    }

    private void verificarCondicionais(Perguntas perguntaBase, String resposta) {
        perguntas.stream()
            .filter(p -> perguntaBase.getId() == p.getPerguntaCondicionalId() &&
                         resposta.equalsIgnoreCase(p.getValorCondicional()))
            .forEach(this::adicionarPergunta);
    }

    @FXML
    void onEnviarFormulario() {
        RespostaDAO dao = new RespostaDAO(conn);
        for (Map.Entry<Integer, Control> entry : campoRespostas.entrySet()) {
            String valor = "";

            if (entry.getValue() instanceof TextField) {
                TextField tf = (TextField) entry.getValue();
                valor = tf.getText();
            } else if (entry.getValue() instanceof ChoiceBox) {
                ChoiceBox<String> cb = (ChoiceBox<String>) entry.getValue();
                valor = cb.getValue();
            }

            dao.salvarResposta(new Resposta(0, usuario.getId(), entry.getKey(), valor));
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Respostas salvas!");
        alert.showAndWait();
    }
}