import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProcessarDeposito")
public class ProcessarDeposito extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Parâmetros do formulário
        String numeroConta = request.getParameter("numeroConta");
        double valorDeposito = Double.parseDouble(request.getParameter("valorDeposito"));

        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/projeto_banco";
        String usuario = "root";
        String senha = "natalia";

        // Consulta SQL para obter o saldo atual da conta
        String sqlConsulta = "SELECT saldo FROM tb_conta_corrente WHERE numero_conta = ?";
        // Atualização SQL para adicionar o valor do depósito ao saldo
        String sqlAtualizacao = "UPDATE tb_conta_corrente SET saldo = saldo + ? WHERE numero_conta = ?";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            // Consulta para obter o saldo atual da conta
            PreparedStatement stmtConsulta = conexao.prepareStatement(sqlConsulta);
            stmtConsulta.setString(1, numeroConta);
            ResultSet resultado = stmtConsulta.executeQuery();

            if (resultado.next()) {
                double saldoAtual = resultado.getDouble("saldo");

                // Atualização do saldo da conta com o valor do depósito
                PreparedStatement stmtAtualizacao = conexao.prepareStatement(sqlAtualizacao);
                stmtAtualizacao.setDouble(1, valorDeposito);
                stmtAtualizacao.setString(2, numeroConta);
                int linhasAfetadas = stmtAtualizacao.executeUpdate();

                if (linhasAfetadas > 0) {
                    out.println("<html><body><h3>Depósito realizado com sucesso!</h3></body></html>");
                    out.println("<p>Novo saldo: " + (saldoAtual + valorDeposito) + "</p>");
                } else {
                    out.println("<html><body><h3>Erro ao realizar o depósito.</h3></body></html>");
                }
            } else {
                out.println("<html><body><h3>Conta não encontrada.</h3></body></html>");
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao processar o depósito.", e);
        }
    }
}
