import dao.ClienteDAO;
import dao.IClienteDAO;
import domain.Cliente;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ClienteTest {
    private IClienteDAO dao;

    @Test
    public void cadastrarTest() throws Exception {
        dao = new ClienteDAO();
        Cliente cliente = new Cliente();

        cliente.setNome("Cadastro");
        cliente.setCodigo("01");

        Integer qtd = dao.cadastrar(cliente);
        Assert.assertTrue(qtd == 1);

        Cliente clienteBD = dao.consultar(cliente.getCodigo());

        Assert.assertNotNull(clienteBD);
        Assert.assertNotNull(clienteBD.getId());
        Assert.assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        Assert.assertEquals(cliente.getNome(), clienteBD.getNome());

        // Deletando para não dar erro nos testes de consulta
        dao.excluir(clienteBD);
    }

    @Test
    public void excluirTest() throws Exception{
        dao = new ClienteDAO();
        Cliente cliente = new Cliente();

        cliente.setNome("Excluir");
        cliente.setCodigo("02");

        dao.cadastrar(cliente);
        Cliente clienteBD = dao.consultar(cliente.getCodigo());

        Integer qtdDel = dao.excluir(clienteBD);
        Assert.assertNotNull(qtdDel);
    }

    @Test
    public void consultarTest() throws Exception{
        dao = new ClienteDAO();
        Cliente cliente = new Cliente();

        cliente.setNome("Consultar");
        cliente.setCodigo("03");
        dao.cadastrar(cliente);

        Cliente clienteBD = dao.consultar(cliente.getCodigo());

        Assert.assertNotNull(clienteBD);
        Assert.assertNotNull(clienteBD.getId());
        Assert.assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        Assert.assertEquals(cliente.getNome(), clienteBD.getNome());


        // Deletando para não dar erro nos testes de consulta
        dao.excluir(clienteBD);
    }

    @Test
    public void consultartTodosTest() throws Exception{
        dao = new ClienteDAO();

        Cliente cliente1 = new Cliente();
        cliente1.setNome("Consultar1");
        cliente1.setCodigo("04");
        dao.cadastrar(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Consultar2");
        cliente2.setCodigo("05");
        dao.cadastrar(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.setNome("Consultar3");
        cliente3.setCodigo("06");
        dao.cadastrar(cliente3);

        List<Cliente> clientes = dao.consultarTodos();
        Assert.assertNotNull(clientes);
        Assert.assertEquals(3, clientes.size());

        // Deletando a tabela
        for (Cliente cliente : clientes){
            dao.excluir(cliente);
        }
        clientes = dao.consultarTodos();
        Assert.assertEquals(clientes.size(), 0);
    }

    @Test
    public void atualizarTest() throws Exception{
        dao = new ClienteDAO();
        Cliente cliente = new Cliente();

        cliente.setNome("Cadastrando");
        cliente.setCodigo("07");

        Integer qtd = dao.cadastrar(cliente);
        Assert.assertTrue(qtd == 1);

        Cliente clienteBD = dao.consultar(cliente.getCodigo());

        clienteBD.setNome("Atualizar");
        clienteBD.setCodigo("08");
        Integer update = dao.atualizar(clienteBD);
        Assert.assertTrue(update == 1);

        Cliente clienteAntigo = dao.consultar(cliente.getCodigo());
        Assert.assertNull(clienteAntigo);

        Cliente clienteAtulizado = dao.consultar(clienteBD.getCodigo());
        Assert.assertNotNull(clienteAtulizado);
        Assert.assertNotNull(clienteAtulizado.getId());
        Assert.assertEquals(clienteAtulizado.getCodigo(), clienteBD.getCodigo());
        Assert.assertEquals(clienteAtulizado.getNome(), clienteBD.getNome());

        // Deletando para não dar erro nos testes de consulta
        dao.excluir(clienteAtulizado);
    }
}
