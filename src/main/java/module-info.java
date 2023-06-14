module br.com.ulkiorra.clienteplacas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens br.com.ulkiorra.clienteplacas to javafx.fxml;
    exports br.com.ulkiorra.clienteplacas.controller;
    exports br.com.ulkiorra.clienteplacas.listeners;
    exports br.com.ulkiorra.clienteplacas;
    exports br.com.ulkiorra.clienteplacas.DAO;
    exports br.com.ulkiorra.clienteplacas.model;
    exports br.com.ulkiorra.clienteplacas.config;
    opens br.com.ulkiorra.clienteplacas.controller to javafx.fxml;
}