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
    <header>
        <h1>Consultar Saldo</h1>
    </header>
    <main>
        <p>Seu saldo atual é: <span id="saldo">0</span></p>
    </main>
    <footer>
        <nav>
        <a href="index.jsp">Página Inicial</a> |
        <a href="consultarSaldo.jsp">Consultar Saldo</a> |
        <a href="saque.jsp">Saque</a> |
        <a href="depositar.jsp">Depositar</a> 
        <a href="encerrarConta.jsp">Encerrar Conta</a> |
        
            </nav>
    </footer>

    <script>
        // Simulando a obtenção do saldo atual da conta do servidor
        // Aqui você pode substituir este valor pela lógica real de obtenção do saldo
        var saldoAtual = 1000; // Supondo um saldo inicial de 1000 unidades monetárias

        // Atualiza o elemento HTML com o saldo atual da conta
        document.getElementById("saldo").textContent = saldoAtual;
    </script>
</body>
</html>
