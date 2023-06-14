package br.com.ulkiorra.clienteplacas.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Utils {
    public static Stage getCurrentStage(ActionEvent event) {
        Node node = (Node) event.getSource();
        Scene scene = node.getScene();
        Window window = scene.getWindow();
        return (Stage) window;
    }

    public static Stage getCurrentStage(Node node) {
        Scene scene = node.getScene();
        Window window = scene.getWindow();
        return (Stage) window;
    }
}