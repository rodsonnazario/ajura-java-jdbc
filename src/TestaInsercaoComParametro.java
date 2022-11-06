import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection con = connectionFactory.recuperarConexao()) {
			con.setAutoCommit(false);

			try (PreparedStatement stm = con.prepareStatement("INSERT INTO produto (nome, descricao) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS)) {
				adicionarVariavel("SMARTTV", "45 POLEGADAS", stm);
				adicionarVariavel("RADIO", "RADIO DE BATERIA", stm);

				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO");

				con.rollback();
			}
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);

		stm.execute();

		try (ResultSet rs = stm.getGeneratedKeys()) {
			while (rs.next()) {
				Integer id = rs.getInt(1);
				System.out.println("O id criado foi: " + id);
			}
		}
	}
}
