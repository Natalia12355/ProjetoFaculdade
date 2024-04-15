<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - NatBank</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f3f3f3;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 400px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #004386;
            text-align: center;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button {
            background-color: #004386;
            color: #fff;
            border: none;
            padding: 15px 20px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            background-color: #00325b;
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
        <h1>Login - NatBank</h1>
        <form action="contaAcessada.jsp" method="post">
             <label for="numeroConta">Número da Conta:</label><br>
             <br>
            <input type="text" id="numeroConta" name="numeroConta" value="${param.numeroConta}">
            <button type="submit">Login</button>
        </form>
        
    </div>
            <footer>
        <nav>
        <a href="index.jsp">Página Inicial</a> |
        <a href="consultarSaldo.jsp">Consultar Saldo</a> |
        <a href="saque.jsp">Saque</a> |
        <a href="depositar.jsp">Depositar</a> |
        <a href="encerrarConta.jsp">Encerrar Conta</a> |        
        </nav>
    </footer>
</body>
</html>
