<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmação de Saque</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        header {
            background-color: #003366;
            color: white;
            padding: 20px;
        }
        main {
            padding: 20px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Confirmação de Saque</h1>
    </header>
    <main>
        <p>Saque realizado com sucesso!</p>
        <p>Seu saldo foi atualizado.</p>
        <%-- Adicione esta linha para exibir o novo saldo --%>
        <p>Novo Saldo: R$ <%= request.getAttribute("novoSaldo") %></p>
        <%-- Adicione esta linha para exibir o número da conta --%>
        <p>Número da Conta: <%= request.getParameter("numeroConta") %></p>
    </main>

    <%
        // Recupere o número da conta e o novo saldo da solicitação
        String numeroConta = request.getParameter("numeroConta");
        String novoSaldo = request.getAttribute("novoSaldo").toString();
        // Redirecione para a página contaAcessada.jsp com o número da conta
        response.sendRedirect("contaAcessada.jsp?numeroConta=" + numeroConta + "&novoSaldo=" + novoSaldo);
    %>
</body>
</html>
