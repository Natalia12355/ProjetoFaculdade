import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EncerrarContaServlet")
public class EncerrarContaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtendo o número da conta a ser encerrada do parâmetro da solicitação
        String numeroConta = request.getParameter("numeroConta");
        
        // Declaração das variáveis de conexão e declaração preparada
        Connection conn = null;
        PreparedStatement stmtConta = null;
        
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelecer conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_banco", "root", "natalia");
            
            // Deletar a conta corrente associada à conta
            String sqlConta = "DELETE FROM tb_conta_corrente WHERE numero_conta = ?";
            stmtConta = conn.prepareStatement(sqlConta);
            stmtConta.setString(1, numeroConta);
            int rowsAffectedConta = stmtConta.executeUpdate();
            
            // Verificar se a conta foi encontrada e deletada
            if (rowsAffectedConta > 0) {
                // A conta foi excluída com sucesso, então não precisamos excluir explicitamente o cliente associado
                response.sendRedirect("index.jsp"); // Redirecionar para a página inicial após o encerramento da conta
            } else {
                response.getWriter().println("Erro ao encerrar a conta: Conta não encontrada.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EncerrarContaServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().println("Erro ao encerrar a conta: " + ex.getMessage());
        } finally {
            // Fechar recursos
            if (stmtConta != null) {
                try {
                    stmtConta.close();
                } catch (SQLException e) {
                    Logger.getLogger(EncerrarContaServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(EncerrarContaServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
