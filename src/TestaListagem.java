import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
	
		Connection con = ConnectionFactory.recuperarConexao();
		
		Statement stm = con.createStatement();
		stm.execute("SELECT id, nome, descricao FROM produto");
		
		ResultSet rs = stm.getResultSet();
		
		while(rs.next()) {
			Integer id = rs.getInt("id");
			String nome = rs.getString("nome");
			String descricao = rs.getString("descricao");
			System.out.println(String.format("id: %s, nome: %s, descricao: %s", id, nome, descricao));
		}		
		
		con.close();
	}
}
