package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.model.Produto;

public class TestaInsercaoComProduto {

	public static void main(String[] args) throws SQLException {

		Produto produto = new Produto("CÔMODA", "CÔMODA VERTICAL");

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			ProdutoDAO persistenciaProduto = new ProdutoDAO(connection);
			persistenciaProduto.salvar(produto);
			persistenciaProduto.listar();
		}		
	}
}
