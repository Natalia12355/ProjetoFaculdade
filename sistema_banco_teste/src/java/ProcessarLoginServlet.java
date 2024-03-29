import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ProcessarLoginServlet", urlPatterns = {"/ProcessarLoginServlet"})
public class ProcessarLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obter os parâmetros do formulário
        String nome = request.getParameter("nome");
        String numeroConta = request.getParameter("numeroConta");

        // Verificar se as credenciais são válidas
        boolean credenciaisValidas = verificarCredenciais(nome, numeroConta);

        if (credenciaisValidas) {
            // Redirecionar para a página contaAcessada.jsp
            response.sendRedirect("contaAcessada.jsp?nome=" + nome + "&numeroConta=" + numeroConta);
        } else {
            // Se as credenciais não forem válidas, redirecionar de volta para a página de login
            response.sendRedirect("login.jsp?erro=1");
        }
    }

    // Método para verificar as credenciais do usuário
    private boolean verificarCredenciais(String nome, String numeroConta) {
        // Declaração das variáveis de conexão e declaração preparada
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean credenciaisValidas = false;

        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelecer conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seu_banco_de_dados", "seu_usuario", "sua_senha");

            // Consulta para verificar as credenciais do usuário
            String sql = "SELECT nome, numero_conta FROM tabela_usuarios WHERE nome = ? AND numero_conta = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, numeroConta);
            rs = stmt.executeQuery();

            // Se encontrar um resultado, as credenciais são válidas
            credenciaisValidas = rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Trate melhor os erros em um ambiente de produção
        } finally {
            // Fechar recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return credenciaisValidas;
    }
}
