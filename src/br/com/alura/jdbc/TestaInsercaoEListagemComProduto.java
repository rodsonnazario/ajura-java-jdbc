package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.model.Produto;

public class TestaInsercaoEListagemComProduto {

	public static void main(String[] args) throws SQLException {

		Produto produto = new Produto("CÔMODA", "CÔMODA VERTICAL");

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			ProdutoDAO persistenciaProduto = new ProdutoDAO(connection);
			persistenciaProduto.salvar(produto);
			List<Produto> listaDeProdutos = persistenciaProduto.listar();
			listaDeProdutos.stream().forEach(System.out::println);
		}
	}
}
