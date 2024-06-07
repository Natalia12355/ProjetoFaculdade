<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Conta Acessada - NatBank</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f3f3f3;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        footer {
            background-color: #004386;
            color: #fff;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
            text-align: center; /* centralizar o texto */
        }

        footer nav a {
            color: #fff; /* cor do texto branco */
            text-decoration: none;
            margin: 0 10px; /* espaço entre os links */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Detalhes da Conta</h2>
        <% 
            // Recuperando o número da conta da URL
            String numeroConta = request.getParameter("numeroConta");

            // Estabelecendo a conexão com o banco de dados
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                // Carregando o driver JDBC do MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Conectando ao banco de dados
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_banco", "root", "natalia");
                
                // Consulta para obter os dados da conta
                String sql = "SELECT c.nome, cc.numero_conta, cc.saldo FROM tb_cliente c INNER JOIN tb_conta_corrente cc ON c.id_cliente = cc.cliente_id WHERE cc.numero_conta = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, numeroConta);
                rs = stmt.executeQuery();

                // Verificando se encontrou algum registro
                if (rs.next()) {
                    String nomeCliente = rs.getString("nome");
                    String numeroContaResultado = rs.getString("numero_conta");
                    double saldoConta = rs.getDouble("saldo");
        %>
                    <p>Cliente: <%= nomeCliente %></p>
                    <p>Número da Conta: <%= numeroContaResultado %></p>
                    <p>Saldo: R$ <%= saldoConta %></p>
        <%
                } else {
        %>
                    <p>Conta não encontrada.</p>
        <%
                }
            } catch (Exception e) {
        %>
                <p>Ocorreu um erro: <%= e.getMessage() %></p>
        <%
            } finally {
                // Fechando recursos
                if (rs != null) {
                    try { rs.close(); } catch (SQLException e) { }
                }
                if (stmt != null) {
                    try { stmt.close(); } catch (SQLException e) { }
                }
                if (conn != null) {
                    try { conn.close(); } catch (SQLException e) { }
                }
            }
        %>
    </div>
    <footer>
        <nav>
        <a href="index.jsp">Página Inicial</a> |
        <a href="saque.jsp">Saque</a> |
        <a href="depositar.jsp">Depositar</a> 
        <a href="encerrarConta.jsp">Encerrar Conta</a> |
        
            </nav>
    </footer>
</body>
</html>
