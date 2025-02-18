import dao.IProdutosDAO;
import dao.ProdutosDAO;
import domain.Produtos;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ProdutoTest {
    private IProdutosDAO dao;

    @Test
    public void cadastrarTest() throws Exception {
        dao = new ProdutosDAO();
        Produtos produto = new Produtos();

        produto.setNome("Cadastro");
        produto.setCodigo("01");

        Integer qtd = dao.cadastrar(produto);
        Assert.assertTrue(qtd == 1);

        Produtos produtoBD = dao.consultar(produto.getCodigo());

        Assert.assertNotNull(produtoBD);
        Assert.assertNotNull(produtoBD.getId());
        Assert.assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        Assert.assertEquals(produto.getNome(), produtoBD.getNome());

        // Deletando para não dar erro nos testes de consulta
        dao.excluir(produtoBD);
    }

    @Test
    public void excluirTest() throws Exception {
        dao = new ProdutosDAO();
        Produtos produto = new Produtos();

        produto.setNome("Excluir");
        produto.setCodigo("02");

        dao.cadastrar(produto);
        Produtos produtoBD = dao.consultar(produto.getCodigo());

        Integer qtdDel = dao.excluir(produtoBD);
        Assert.assertNotNull(qtdDel);
    }

    @Test
    public void consultarTest() throws Exception {
        dao = new ProdutosDAO();
        Produtos produto = new Produtos();

        produto.setNome("Consultar");
        produto.setCodigo("03");
        dao.cadastrar(produto);

        Produtos produtoBD = dao.consultar(produto.getCodigo());

        Assert.assertNotNull(produtoBD);
        Assert.assertNotNull(produtoBD.getId());
        Assert.assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        Assert.assertEquals(produto.getNome(), produtoBD.getNome());

        // Deletando para não dar erro nos testes de consulta
        dao.excluir(produtoBD);
    }

    @Test
    public void consultarTodosTest() throws Exception {
        dao = new ProdutosDAO();

        Produtos produto1 = new Produtos();
        produto1.setNome("Consultar1");
        produto1.setCodigo("04");
        dao.cadastrar(produto1);

        Produtos produto2 = new Produtos();
        produto2.setNome("Consultar2");
        produto2.setCodigo("05");
        dao.cadastrar(produto2);

        Produtos produto3 = new Produtos();
        produto3.setNome("Consultar3");
        produto3.setCodigo("06");
        dao.cadastrar(produto3);

        List<Produtos> produtos = dao.consultarTodos();
        Assert.assertNotNull(produtos);
        Assert.assertEquals(3, produtos.size());

        // Deletando a tabela
        for (Produtos produto : produtos) {
            dao.excluir(produto);
        }
        produtos = dao.consultarTodos();
        Assert.assertEquals(produtos.size(), 0);
    }

    @Test
    public void atualizarTest() throws Exception {
        dao = new ProdutosDAO();
        Produtos produto = new Produtos();

        produto.setNome("Cadastrando");
        produto.setCodigo("07");

        Integer qtd = dao.cadastrar(produto);
        Assert.assertTrue(qtd == 1);

        Produtos produtoBD = dao.consultar(produto.getCodigo());

        produtoBD.setNome("Atualizado");
        produtoBD.setCodigo("08");
        Integer update = dao.atualizar(produtoBD);
        Assert.assertTrue(update == 1);

        Produtos produtoAntigo = dao.consultar(produto.getCodigo());
        Assert.assertNull(produtoAntigo);

        Produtos produtoAtualizado = dao.consultar(produtoBD.getCodigo());
        Assert.assertNotNull(produtoAtualizado);
        Assert.assertNotNull(produtoAtualizado.getId());
        Assert.assertEquals(produtoAtualizado.getCodigo(), produtoBD.getCodigo());
        Assert.assertEquals(produtoAtualizado.getNome(), produtoBD.getNome());

        // Deletando para não dar erro nos testes de consulta
        dao.excluir(produtoAtualizado);
    }
}
