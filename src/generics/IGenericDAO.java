package generics;

import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T> {
    Integer cadastrar(T entity) throws SQLException;
    T consultar(String codigo) throws SQLException;
    Integer excluir(T entity) throws SQLException;
    List<T> consultarTodos() throws SQLException;
    Integer atualizar(T entity) throws SQLException;
}
