<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>P�gina Inicial do Banco</title>
      <link rel="stylesheet" type="text/css" href="styles.css">
      
       <style>
        /* Estilos espec�ficos para o layout da p�gina index */
        body {
            background-color: #ffffff; /* branco */
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .header {
            background-color: #005aa7; /* azul escuro */
            color: #ffffff; /* branco */
            padding: 20px;
            text-align: center;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        .main-content {
            background-color: #f9f9f9; /* cinza claro */
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .button {
            background-color: #005aa7; /* azul escuro */
            color: #ffffff; /* branco */
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }

        .button:hover {
            background-color: #003366; /* azul mais escuro no hover */
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }
    </style>
      
</head>
<body>
    <div class="header">
        <h1>Bem-vindo ao Nosso Banco NatBank</h1>
    </div>
     <div class="container">
        <div class="main-content">
            <h2>Selecione uma op��o:</h2>
            <div class="button-container">
                <a href="usuarios.jsp" class="button">Crie uma Conta</a>
                <a href="login.jsp" class="button">Acessar Conta</a>
            </div>
        </div>
    </div>
    <main>
        <p>Seja bem-vindo ao nosso banco virtual. Fa�a login ou cadastre-se para acessar sua conta.</p>
    </main>
    <footer>
        <p>&copy; 2024 Banco Virtual. Todos os direitos reservados.</p>
    </footer>
</body>
</html>
