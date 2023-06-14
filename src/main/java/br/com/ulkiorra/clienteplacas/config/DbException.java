package br.com.ulkiorra.clienteplacas.config;

public class DbException extends RuntimeException {
    public DbException(String msg){
        super(msg);
    }
}
