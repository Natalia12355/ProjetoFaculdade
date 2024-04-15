import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ConsultarSaldoServlet")
public class ConsultarSaldoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtendo o número da conta associada ao cliente
        String numeroConta = request.getParameter("numeroConta");

        // Declaração das variáveis de conexão e declaração preparada
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelecer conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_banco", "root", "natalia");

            // Consulta SQL para obter o saldo do cliente com base no número da conta
            String sql = "SELECT saldo FROM tb_conta_corrente WHERE numero_conta = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, numeroConta);
            
            Logger.getLogger(ConsultarSaldoServlet.class.getName()).log(Level.INFO, "Executando consulta SQL: {0}", stmt.toString());
            
            rs = stmt.executeQuery();

            if (rs.next()) {
                double saldo = rs.getDouble("saldo");
                response.getWriter().println("Seu saldo atual é: " + saldo);
            } else {
                response.getWriter().println("Saldo não encontrado para a conta: " + numeroConta);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConsultarSaldoServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().println("Erro ao consultar o saldo: " + ex.getMessage());
        } finally {
            // Fechar recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(ConsultarSaldoServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    Logger.getLogger(ConsultarSaldoServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(ConsultarSaldoServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
