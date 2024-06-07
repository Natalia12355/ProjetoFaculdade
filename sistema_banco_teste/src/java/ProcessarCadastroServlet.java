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

@WebServlet(name = "ProcessarCadastroServlet", urlPatterns = {"/ProcessarCadastroServlet"})
public class ProcessarCadastroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Obtendo o nome do cliente
        String nome = request.getParameter("nome");
        // Obtendo o saldo inicial
        double saldo = Double.parseDouble(request.getParameter("saldo"));
        
        // Declaração das variáveis de conexão e declaração preparada
        Connection conn = null;
        PreparedStatement stmtCliente = null;
        PreparedStatement stmtConta = null;
        
        try {
            // Carrega o driver JDBC do MySQL explicitamente
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelecer conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_banco", "root", "natalia");
            
            // Gerar um número de conta aleatório
            Random random = new Random();
            int numeroConta = 100000 + random.nextInt(900000); // Números de conta de 6 dígitos
            
            // Inserir o cliente na tabela cliente
            String sqlCliente = "INSERT INTO tb_cliente (nome, saldo) VALUES (?, ?)";
            stmtCliente = conn.prepareStatement(sqlCliente, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtCliente.setString(1, nome);
            stmtCliente.setDouble(2, saldo);
            int rowsAffectedCliente = stmtCliente.executeUpdate();
            
            // Se o cliente foi inserido com sucesso
            if (rowsAffectedCliente > 0) {
                // Obter o ID do cliente inserido
                ResultSet generatedKeys = stmtCliente.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idCliente = generatedKeys.getInt(1);
                    
                    // Inserir a conta na tabela conta corrente
                    String sqlConta = "INSERT INTO tb_conta_corrente (numero_conta, cliente_id, saldo) VALUES (?, ?, ?)";
                    stmtConta = conn.prepareStatement(sqlConta);
                    stmtConta.setInt(1, numeroConta);
                    stmtConta.setInt(2, idCliente);
                    stmtConta.setDouble(3, saldo);
                    int rowsAffectedConta = stmtConta.executeUpdate();
                    
                    
                    // Se a conta foi inserida com sucesso
                    if (rowsAffectedConta > 0) {
                        response.sendRedirect("contaAcessada.jsp?idCliente=" + idCliente);
                        System.out.println("Redirecionamento para contaAcessada.jsp ocorreu com sucesso.");

                    } else {
                        out.println("<p>Erro ao criar a conta.</p>");
                    }
                } else {
                    out.println("<p>Erro ao obter o ID do cliente.</p>");
                }
            } else {
                out.println("<p>Erro ao cadastrar o cliente.</p>");
            }
        } catch (SQLException e) {
            out.println("<p>Erro ao salvar o cadastro: " + e.getMessage() + "</p>");
            Logger.getLogger(ProcessarCadastroServlet.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            out.println("<p>Erro interno do servidor. Por favor, entre em contato com o administrador.</p>");
            Logger.getLogger(ProcessarCadastroServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fechar recursos
            if (stmtCliente != null) {
                try {
                    stmtCliente.close();
                } catch (SQLException e) {
                    Logger.getLogger(ProcessarCadastroServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (stmtConta != null) {
                try {
                    stmtConta.close();
                } catch (SQLException e) {
                    Logger.getLogger(ProcessarCadastroServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(ProcessarCadastroServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            out.close();
        }
    }
}
