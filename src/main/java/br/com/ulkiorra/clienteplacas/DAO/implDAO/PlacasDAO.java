package br.com.ulkiorra.clienteplacas.DAO.implDAO;


import br.com.ulkiorra.clienteplacas.DAO.IPlacasDAO;
import br.com.ulkiorra.clienteplacas.config.ConnectionFactory;
import br.com.ulkiorra.clienteplacas.config.DbException;
import br.com.ulkiorra.clienteplacas.model.Placa_Tipo;
import br.com.ulkiorra.clienteplacas.model.Placas;
import br.com.ulkiorra.clienteplacas.model.Status;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlacasDAO implements IPlacasDAO {
    private final Connection conn;
    public PlacasDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public Placas create(Placas placas) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            String query = "INSERT INTO placas(placa, status, observation, dataestampagem, client_name, client_fone, cliente_id, datafinalizacao, vendedor, preco, tipo)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, placas.getPlaca());
            st.setString(2, placas.getStatus().toString());
            st.setString(3, placas.getObservation());
            st.setNull(4, Types.DATE);
            st.setString(5, placas.getClient_name());
            st.setString(6, placas.getClient_fone());
            if (placas.getClientId() != null) {
                st.setLong(7, placas.getClientId());
            } else {
                st.setNull(7, Types.BIGINT);
            }
            st.setNull(8, Types.DATE);
            st.setString(9, placas.getVendedor());
            st.setFloat(10, placas.getPreco());
            st.setString(11, placas.getTipo().toString());
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            rs.next();
            Long idGerado = rs.getLong(1);
            placas.setId(idGerado);
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeResultSet(rs);
        }
        return placas;
    }

    @Override
    public Placas update(Placas placas) {
        PreparedStatement st = null;
        try{
            String query = "UPDATE placas SET " + "placa = ?, status = ?, observation = ?, dataestampagem = ?, datafinalizacao = ?, cliente_id = ? , client_name = ?, client_fone = ?, vendedor = ?, preco = ?, tipo = ?" + "WHERE id = ?";
            st = conn.prepareStatement(query);
            st.setString(1, placas.getPlaca());
            st.setString(2, placas.getStatus().toString());
            st.setString(3, placas.getObservation());
            st.setDate(4, Date.valueOf(placas.getDataestampagem()));
            if (placas.getDatafinalizacao() != null) {
                st.setDate(5, Date.valueOf(placas.getDatafinalizacao()));
            } else {
                st.setNull(5, Types.DATE);
            }
            if (placas.getClientId() != null) {
                st.setLong(6, placas.getClientId());
            } else {
                st.setNull(6, Types.BIGINT);
            }
            st.setString(7, placas.getClient_name());
            st.setString(8, placas.getClient_fone());
            st.setString(9, placas.getVendedor());
            st.setFloat(10, placas.getPreco());
            st.setString(11, placas.getTipo().toString());
            st.setLong(12, placas.getId());
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            ConnectionFactory.closeStatement(st);
        }
        return placas;
    }

    @Override
    public void delete(Placas id) {
        PreparedStatement st = null;
        try{
            String query = "DELETE FROM placas WHERE id = ?";
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
    public List<Placas> FindAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM placas";
            List<Placas> list = new ArrayList<>();
            st = conn.prepareStatement(query);
            st.executeQuery();
            rs = st.executeQuery();
            while (rs.next()) {
                Placas placas = new Placas();
               placas.setId(rs.getLong("id"));
                placas.setPlaca(rs.getString("placa"));
                placas.setStatus(Status.valueOf(rs.getString("status")));
                String observation = rs.getString("observation");
                placas.setObservation(!rs.wasNull() ? observation : null);
                placas.setObservation(rs.getString("observation"));
                placas.setDataestampagem(rs.getDate("dataestampagem").toLocalDate());
                LocalDate dateFinalizacao = rs.getDate("datafinalizacao") != null ? rs.getDate("datafinalizacao").toLocalDate() : null;
                placas.setDatafinalizacao(dateFinalizacao);
                long clientId = rs.getLong("cliente_id");
                placas.setClientId(!rs.wasNull() ? clientId : null);
                placas.setClient_name(rs.getString("client_name"));
                placas.setClient_fone(rs.getString("client_fone"));
                String vendedor = rs.getString("vendedor");
                placas.setVendedor(!rs.wasNull() ? vendedor : null);
                placas.setPreco(rs.getFloat("preco"));
                placas.setTipo(Placa_Tipo.valueOf(rs.getString("tipo")));
                list.add(placas);
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
    public List<Placas> FindByPlaca(String placa) {
        return null;
    }

    @Override
    public List<Placas> FindByName(String name) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Placas> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM placas WHERE client_name = ?";
            st = conn.prepareStatement(query);
            st.setString(1, name);
            st.executeQuery();
            rs = st.executeQuery();
            while (rs.next()) {
                Placas placas = new Placas();
                placas.setId(rs.getLong("id"));
                placas.setPlaca(rs.getString("placa"));
                placas.setStatus(Status.valueOf(rs.getString("status")));
                String observation = rs.getString("observation");
                placas.setObservation(!rs.wasNull() ? observation : null);
                placas.setObservation(rs.getString("observation"));
                placas.setDataestampagem(null);
                placas.setDatafinalizacao(null);
                long clientId = rs.getLong("cliente_id");
                placas.setClientId(!rs.wasNull() ? clientId : null);
                placas.setClient_name(rs.getString("client_name"));
                placas.setClient_fone(rs.getString("client_fone"));
                String vendedor = rs.getString("vendedor");
                placas.setVendedor(!rs.wasNull() ? vendedor : null);
                placas.setPreco(rs.getFloat("preco"));
                placas.setTipo(Placa_Tipo.valueOf(rs.getString("tipo")));
                list.add(placas);
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
    public List<Placas> FindByStatus(Status status) {
        return null;
    }
}
