package dao;

import domain.Produtos;
import generics.GenericDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ProdutosDAO extends GenericDAO<Produtos> implements IProdutosDAO {
    @Override
    protected String getTableName() {
        return "tb_produto";
    }

    @Override
    protected String getSequence() {
        return "sq_produto";
    }

    @Override
    protected Produtos mapResultSetToEntity(ResultSet rs) throws SQLException {
        Produtos produto = new Produtos();
        produto.setId(rs.getLong("id"));
        produto.setCodigo(rs.getString("codigo"));
        produto.setNome(rs.getString("nome"));
        return produto;
    }

    @Override
    protected void mapStatementForCreate(PreparedStatement stm, Produtos entity) throws SQLException {
        stm.setString(1, entity.getCodigo());
        stm.setString(2, entity.getNome());
    }

    @Override
    protected void mapStatementForUpdate(PreparedStatement stm, Produtos entity) throws SQLException {
        stm.setString(1, entity.getCodigo());
        stm.setString(2, entity.getNome());
        stm.setLong(3, entity.getId());
    }

    @Override
    protected void mapStatementForDelete(PreparedStatement stm, Produtos entity) throws SQLException {
        stm.setString(1, entity.getCodigo());
    }
}
