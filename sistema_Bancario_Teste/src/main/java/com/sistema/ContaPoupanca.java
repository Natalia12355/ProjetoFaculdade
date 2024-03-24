package com.sistema;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(String numeroConta, Pessoa pessoa, double saldo, String senha) {
        super(numeroConta, pessoa, "Poupança", saldo, senha);
    }
}
