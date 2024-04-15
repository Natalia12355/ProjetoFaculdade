<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Principal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f3f3f3;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #004386;
            text-align: center;
        }

        ul {
            list-style-type: none;
            padding: 0;
            margin: 20px 0;
            text-align: center;
        }

        li {
            margin-bottom: 10px;
        }

        button {
            background-color: #004386;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #005aa7;
        }

        a {
            color: #004386;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
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
        <h1>Menu Principal - Natbank</h1>
        <ul>
            <li><button onclick="depositar()">Depositar</button></li>
            <li><button onclick="sacar()">Sacar</button></li>
            <li><button onclick="contaAcessada()">Acessar Conta</button></li>
            <li><button onclick="encerrarConta()">Encerrar Conta</button></li>
            <li><a href="index.jsp">Voltar para pagina principal</a></li>
        </ul>
    </div>
    
    <script>
        function depositar() {
            // Implementação para chamar a página de depósito
            window.location.href = "depositar.jsp";
        }

        function sacar() {
            // Implementação para chamar a página de saque
            window.location.href = "sacar.jsp";
        }

        function consultarSaldo() {
            // Implementação para chamar a página de consulta de saldo
            window.location.href = "consultarSaldo.jsp";
        }

        function encerrarConta() {
            // Implementação para chamar a página de encerramento de conta
            window.location.href = "encerrarConta.jsp";
        }
    </script>
</body>
</html>
