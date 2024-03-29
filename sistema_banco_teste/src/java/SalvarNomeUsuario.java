import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SalvarNomeUsuario")
public class SalvarNomeUsuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém o nome enviado pelo formulário
        String nome = request.getParameter("nome");
        String conta = request.getParameter("conta");
        String saldo = request.getParameter("saldo");
        
        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/projeto_banco";
        String usuario = "root";
        String senha = "natalia";
        
        // Salva o nome na tabela de usuário do MySQL
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "INSERT INTO tb_cliente (nome,conta, saldo) VALUES (?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, conta);
            statement.setString(3, saldo);
            statement.executeUpdate();
            
            // Retorna uma resposta ao cliente
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h3>Dados salvo com sucesso!</h3></body></html>");
        } catch (SQLException e) {
            // Se ocorrer um erro, imprime a mensagem de erro
            throw new ServletException("Erro ao salvar os dados no banco de dados", e);
        }
    }
}
