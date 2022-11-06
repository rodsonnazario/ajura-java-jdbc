import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
	
		Connection con = ConnectionFactory.recuperarConexao();
		
		PreparedStatement stm = con.prepareStatement("SELECT id, nome, descricao FROM produto");
		stm.execute();
		
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
