<%-- 
    Document   : usuarios
    Created on : 25 de mar de 2024, 15:08:31
    Author     : Natalia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de Cliente</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #004d99;
            color: #fff;
            text-align: center;
            padding: 20px 0;
        }

        h2 {
            color: #004d99;
            text-align: center;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            margin-bottom: 5px;
            font-weight: bold;
            color: #004d99;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #004d99;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #003366;
        }

        footer {
            margin-top: 20px;
            text-align: center;
            color: #004d99;
        }

        footer a {
            color: #004d99;
            text-decoration: none;
            margin: 0 5px;
        }

        footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<header>
    <h1>NatBank</h1>
</header>
<h2>Cadastro de Conta</h2>
<form action="ProcessarCadastroServlet" method="post">
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" required>
    
    
    <label for="saldo">Saldo Inicial:</label>
    <input type="number" id="saldo" name="saldo" required>
    
    <input type="submit" value="Cadastrar">
</form>
<footer>
    <a href="index.jsp">Página Inicial</a> |
    <a href="login.jsp">Acessar Conta</a> |
    <a href="consultarSaldo.jsp">Consultar Saldo</a> |
    <a href="saque.jsp">Saque</a> |
    <a href="depositar.jsp">Depositar</a> |
    <a href="encerrarConta.jsp">Encerrar Conta</a> |
    
</footer>
</body>
</html>
