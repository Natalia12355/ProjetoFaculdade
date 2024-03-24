package com.sistema;

public class ContaCorrente extends Conta {
    public ContaCorrente(String numeroConta, Pessoa pessoa, double saldo, String senha) {
        super(numeroConta, pessoa, "Corrente", saldo, senha);
    }
}
