import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessarCadastroServlet")
public class ProcessarCadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //testar se a pagina cadatro está chamado	
    	  System.out.println("Servlet ProcessarCadastroServlet chamado.");
    	  
    	  String nome = request.getParameter("nome");
  	    if (nome == null) {
  	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O nome não pode ser nulo.");
  	        return;
  	    }
  	    
  	    String cpf = request.getParameter("cpf");
  	    if (cpf == null) {
  	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O CPF não pode ser nulo.");
  	        return;
  	    }
  	    
  	    String endereco = request.getParameter("endereco");
  	    if (endereco == null) {
  	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O endereço não pode ser nulo.");
  	        return;
  	    }
  	    
  	    String telefone = request.getParameter("telefone");
  	    if (telefone == null) {
  	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O telefone não pode ser nulo.");
  	        return;
  	    }
  	    
  	    double saldo;
  	    try {
  	        saldo = Double.parseDouble(request.getParameter("saldo"));
  	    } catch (NumberFormatException e) {
  	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O saldo deve ser um número válido.");
  	        return;
  	    }
  	    
  	    String senha = request.getParameter("senha");
  	    if (senha == null) {
  	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "A senha não pode ser nula.");
  	        return;
  	    }
  	  
  	
      //String nome = request.getParameter("nome");
      //String cpf = request.getParameter("cpf");
      //String endereco = request.getParameter("endereco");
      //String telefone = request.getParameter("telefone");
      //double saldo = Double.parseDouble(request.getParameter("saldo"));
      //String senha = request.getParameter("senha");

        // Configurações de conexão com o banco de dados MySQL
        String url = "jdbc:mysql://localhost:3306/projeto_banco"; // Substitua pelo seu URL do MySQL
        String usuario = "root"; // Substitua pelo seu nome de usuário do MySQL
        String senhaBanco = "natalia"; // Substitua pela sua senha do MySQL

        try {
            // Estabelece a conexão com o banco de dados MySQL
            Connection conexao = DriverManager.getConnection(url, usuario, senhaBanco);

            // Prepara a instrução SQL para inserir os dados na tabela cliente
            String sql = "INSERT INTO cliente (nome, cpf, endereco, telefone, saldo, senha) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement declaracao = conexao.prepareStatement(sql);
            declaracao.setString(1, nome);
            declaracao.setString(2, cpf);
            declaracao.setString(3, endereco);
            declaracao.setString(4, telefone);
            declaracao.setDouble(5, saldo);
            declaracao.setString(6, senha);

            // Executa a instrução SQL para inserir os dados na tabela cliente
            int linhasAfetadas = declaracao.executeUpdate();

            // Verifica se os dados foram inseridos com sucesso
            if (linhasAfetadas > 0) {
                // Envie uma resposta para o cliente informando que o cadastro foi realizado com sucesso
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h2>Cadastro realizado com sucesso!</h2>");
                out.println("</body></html>");
            }

            // Fecha a conexão com o banco de dados MySQL
            conexao.close();
        } catch (SQLException e) {
            // Trata qualquer erro de SQL que ocorra durante a inserção dos dados
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar o cadastro. Tente novamente mais tarde.");
        }
    }
}
