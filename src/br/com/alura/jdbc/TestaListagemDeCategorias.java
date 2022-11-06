package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.jdbc.dao.CategoriaDAO;
import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.model.Categoria;
import br.com.alura.jdbc.model.Produto;

public class TestaListagemDeCategorias {

	public static void main(String[] args) throws SQLException {

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			List<Categoria> listaDeCategorias = categoriaDAO.listarComProdutos();
			listaDeCategorias.stream().forEach(categoria -> {
				System.out.println(categoria);
				for (Produto produto : categoria.getProdutos()) {
					System.out.println(produto);
				}
			});
		}
	}
}
