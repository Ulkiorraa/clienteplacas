package br.com.ulkiorra.clienteplacas.DAO;


import br.com.ulkiorra.clienteplacas.DAO.implDAO.ClientDAO;
import br.com.ulkiorra.clienteplacas.DAO.implDAO.PlacasDAO;
import br.com.ulkiorra.clienteplacas.DAO.implDAO.UserDAO;
import br.com.ulkiorra.clienteplacas.config.ConnectionFactory;

public class DaoFactory {
    public static IUserDAO createUserDAO(){
        return new UserDAO(ConnectionFactory.getConnection());
    }
    public static IPlacasDAO createPlacasDAO(){
        return new PlacasDAO(ConnectionFactory.getConnection());
    }
    public static IClientDAO createClientDAO(){
        return new ClientDAO(ConnectionFactory.getConnection());
    }
}