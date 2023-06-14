package br.com.ulkiorra.clienteplacas.DAO;

import br.com.ulkiorra.clienteplacas.model.User;

import java.util.List;

public interface IUserDAO {
    User create(User user);
    User update(User user);
    void delete(User id);
    List<User>findAll();
    User findByUser(String user1);
}
