package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.jdbc.model.Produto;

public class TestaInsercaoComProduto {

	public static void main(String[] args) throws SQLException {
		
		Produto produto = new Produto("CÔMODA", "CÔMODA VERTICAL");
		
		try(Connection con = new ConnectionFactory().recuperarConexao()) {
			String sql = "INSERT INTO produto (nome, descricao) VALUES (?, ?)";
			
			try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, produto.getNome());
				pstm.setString(2, produto.getDescricao());
				pstm.execute();
				
				try(ResultSet rs = pstm.getGeneratedKeys()) {
					while(rs.next()) {
						produto.setId(rs.getInt(1));
					}
				}
			}
		}
		
		System.out.println(produto);
	}
}
