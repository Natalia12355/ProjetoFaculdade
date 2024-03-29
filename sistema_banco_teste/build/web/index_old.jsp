<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="CSS/estilo.css" rel="stylesheet"
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<body>
    <div id="login">
        
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="" method="post">
                            <h3 class="text-center text-info">Login</h3>
                            <div class="form-group">
                                <label for="username" class="text-info">Usuário:</label><br>
                                <input type="text" name="txtusuario" id="txtusuario" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">Senha:</label><br>
                                <input type="text" name="txtsenha" id="txtsenha" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="remember-me" class="text-info"><span>Lembrar-me</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br>
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="Logar">
                            </div>
                            <div id="register-link" class="text-right">
                                <a href="#" class="text-info">Cadastre-se</a>
                            </div>
                        </form>
                        <footer>
                            <br>
                            <br>
                            <br>
    <a href="index.jsp">Página Inicial</a> |
    <a href="login.jsp">Acessar Conta</a> |
    <a href="consultarSaldo.jsp">Consultar Saldo</a> |
    <a href="saque.jsp">Saque</a> |
    <a href="depositar.jsp">Depositar</a> |
    <a href="encerrarConta.jsp">Encerrar Conta</a> |
    
</footer>
                    </div>
                </div>
            </div>
            <p align="center" class="text-light mt-2">
                <% 
            String usuario = request.getParameter("txtusuario");
            String senha = request.getParameter("txtsenha");
            
            
            
            if(usuario == null || senha == null){
                        out.println("Preencha os dados");
                        
                        }else{
               if(usuario.equals("Natalia") && senha.equals("123")) {
               response.sendRedirect("usuarios.jsp");
                    }else{
                    out.println("Dados incorretos");
                    }
            }
                 
        %>

            </p>
        </div>
    </div>
</body>

