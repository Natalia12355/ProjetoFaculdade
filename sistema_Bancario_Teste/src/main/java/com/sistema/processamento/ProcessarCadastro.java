package com.sistema.processamento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProcessarCadastro {

    // Método para cadastrar um novo cliente no banco de dados
    public void cadastrarCliente(String nome, String cpf, String endereco, String telefone, double saldo, String senha) {
        // Definir as informações de conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/projeto_banco"; // URL do banco de dados
        String usuario = "natalia"; // Nome de usuário do banco de dados
        String senhaBanco = "natalia"; // Senha do banco de dados

        // Definir a query SQL para inserir um novo cliente
        String sql = "INSERT INTO cliente (nome, cpf, endereco, telefone, saldo, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            // Estabelecer a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection(url, usuario, senhaBanco);
            // Preparar a declaração SQL para inserir os dados do cliente
            PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            // Definir os parâmetros da declaração SQL com os dados do cliente
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, endereco);
            stmt.setString(4, telefone);
            stmt.setDouble(5, saldo);
            stmt.setString(6, senha);

            // Executar a inserção dos dados do cliente
            int linhasAfetadas = stmt.executeUpdate();

            // Verificar se a inserção foi bem-sucedida
            if (linhasAfetadas > 0) {
                System.out.println("Cadastro realizado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar cliente.");
            }
        } catch (SQLException e) {
            // Lidar com erros de SQL
            e.printStackTrace();
        }
    }
}
