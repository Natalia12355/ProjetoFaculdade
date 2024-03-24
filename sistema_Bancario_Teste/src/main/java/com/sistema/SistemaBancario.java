package com.sistema;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SistemaBancario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static SistemaBancario instancia;
    private List<Conta> contas;

    public SistemaBancario() {
        this.contas = new ArrayList<>();
    }

    public static SistemaBancario getInstance() {
        if (instancia == null) {
            instancia = new SistemaBancario();
        }
        return instancia;
    }

    public synchronized void cadastrarConta(String numeroConta, Pessoa pessoa, String tipoConta, double saldo, String senha) {
        Conta novaConta = null;
        if (tipoConta.equalsIgnoreCase("Corrente")) {
            novaConta = new ContaCorrente(numeroConta, pessoa, saldo, senha);
        } else if (tipoConta.equalsIgnoreCase("Poupança")) {
            novaConta = new ContaPoupanca(numeroConta, pessoa, saldo, senha);
        }
        if (novaConta != null) {
            contas.add(novaConta);
        }
    }

    public synchronized Conta acessarConta(String numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                return conta;
            }
        }
        return null;
    }

    public synchronized void encerrarConta(String numeroConta) {
        contas.removeIf(conta -> conta.getNumeroConta().equals(numeroConta));
    }

    // Métodos adicionais para realizar operações bancárias, como depositar, sacar, etc.
    // Você pode adicionar esses métodos conforme necessário para implementar as funcionalidades desejadas.
    public synchronized void depositar(String numeroConta, double valor) {
        Conta conta = acessarConta(numeroConta);
        if (conta != null) {
            conta.depositar(valor);
        }
    }

    public synchronized void sacar(String numeroConta, double valor) {
        Conta conta = acessarConta(numeroConta);
        if (conta != null) {
            conta.sacar(valor);
        }
    }

    public synchronized double consultarSaldo(String numeroConta) {
        Conta conta = acessarConta(numeroConta);
        return (conta != null) ? conta.getSaldo() : 0.0;
    }

    // Outros métodos para operações bancárias
    public void testarConexao() {
        String url = "jdbc:mysql://localhost:3306/projeto_banco";
        String usuario = "root";
        String senha = "natalia";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "SELECT 1";
            try (Statement statement = conexao.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    int resultado = resultSet.getInt(1);
                    System.out.println("Teste de conexão bem-sucedido. Resultado: " + resultado);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao testar a conexão: " + e.getMessage());
        }
    }
}
