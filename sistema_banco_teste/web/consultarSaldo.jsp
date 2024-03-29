<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar Saldo</title>
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

        footer {
            margin-top: 20px;
            text-align: center;
        }

        footer a {
            color: #004386;
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
        <h1>Consultar Saldo</h1>
    </header>
    <main>
        <p>Seu saldo atual �: <span id="saldo">0</span></p>
    </main>
    <footer>
        <a href="index.jsp">P�gina Inicial</a> |
        <a href="cadastro.jsp">Cadastro</a> |
        <a href="login.jsp">Acessar Conta</a> |
        <a href="encerrarConta.jsp">Encerrar Conta</a> |
        <a href="saque.jsp">Saque</a>
    </footer>

    <script>
        // Simulando a obten��o do saldo atual da conta do servidor
        // Aqui voc� pode substituir este valor pela l�gica real de obten��o do saldo
        var saldoAtual = 1000; // Supondo um saldo inicial de 1000 unidades monet�rias

        // Atualiza o elemento HTML com o saldo atual da conta
        document.getElementById("saldo").textContent = saldoAtual;
    </script>
</body>
</html>
