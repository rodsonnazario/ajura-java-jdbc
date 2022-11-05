import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		
		Connection con = ConnectionFactory.recuperarConexao();
		
		Statement stm = con.createStatement();
		stm.execute("DELETE FROM produto WHERE id > 2");
		
		Integer linhasModificadas = stm.getUpdateCount();		
		System.out.println("Linhas modificadas: " + linhasModificadas);		
		
		con.close();
	}
}
