package generics;

import jdbc.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO<T> {

    protected abstract String getTableName();

    protected abstract String getSequence();

    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

    public Integer cadastrar(T entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO " + getTableName() + " (id, codigo, nome) values (nextval('" + getSequence() + "'),?,?)";
            stm = connection.prepareStatement(sql);
            mapStatementForCreate(stm, entity);
            return stm.executeUpdate();
        } catch (Exception e){
            throw e;
        } finally {
            closeResources(stm, connection);
        }
    }

    public T consultar(String codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        T entity = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "select * from " + getTableName() + " where codigo = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, codigo);
            rs = stm.executeQuery();
            if (rs.next()){
                entity = mapResultSetToEntity(rs);
            }
            return entity;
        } catch (Exception e){
            throw e;
        } finally {
            closeResources(stm, connection, rs);
        }
    }

    public Integer excluir(T entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM " + getTableName() + " WHERE codigo = ?";
            stm = connection.prepareStatement(sql);
            mapStatementForDelete(stm, entity);
            return stm.executeUpdate();
        } catch (Exception e){
            throw e;
        } finally {
            closeResources(stm, connection);
        }
    }

    public List<T> consultarTodos() throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<T> entities = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "select * from " + getTableName();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()){
                entities.add(mapResultSetToEntity(rs));
            }
        } catch (Exception e){
            throw e;
        } finally {
            closeResources(stm, connection, rs);
        }
        return entities;
    }

    public Integer atualizar(T entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "update " + getTableName() + " set codigo = ?, nome = ? where id = ?";
            stm = connection.prepareStatement(sql);
            mapStatementForUpdate(stm, entity);
            return stm.executeUpdate();
        } catch (Exception e){
            throw e;
        } finally {
            closeResources(stm, connection);
        }
    }

    private void closeResources(PreparedStatement stm, Connection connection) throws SQLException {
        closeResources(stm, connection, null);
    }

    private void closeResources(PreparedStatement stm, Connection connection, ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()){
            rs.close();
        }
        if (stm != null && !stm.isClosed()){
            stm.close();
        }
        if (connection != null && !connection.isClosed()){
            connection.close();
        }
    }

    // Métodos para mapear parâmetros
    protected abstract void mapStatementForCreate(PreparedStatement stm, T entity) throws SQLException;

    protected abstract void mapStatementForUpdate(PreparedStatement stm, T entity) throws SQLException;

    protected abstract void mapStatementForDelete(PreparedStatement stm, T entity) throws SQLException;
}
