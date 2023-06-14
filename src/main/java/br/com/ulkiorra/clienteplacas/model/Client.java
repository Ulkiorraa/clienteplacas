package br.com.ulkiorra.clienteplacas.model;

import java.time.LocalDate;

public class Client {
    Long id;
    String nome;
    Float preco_par;
    Float preco_und;
    String cpf;
    String cnpj;
    String telefone;
    String email;
    LocalDate dateCad;

    public Client() {
    }

    public Client(Long id, String nome, Float preco_par, Float preco_und, String cpf, String cnpj, String telefone, String email, LocalDate dateCad) {
        this.id = id;
        this.nome = nome;
        this.preco_par = preco_par;
        this.preco_und = preco_und;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
        this.dateCad = dateCad;
    }

    public Float getPreco_par() {
        return preco_par;
    }

    public void setPreco_par(Float preco_par) {
        this.preco_par = preco_par;
    }

    public Float getPreco_und() {
        return preco_und;
    }

    public void setPreco_und(Float preco_und) {
        this.preco_und = preco_und;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateCad() {
        return dateCad;
    }

    public void setDateCad(LocalDate dateCad) {
        this.dateCad = dateCad;
    }
}
