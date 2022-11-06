package br.com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.model.Categoria;

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
}
