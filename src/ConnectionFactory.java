import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection recuperarConexao() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mariadb://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", 
				"root", 
				"root");
	}

}
