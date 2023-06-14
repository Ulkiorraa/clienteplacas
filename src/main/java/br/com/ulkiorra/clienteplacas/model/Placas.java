package br.com.ulkiorra.clienteplacas.model;

import java.time.LocalDate;

public class Placas {
    Long id;
    Long clientId;
    String placa;
    Placa_Tipo tipo;
    Float preco;
    String client_fone;
    String client_name;
    Status status;
    String observation;
    String vendedor;
    LocalDate dataestampagem;
    LocalDate datafinalizacao;


    public Placas() {
    }

    public Placas(Long id, Long clientId, String placa, Placa_Tipo tipo, Float preco, String client_fone, String client_name, Status status, String observation, String vendedor, LocalDate dataestampagem, LocalDate datafinalizacao) {
        this.id = id;
        this.clientId = clientId;
        this.placa = placa;
        this.tipo = tipo;
        this.preco = preco;
        this.client_fone = client_fone;
        this.client_name = client_name;
        this.status = status;
        this.observation = observation;
        this.vendedor = vendedor;
        this.dataestampagem = dataestampagem;
        this.datafinalizacao = datafinalizacao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Placa_Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Placa_Tipo tipo) {
        this.tipo = tipo;
    }

    public String getClient_fone() {
        return client_fone;
    }

    public void setClient_fone(String client_fone) {
        this.client_fone = client_fone;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public LocalDate getDataestampagem() {
        return dataestampagem;
    }

    public void setDataestampagem(LocalDate dataestampagem) {
        this.dataestampagem = dataestampagem;
    }

    public LocalDate getDatafinalizacao() {
        return datafinalizacao;
    }

    public void setDatafinalizacao(LocalDate datafinalizacao) {
        this.datafinalizacao = datafinalizacao;
    }
}
