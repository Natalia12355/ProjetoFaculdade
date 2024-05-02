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
        // Obtendo os parâmetros do formulário
        String numeroConta = request.getParameter("numeroConta");
        double valorSaque = Double.parseDouble(request.getParameter("valorSaque"));
        double novoSaldo = 0.0; // Inicializar o novo saldo

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

            // Consulta o novo saldo
            sql = "SELECT saldo FROM tb_conta_corrente WHERE numero_conta = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, numeroConta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                novoSaldo = rs.getDouble("saldo");
            }

            // Defina o novo saldo como um atributo da solicitação
            request.setAttribute("novoSaldo", novoSaldo);

            // Encaminhe para a página de confirmação de saque
            request.getRequestDispatcher("confirmacaoSaque.jsp").forward(request, response);
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
}
