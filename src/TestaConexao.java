import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {

		Connection connection = ConnectionFactory.recuperarConexao();
		System.out.println("Fechando conexão");
		connection.close();
	}
}
