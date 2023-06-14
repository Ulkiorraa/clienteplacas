package br.com.ulkiorra.clienteplacas.controller;


import br.com.ulkiorra.clienteplacas.DAO.IPlacasDAO;
import br.com.ulkiorra.clienteplacas.Principal;
import br.com.ulkiorra.clienteplacas.listeners.DataChangedListner;
import br.com.ulkiorra.clienteplacas.model.Placas;
import br.com.ulkiorra.clienteplacas.model.Status;
import br.com.ulkiorra.clienteplacas.util.Alerts;
import br.com.ulkiorra.clienteplacas.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ListPlacasController implements Initializable, DataChangedListner {

    private String entity;
    private String email;
    private String fone;

    private IPlacasDAO iPlacasDAO;

    @FXML
    private AnchorPane ancorpaine;

    @FXML
    private Label labeldtf;

    @FXML
    private Label labeldtfn;

    @FXML
    private Label labelfone;

    @FXML
    private Label labelnome;

    @FXML
    private Label labelobs;

    @FXML
    private Label labelprc;

    @FXML
    private Label labelstatus;

    @FXML
    private Label labelvend;

    @FXML
    private TableColumn<Placas, String> table_cliente;

    @FXML
    private TableColumn<Placas, LocalDate> table_dtestamp;

    @FXML
    private TableColumn<Placas, LocalDate> table_dtfin;

    @FXML
    private TableColumn<Placas, String> table_fone;

    @FXML
    private TableColumn<Placas, String> table_observacao;

    @FXML
    private TableColumn<Placas, String> table_placa;

    @FXML
    private TableColumn<Placas, Status> table_status;

    @FXML
    private TableColumn<Placas, String> table_vendedor;

    @FXML
    private TableColumn<Placas, Float> table_preco;

    @FXML
    private TableView<Placas> table_placas;

    @FXML
    private Label txtlabelplaca;

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    @FXML
    void onActionNew(ActionEvent event) {
        Stage parentStage = Utils.getCurrentStage(event);
        createDialogorm(parentStage);
    }

    public void setiPlacasDAO(IPlacasDAO iPlacasDAO) {
        this.iPlacasDAO = iPlacasDAO;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
        table_placas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtlabelplaca.setText(newValue.getPlaca());
                labelnome.setText(newValue.getClient_name());
                labelfone.setText(newValue.getClient_fone());
                labeldtf.setText(newValue.getDataestampagem().toString());
                if(newValue.getDatafinalizacao() != null){
                    labeldtfn.setText(newValue.getDatafinalizacao().toString());
                }else {
                    labeldtfn.setText("");
                }
                labelobs.setText(newValue.getObservation());
                labelprc.setText(newValue.getPreco().toString());
                labelstatus.setText(newValue.getStatus().toString());
                labelvend.setText(newValue.getVendedor());
            }
        });
    }
    private void initializeNodes(){
        table_placa.setCellValueFactory(new PropertyValueFactory<>("placa"));
        table_cliente.setCellValueFactory(new PropertyValueFactory<>("client_name"));
        table_fone.setCellValueFactory(new PropertyValueFactory<>("client_fone"));
        table_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        table_vendedor.setCellValueFactory(new PropertyValueFactory<>("vendedor"));
        table_observacao.setCellValueFactory(new PropertyValueFactory<>("observation"));
        table_dtestamp.setCellValueFactory(new PropertyValueFactory<>("dataestampagem"));
        table_dtfin.setCellValueFactory(new PropertyValueFactory<>("datafinalizacao"));
        table_preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        Stage stage = (Stage) LoginController.getMainScene().getWindow();
        table_placas.prefHeightProperty().bind(stage.heightProperty());
        table_placas.prefWidthProperty().bind(stage.widthProperty());
        ancorpaine.prefHeightProperty().bind(stage.heightProperty());
        ancorpaine.prefWidthProperty().bind(stage.widthProperty());
    }

    public void  updateTableView(){
        List<Placas> list = iPlacasDAO.FindByName(entity);
        ObservableList<Placas> obsList = FXCollections.observableList(list);
        table_placas.setItems(obsList);
    }

    private void createDialogorm(Stage parentStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource("view/cadastroPlacasView.fxml"));
            Pane pane = fxmlLoader.load();
            CadastroPlacasController controller = fxmlLoader.getController();
            controller.subscribeDataChangeListener(this);
            controller.setEntity(entity);
            controller.setEmail(email);
            controller.setFone(fone);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Pedir de Placas");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
        } catch (IOException e) {
            Alerts.mostrarMensagemDeErro("Erro", "Erro load view!", e.getMessage());
        }
    }

    @Override
    public void onDataChanged() {
        updateTableView();
    }
}
