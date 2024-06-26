<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro</title>
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
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"],
        input[type="password"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #004386;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #005aa7;
        }

        #confirmacaoCadastro {
            background-color: #d4edda;
            color: #155724;
            padding: 10px;
            margin-top: 10px;
            border-radius: 5px;
            display: none;
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
        <h1>Cadastro de Conta</h1>
    </header>
    <main>
        <form id="cadastroForm" action="/sistema_banco/ProcessarCadastroServlet" method="post">

            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" required>
            
            <label for="cpf">CPF:</label>
            <input type="text" id="cpf" name="cpf" required>
            
            <label for="endereco">Endere�o:</label>
            <input type="text" id="endereco" name="endereco" required>
            
            <label for="telefone">Telefone:</label>
            <input type="text" id="telefone" name="telefone" required>
            
            <label for="saldo">Saldo Inicial:</label>
            <input type="number" id="saldo" name="saldo" required>
            
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" required>
            
            <input type="submit" value="Cadastrar">
        </form>
        <div id="confirmacaoCadastro">
            <p>Cadastro realizado com sucesso!</p>
        </div>
    </main>
    <footer>
        <a href="index.jsp">P�gina Inicial</a> |
        <a href="login.jsp">Acessar Conta</a> |
        <a href="consultarSaldo.jsp">Consultar Saldo</a> |
        <a href="encerrarConta.jsp">Encerrar Conta</a> |
        <a href="saque.jsp">Saque</a>
    </footer>

   <script>
    document.getElementById("cadastroForm").addEventListener("submit", function(event) {
        event.preventDefault(); // Impede o envio do formul�rio

        var formData = new FormData(this);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "ProcessarCadastroServlet", true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById("confirmacaoCadastro").style.display = "block";
                document.getElementById("confirmacaoCadastro").innerHTML = xhr.responseText;
            }
        };
        xhr.send(formData);
    });
</script>

</body>
</html>
