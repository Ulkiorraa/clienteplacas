package br.com.ulkiorra.clienteplacas.DAO;

import br.com.ulkiorra.clienteplacas.model.Client;

import java.util.List;

public interface IClientDAO {
    Client create(Client client);
    Client update(Client client);
    void delete(Client id);
    List<Client> FindAll();
    Client FindByName(String name);
    List<Client> FindByFone(String fone);
}
