package dao;

import domain.Cliente;
import jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{
    @Override
    public Integer cadastrar(Cliente cliente) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO tb_cliente (id, codigo, nome) values (nextval('sq_cliente'),?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, cliente.getCodigo());
            stm.setString(2, cliente.getNome());
            return stm.executeUpdate();
        } catch (Exception e){
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public Cliente consultar(String codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "select * from tb_cliente where codigo = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, codigo);
            rs = stm.executeQuery();
            if (rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setCodigo(rs.getString("codigo"));
                cliente.setNome(rs.getString("nome"));
            }
            return cliente;
        } catch (Exception e){
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM tb_cliente WHERE CODIGO = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, cliente.getCodigo());
            return stm.executeUpdate();
        } catch (Exception e){
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public List<Cliente> consultarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        List<Cliente> clientes = new ArrayList<>();
        ResultSet rs = null;
        Cliente temp = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "select * from tb_cliente";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()){
                temp = new Cliente();
                temp.setId(rs.getLong("id"));
                temp.setCodigo(rs.getString("codigo"));
                temp.setNome(rs.getString("nome"));

                clientes.add(temp);
            }
        } catch (Exception e){
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return clientes;
    }

    @Override
    public Integer atualizar(Cliente clienteBD) throws Exception {

        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "update tb_cliente set codigo = ?, nome = ? where id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, clienteBD.getCodigo());
            stm.setString(2, clienteBD.getNome());
            stm.setLong(3, clienteBD.getId());
            return stm.executeUpdate();
        } catch (Exception e){
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }
}
