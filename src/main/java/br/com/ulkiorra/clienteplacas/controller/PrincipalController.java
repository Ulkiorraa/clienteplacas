package br.com.ulkiorra.clienteplacas.controller;

import br.com.ulkiorra.clienteplacas.DAO.DaoFactory;
import br.com.ulkiorra.clienteplacas.DAO.IUserDAO;
import br.com.ulkiorra.clienteplacas.Principal;
import br.com.ulkiorra.clienteplacas.model.User;
import br.com.ulkiorra.clienteplacas.util.Alerts;
import br.com.ulkiorra.clienteplacas.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class PrincipalController implements Initializable {
    @FXML
    private ScrollPane principalView;

    private String name;
    private String email;
    private String fone;

    @FXML
    void onListUser() {
        IUserDAO iUserDAO = DaoFactory.createUserDAO();
        User user = iUserDAO.findByUser(name);
        createDialogormUpdate(user, Utils.getCurrentStage(principalView));
    }

    @FXML
    void onListPedidos() {
        loadView("view/listPlacasView.fxml", (ListPlacasController controller) -> {
            controller.setEntity(name);
            controller.setEmail(email);
            controller.setFone(fone);
            controller.setiPlacasDAO(DaoFactory.createPlacasDAO());
            controller.updateTableView();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private synchronized <T> void loadView(String absolutName, Consumer<T> initializeAction) {
        try {
            FXMLLoader loader = new FXMLLoader(Principal.class.getResource(absolutName));
            VBox newVbox = loader.load();
            Scene mainScene = LoginController.getMainScene();
            VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
            Node mainMenu = mainVbox.getChildren().get(0);
            mainVbox.getChildren().clear();
            mainVbox.getChildren().add(mainMenu);
            mainVbox.getChildren().addAll(newVbox.getChildren());
            T controller = loader.getController();
            initializeAction.accept(controller);
        } catch (IOException e) {
            Alerts.mostrarMensagemDeErro("Erro ao carregar tela!", "Erro ao carregar tela!", e.getMessage());
        }
    }

    private void createDialogormUpdate(User obj, Stage parentStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource("view/updateUserView.fxml"));
            Pane pane = fxmlLoader.load();
            UpdateUserController controller2 = fxmlLoader.getController();
            controller2.setEntity(obj);
            controller2.updateFormData();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Update de Cliente");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
        } catch (IOException e) {
            Alerts.mostrarMensagemDeErro("Erro", "Erro load view!", e.getMessage());
        }
    }
}
