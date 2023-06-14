package br.com.ulkiorra.clienteplacas.controller;


import br.com.ulkiorra.clienteplacas.DAO.DaoFactory;
import br.com.ulkiorra.clienteplacas.DAO.IPlacasDAO;
import br.com.ulkiorra.clienteplacas.listeners.DataChangedListner;
import br.com.ulkiorra.clienteplacas.model.Placa_Tipo;
import br.com.ulkiorra.clienteplacas.model.Placas;
import br.com.ulkiorra.clienteplacas.model.Status;
import br.com.ulkiorra.clienteplacas.util.Alerts;
import br.com.ulkiorra.clienteplacas.util.Formatter;
import br.com.ulkiorra.clienteplacas.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroPlacasController implements Initializable {

    private final List<DataChangedListner> dataChangeListeners = new ArrayList<>();

    private String entity;
    private String email;
    private String fone;

    @FXML
    private TextField txt_preco;

    @FXML
    private TextField txt_email;

    @FXML
    private ComboBox<Placa_Tipo> txt_tipo;

    @FXML
    private TextField txt_vendedor;

    @FXML
    private TextField txt_nome;

    @FXML
    private TextArea txt_observacao;

    @FXML
    private TextField txt_placa;

    @FXML
    private TextField txt_telefone;

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
    void onClickCad(ActionEvent event) {
        IPlacasDAO iPlacasDAO = DaoFactory.createPlacasDAO();
        String placa = txt_placa.getText().trim();
        Status status = Status.SOLICITADA;
        String observation = txt_observacao.getText().trim();
        String name = txt_nome.getText().trim();
        String fone = txt_telefone.getText().trim();
        String vendedor = txt_vendedor.getText().trim();
        String prc = txt_preco.getText().trim();
        Float preco = Float.parseFloat(prc);
        Placa_Tipo tipo = txt_tipo.getValue();

        if (placa.isEmpty()) {
            Alerts.mostrarMensagemDeErro("Erro", null, "Placa é um campo obrigatório!");
            return;
        }
        if (name.isEmpty()) {
            Alerts.mostrarMensagemDeErro("Erro", null, "Nome é um campo obrigatório!");
            return;
        }
        if (fone.isEmpty()) {
            Alerts.mostrarMensagemDeErro("Erro", null, "Telefone é um campo obrigatório!");
            return;
        }
        if (prc.isEmpty()) {
            Alerts.mostrarMensagemDeErro("Erro", null, "Preço é um campo obrigatório!");
            return;
        }
        if (tipo == null) {
            Alerts.mostrarMensagemDeErro("Erro", null, "Tipo é um campo obrigatório!");
            return;
        }
        Placas placa1 = new Placas();
        placa1.setPlaca(placa);
        placa1.setClientId(null);
        placa1.setStatus(status);
        placa1.setObservation(observation);
        placa1.setDataestampagem(null);
        placa1.setDatafinalizacao(null);
        placa1.setClient_name(name);
        placa1.setClient_fone(fone);
        placa1.setVendedor(vendedor);
        placa1.setPreco(preco);
        placa1.setTipo(tipo);

        Placas cadastroComSucesso = iPlacasDAO.create(placa1);
        if (cadastroComSucesso != null) {
            Alerts.mostrarMensagem("Information", null, "Cadastro bem sucedido!");
            notifyDataChangeListeners();
        } else {
            Alerts.mostrarMensagemDeErro("Erro", null, "Cadastro falhou!");
        }

        Utils.getCurrentStage(event).close();
    }

    @FXML
    void onClickCancel(ActionEvent event) {
        Utils.getCurrentStage(event).close();
    }

    public void subscribeDataChangeListener(DataChangedListner listner) {
        dataChangeListeners.add(listner);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txt_tipo.getItems().add(Placa_Tipo.PAR);
        txt_tipo.getItems().add(Placa_Tipo.UNIDADE);

        txt_placa.textProperty().addListener((observable, oldValue, newValue) -> txt_placa.setText(newValue.toUpperCase()));
        txt_vendedor.textProperty().addListener((observable, oldValue, newValue) -> txt_vendedor.setText(newValue.toUpperCase()));

        txt_placa.setTextFormatter(Formatter.PlacasFormatter());
        txt_nome.setTextFormatter(Formatter.noNumberFormatter());
        txt_telefone.setTextFormatter(Formatter.noLettersFormatter());
        txt_vendedor.setTextFormatter(Formatter.noNumberFormatter());
        txt_preco.setTextFormatter(Formatter.noLettersFormatter());

        txt_tipo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Placa_Tipo.PAR) {
                txt_preco.setText("240.00");
                txt_nome.setText(entity);
                txt_email.setText(email);
                txt_telefone.setText(fone);
            }
            if (newValue == Placa_Tipo.UNIDADE) {
                txt_preco.setText("150.00");
                txt_nome.setText(entity);
                txt_email.setText(email);
                txt_telefone.setText(fone);
            }
        });
    }

    private void notifyDataChangeListeners() {
        for (DataChangedListner listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

}