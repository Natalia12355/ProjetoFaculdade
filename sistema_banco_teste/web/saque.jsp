<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Saque</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #003366;
            color: white;
            padding: 20px;
            text-align: center;
        }
        main {
            padding: 20px;
            text-align: center;
        }
        footer {
            background-color: #f0f0f0;
            padding: 20px;
            text-align: center;
        }
        form {
            display: inline-block;
            text-align: left;
        }
    </style>
</head>
<body>
    <header>
        <h1>Saque</h1>
    </header>
    <main>
        <form>
            <label for="valorSaque">Valor do Saque:</label><br>
            <input type="text" id="valorSaque" name="valorSaque" pattern="\d+(\.\d{1,2})?" title="Digite um valor numérico com até duas casas decimais"><br><br>
            <button type="button" onclick="sacar()">Sacar</button>
        </form>
    </main>
    <footer>
        <a href="index.jsp">Página Inicial</a> |
        <a href="consultarSaldo.jsp">Consultar Saldo</a> |
        <a href="encerrarConta.jsp">Encerrar Conta</a>
    </footer>

    <script>
        function sacar() {
            var valorSaque = document.getElementById('valorSaque').value;

            // Implementar a lógica de saque aqui

            alert("Saque realizado com sucesso!");
        }
    </script>
</body>
</html>
