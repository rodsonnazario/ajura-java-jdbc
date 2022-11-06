package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.jdbc.factory.ConnectionFactory;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection con = connectionFactory.recuperarConexao();
		
		Statement stm = con.createStatement();
		stm.execute("INSERT INTO produto (nome, descricao, categoria_id) "
				+ "VALUES ('MOUSE', 'MOUSE SEM FIO', 3)"
				,Statement.RETURN_GENERATED_KEYS);
		
		ResultSet rs = stm.getGeneratedKeys();
		while (rs.next()) {
			Integer id = rs.getInt(1);
			System.out.println("O id criado foi: "+ id);
		}
		
		rs.close();
		stm.close();		
		con.close();
	}
}
