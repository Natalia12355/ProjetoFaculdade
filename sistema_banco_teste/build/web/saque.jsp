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
        <form id="saqueForm" action="SaqueServlet" method="post">
            <label for="numeroContaAcessada">Número da Conta Acessada:</label>
            <span id="numeroContaAcessada"><%= request.getParameter("numeroConta") %></span><br><br>
            <label for="valorSaque">Valor do Saque:</label><br>
            <input type="hidden" name="numeroConta" value="<%= request.getParameter("numeroConta") %>">
            <input type="text" id="valorSaque" name="valorSaque" pattern="\d+(\.\d{1,2})?" title="Digite um valor numérico com até duas casas decimais"><br><br>
            <button type="submit">Sacar</button>
        </form>
    </main>
    <footer>
        <a href="index.jsp">Página Inicial</a> |
        <a href="#" onclick="redirecionarParaConsultarSaldo()">Consultar Saldo</a> |
        <a href="encerrarConta.jsp">Encerrar Conta</a>
    </footer>
    <script>
        // Função para redirecionar para consultarSaldo.jsp
        function redirecionarParaConsultarSaldo() {
            var numeroConta = document.getElementById("numeroContaAcessada").textContent.trim();
            window.location.href = "consultarSaldo.jsp?numeroConta=" + numeroConta;
        }
    </script>
</body>
</html>
