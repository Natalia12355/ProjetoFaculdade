import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/SaqueServlet")
public class SaqueServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtendo os parâmetros da solicitação
        String numeroConta = request.getParameter("numeroConta");
        double valorSaque = Double.parseDouble(request.getParameter("valorSaque"));

        // Declaração das variáveis de conexão e declaração preparada
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelecer conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_banco", "root", "natalia");

            // Atualiza o saldo na tabela tb_conta_corrente após o saque
            String sql = "UPDATE tb_conta_corrente SET saldo = saldo - ? WHERE numero_conta = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, valorSaque);
            stmt.setString(2, numeroConta);
            stmt.executeUpdate();

            // Retorna o novo saldo após o saque
            double novoSaldo = obterSaldo(conn, numeroConta);
            response.getWriter().println(novoSaldo);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SaqueServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Erro ao realizar o saque.");
        } finally {
            // Fechar recursos
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    Logger.getLogger(SaqueServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(SaqueServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    // Método para obter o saldo atual da conta
    private double obterSaldo(Connection conn, String numeroConta) throws SQLException {
        double saldo = 0;
        String sql = "SELECT saldo FROM tb_conta_corrente WHERE numero_conta = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroConta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    saldo = rs.getDouble("saldo");
                }
            }
        }
        return saldo;
    }
}
