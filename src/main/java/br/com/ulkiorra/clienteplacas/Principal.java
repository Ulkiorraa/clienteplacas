package br.com.ulkiorra.clienteplacas;

import br.com.ulkiorra.clienteplacas.config.ConnectionFactory;
import br.com.ulkiorra.clienteplacas.util.Alerts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

;

public class Principal extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource("view/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Login!");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            Alerts.mostrarMensagemDeErro("Erro!", null, "Erro ao carregar tela de Login!");
        }
    }

    public static void main(String[] args) {
        launch();
        ConnectionFactory.closeConnection();
    }
}