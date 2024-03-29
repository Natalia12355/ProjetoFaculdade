

import java.io.Serializable;

public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String numeroConta;
    private Pessoa pessoa;
    private String tipo;
    private double saldo;
    private String senha;

    public Conta(String numeroConta, Pessoa pessoa, String tipo, double saldo, String senha) {
        this.numeroConta = numeroConta;
        this.pessoa = pessoa;
        this.tipo = tipo;
        this.saldo = saldo;
        this.senha = senha;
    }

    // Getters e Setters
    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Métodos adicionais para operações na conta (depositar, sacar, etc.)
    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        saldo -= valor;
    }

    public void encerrarConta() {
        // Implemente a lógica para encerrar a conta aqui
    }
}
