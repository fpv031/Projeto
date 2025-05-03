package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblErro;

    @FXML
    private void onLogin() {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            lblErro.setText("Por favor, preencha todos os campos.");
        } else if (email.equals("admin@fiemg.com") && senha.equals("1234")) {
            lblErro.setText("Login bem-sucedido!");
            lblErro.setStyle("-fx-text-fill: green;");
        } else {
            lblErro.setText("Credenciais inválidas.");
            lblErro.setStyle("-fx-text-fill: red;");
        }
    }
}