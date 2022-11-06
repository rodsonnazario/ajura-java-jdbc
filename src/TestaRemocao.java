import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {

		Connection con = ConnectionFactory.recuperarConexao();

		PreparedStatement stm = con.prepareStatement("DELETE FROM produto WHERE id > ?");
		stm.setInt(1, 2);
		stm.execute();

		Integer linhasModificadas = stm.getUpdateCount();
		System.out.println("Linhas modificadas: " + linhasModificadas);

		con.close();
	}
}
