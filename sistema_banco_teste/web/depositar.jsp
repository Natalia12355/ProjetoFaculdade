<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Depositar</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f3f3f3;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #004386;
            color: #fff;
            text-align: center;
            padding: 20px 0;
        }

        main {
            max-width: 600px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        form {
            text-align: left;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button {
            background-color: #004386;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #00325b;
        }

        footer {
            margin-top: 20px;
            text-align: center;
        }

        footer a {
            color: #004386;
            text-decoration: none;
            margin: 0 10px;
        }

        footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <header>
        <h1>Depositar</h1>
    </header>
    <main>
        <form action="ProcessarDeposito" method="post">
        Número da Conta: <input type="text" name="numeroConta"><br><br>
        Valor do Depósito: <input type="number" name="valorDeposito"><br><br>
        <input type="submit" value="Depositar">
    </form>
    </main>
    <footer>
        <a href="index.jsp">Página Inicial</a> |
        <a href="consultarSaldo.jsp">Consultar Saldo</a> |
        <a href="encerrarConta.jsp">Encerrar Conta</a> |
        <a href="saque.jsp">Saque</a>
    </footer>
</body>
</html>
