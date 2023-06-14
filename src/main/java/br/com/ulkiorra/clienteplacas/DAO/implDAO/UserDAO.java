package br.com.ulkiorra.clienteplacas.DAO.implDAO;


import br.com.ulkiorra.clienteplacas.DAO.IUserDAO;
import br.com.ulkiorra.clienteplacas.config.ConnectionFactory;
import br.com.ulkiorra.clienteplacas.config.DbException;
import br.com.ulkiorra.clienteplacas.model.User;
import br.com.ulkiorra.clienteplacas.util.Encript;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private final Connection conn;

    public UserDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public User create(User user) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            String query = "INSERT INTO userclient(user, senha, name, email, telefone)VALUES (?, ?, ?, ?, ?)";
            st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getUser());
            st.setString(2, Encript.encryptSHA1(user.getPassword()));
            st.setString(3, user.getName());
            st.setString(4, user.getEmail());
            st.setString(5, user.getFone());
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            rs.next();
            Long idGerado = rs.getLong(1);
            user.setId(idGerado);
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeResultSet(rs);
        }
        return user;
    }

    @Override
    public User update(User user) {
        PreparedStatement st = null;
        try{
            String query = "UPDATE userclient SET " + "user = ?, senha = ?, name = ?, email = ?, telefone = ?" + "WHERE id = ?";
            st = conn.prepareStatement(query);
            st.setString(1, user.getUser());
            st.setString(2, Encript.encryptSHA1(user.getPassword()));
            st.setString(3, user.getName());
            st.setString(4, user.getEmail());
            st.setString(5, user.getFone());
            st.setLong(6, user.getId());
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            ConnectionFactory.closeStatement(st);
        }
        return user;
    }

    @Override
    public void delete(User id) {
        PreparedStatement st = null;
        try{
            String query = "DELETE FROM user WHERE id = ?";
            st = conn.prepareStatement(query);
            st.setLong(1, id.getId());
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            ConnectionFactory.closeStatement(st);
        }
    }

    @Override
    public List<User> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM user";
            List<User> list = new ArrayList<>();
            st = conn.prepareStatement(query);
            st.executeQuery();
            rs = st.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUser(rs.getString("user"));
                user.setPassword(Encript.encryptSHA1(rs.getString("senha")));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeResultSet(rs);
        }
    }

    @Override
    public User findByUser(String user1) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM userclient WHERE name = ?";
            st = conn.prepareStatement(query);
            st.setString(1, user1);
            st.executeQuery();
            User user = new User();
            rs = st.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("senha"));
                user.setName(rs.getString("name"));
                user.setFone(rs.getString("telefone"));
                user.setEmail(rs.getString("email"));
            }
            return user;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeResultSet(rs);
        }
    }
}
