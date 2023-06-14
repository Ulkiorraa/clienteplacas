package br.com.ulkiorra.clienteplacas.model;

public class User {
    Long id;
    String name;
    String user;
    String password;
    String email;
    String fone;

    public User() {
    }

    public User(Long id, String name, String user, String password, String email, String fone) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.password = password;
        this.email = email;
        this.fone = fone;
    }

    public String getFone() {
        return fone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
