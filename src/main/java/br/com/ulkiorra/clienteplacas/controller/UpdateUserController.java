package br.com.ulkiorra.clienteplacas.controller;

import br.com.ulkiorra.clienteplacas.DAO.DaoFactory;
import br.com.ulkiorra.clienteplacas.DAO.IUserDAO;
import br.com.ulkiorra.clienteplacas.model.User;
import br.com.ulkiorra.clienteplacas.util.Alerts;
import br.com.ulkiorra.clienteplacas.util.Formatter;
import br.com.ulkiorra.clienteplacas.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateUserController implements Initializable {

    private User entity;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_fone;

    @FXML
    private TextField txt_name;

    @FXML
    private PasswordField txt_pass;

    @FXML
    private TextField txt_user;

    public void setEntity(User entity) {
        this.entity = entity;
    }

    @FXML
    void onBtnUpd(ActionEvent event) {
        IUserDAO iUserDAO = DaoFactory.createUserDAO();
        String user = txt_user.getText().trim();
        String password = txt_pass.getText().trim();
        String name = txt_name.getText().trim();
        String email = txt_email.getText().trim();
        String fone = txt_fone.getText().trim();

        if (user.isEmpty()) {
            Alerts.mostrarMensagemDeErro("Erro", null, "Usuário é um campo obrigatório!");
            return;
        }
        if (fone.isEmpty()) {
            Alerts.mostrarMensagemDeErro("Erro", null, "Telefone é um campo obrigatório!");
            return;
        }
        if (name.isEmpty()) {
            Alerts.mostrarMensagemDeErro("Erro", null, "Nome é um campo obrigatório!");
            return;
        }
        if (email.isEmpty()) {
            Alerts.mostrarMensagemDeErro("Erro", null, "E-mail é um campo obrigatório!");
            return;
        }
        if (user.length() < 4) {
            Alerts.mostrarMensagemDeErro("Erro", null, "Usuário deve ter pelo menos 4 letras!");
            return;
        }
        if (password.isEmpty()) {
            Alerts.mostrarMensagemDeErro("Erro", null, "Senha é um campo obrigatório!");
            return;
        }
        if (password.length() < 6) {
            Alerts.mostrarMensagemDeErro("Erro", null, "Senha deve ter pelo menos 6 caracteres!");
            return;
        }

        User user1 = new User();
        user1.setId(entity.getId());
        user1.setFone(fone);
        user1.setName(name);
        user1.setEmail(email);
        user1.setUser(user);
        user1.setPassword(password);

        User cadastradoComSucesso = iUserDAO.update(user1);
        if (cadastradoComSucesso != null) {
            Alerts.mostrarMensagem("Information", null, "Update bem sucedido!");
        } else {
            Alerts.mostrarMensagemDeErro("Erro", null, "Update falhou!");
        }

        Utils.getCurrentStage(event).close();
    }

    @FXML
    void onBtnCancel(ActionEvent event) {
        Utils.getCurrentStage(event).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_user.setTextFormatter(Formatter.noNumberFormatter());
        txt_user.setTextFormatter(Formatter.noSpaceFormatter());
        txt_pass.setTextFormatter(Formatter.noSpaceFormatter());
    }

    public void updateFormData(){
        txt_user.setText(entity.getUser());
        txt_email.setText(entity.getEmail());
        txt_name.setText(entity.getName());
        txt_fone.setText(entity.getFone());
    }
}
