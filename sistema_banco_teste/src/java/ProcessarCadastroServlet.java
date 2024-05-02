import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
                java.sql.ResultSet generatedKeys = stmtCliente.getGeneratedKeys();
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
                    // Se a conta foi inserida com sucesso
                   if (rowsAffectedConta > 0) {
                       // Encaminhar para contaAcessada.jsp
                       System.out.println("Número da conta: " + numeroConta);
                       request.setAttribute("numeroConta", numeroConta);
                       request.getRequestDispatcher("contaAcessada.jsp").forward(request, response);

                   } else {
                       out.println("<p>Erro ao criar a conta.</p>");
                   }
                } else {
                    throw new SQLException("Erro ao obter o ID do cliente.");
                }
            } else {
                throw new SQLException("Erro ao cadastrar o cliente.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ProcessarCadastroServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("mensagem", "Erro ao salvar o cadastro: " + e.getMessage());
            request.getRequestDispatcher("erroCadastro.jsp").forward(request, response);
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
        }
    }
}
