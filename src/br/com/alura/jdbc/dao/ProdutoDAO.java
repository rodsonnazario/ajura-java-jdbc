package br.com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.model.Categoria;
import br.com.alura.jdbc.model.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Produto produto) {
		try {
			String sql = "INSERT INTO produto (nome, descricao) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, produto.getNome());
				pstm.setString(2, produto.getDescricao());
				pstm.execute();

				try (ResultSet rs = pstm.getGeneratedKeys()) {
					while (rs.next()) {
						produto.setId(rs.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void salvarComCategoria(Produto produto) {
		try {
			String sql = "INSERT INTO produto (nome, descricao, categoria_id) VALUES (?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, produto.getNome());
				pstm.setString(2, produto.getDescricao());
				pstm.setInt(3, produto.getCategoriaId());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						produto.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> listar() {
		try {
			List<Produto> produtos = new ArrayList<>();
			String sql = "SELECT id, nome, descricao FROM produto";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
				transformarResultSetEmProduto(produtos, pstm);
			}

			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> buscar(Categoria categoria) {
		try {
			List<Produto> produtos = new ArrayList<>();
			String sql = "SELECT id, nome, descricao FROM produto WHERE categoria_id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, categoria.getId());
				pstm.execute();
				transformarResultSetEmProduto(produtos, pstm);
			}

			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Integer id) {
		String sql = "DELETE FROM produto WHERE id = ?";

		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(String nome, String descricao, Integer id) {
		String sql = "UPDATE produto p SET p.nome = ?, p.descricao = ? WHERE id = ?";

		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, nome);
			stm.setString(2, descricao);
			stm.setInt(3, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void transformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) {
		try (ResultSet rs = pstm.getResultSet()) {
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Produto produto = new Produto(id, nome, descricao);
				produtos.add(produto);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
