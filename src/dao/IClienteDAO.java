package dao;

import domain.Cliente;

import java.util.List;

public interface IClienteDAO {
    public Integer cadastrar(Cliente cliente) throws Exception;

    Cliente consultar(String codigo) throws Exception;

    Integer excluir(Cliente cliente) throws Exception;

    List<Cliente> consultarTodos() throws Exception;

    Integer atualizar(Cliente clienteBD) throws Exception;
}
