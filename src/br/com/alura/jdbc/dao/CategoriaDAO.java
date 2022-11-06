package br.com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.model.Categoria;
import br.com.alura.jdbc.model.Produto;

public class CategoriaDAO {
	
	private Connection connection;

	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Categoria> listar() throws SQLException {

		List<Categoria> categorias = new ArrayList<>();
		String sql = "SELECT id, nome FROM categoria";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try(ResultSet rs = pstm.getResultSet()) {
				
				while (rs.next()) {
					Integer id = rs.getInt("id");
					String nome = rs.getString("nome");					
					Categoria categoria = new Categoria(id, nome);					
					categorias.add(categoria);
				}
			}
		}

		return categorias;
	}

	public List<Categoria> listarComProdutos() throws SQLException {
		Categoria categoria = null;
		List<Categoria> categorias = new ArrayList<>();
		String sql = "SELECT c.id, c.nome, p.id, p.nome, p.descricao "
				+ "FROM categoria c "
				+ "INNER JOIN produto p ON c.id=p.categoria_id";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try(ResultSet rs = pstm.getResultSet()) {				
				while (rs.next()) {
					Integer idCategoria = rs.getInt(1);
					String nomeCategoria = rs.getString(2);
					Integer idProduto = rs.getInt(3);
					String nomeProduto = rs.getString(4);
					String descricaoProduto = rs.getString(5);
					
					if(categoria == null || !categoria.getNome().equals(nomeCategoria)) {
						categoria = new Categoria(idCategoria, nomeCategoria);						
						categorias.add(categoria);	
					}
					Produto produto = new Produto(idProduto, nomeProduto, descricaoProduto);
					categoria.adicionarProduto(produto);
				}
			}
		}

		return categorias;
	}
}
