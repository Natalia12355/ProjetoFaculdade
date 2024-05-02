<%
// Obtém o número da conta dos parâmetros da URL
String numeroConta = request.getParameter("numeroConta");
%>

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
            text-align: center;
        }

        footer nav a {
            color: #fff;
            text-decoration: none;
            margin: 0 10px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Consultar Saldo</h1>
    </header>
    <main>
        <p>Número da Conta Acessada: <%= numeroConta %></p>
        <p>Seu saldo atual é: <span id="saldo">Carregando...</span></p>
    </main>
    <footer>
        <nav>
            <a href="index.jsp">Página Inicial</a> |
            <a href="saque.jsp">Saque</a> |
            <a href="depositar.jsp">Depositar</a> |
            <a href="encerrarConta.jsp">Encerrar Conta</a> |
        </nav>
    </footer>

    <script>
        // Definir o número da conta como uma variável JavaScript
        var numeroConta = "<%= numeroConta %>";
        
        // Fazer uma solicitação ao servlet ConsultarSaldoServlet
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // Atualiza o saldo na página com o valor retornado pelo servlet
                    document.getElementById("saldo").textContent = xhr.responseText;
                } else {
                    console.error("Erro ao obter saldo: " + xhr.status);
                }
            }
        };
        xhr.open("GET", "ConsultarSaldoServlet?numeroConta=" + numeroConta, true);
        xhr.send();
    </script>
</body>
</html>
