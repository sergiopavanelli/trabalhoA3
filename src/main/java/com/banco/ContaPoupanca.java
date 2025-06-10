// ContaPoupanca.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banco;

/**
 *
 * @author samara
 */
public class ContaPoupanca extends Conta{
    private double rendimento;

    public ContaPoupanca(int numero, double saldoInicial, Agencia agencia){ // Adicionado saldoInicial
        super(numero, saldoInicial, agencia); // Chamando o construtor de Conta com saldo inicial
        // O rendimento em si não é o saldo inicial, mas um fator de cálculo futuro.
        // Se a intenção era que o rendimento fosse o saldo inicial, o design pode precisar ser repensado.
        // Mantendo 'rendimento' como um atributo separado para representar a taxa ou fator de rendimento.
    }

    public ContaPoupanca(int numero, Agencia agencia) { // Construtor auxiliar sem rendimento inicial, chama o outro com 0.0 de saldo
        super(numero, 0.0, agencia);
    }
    
    @Override
    public void imprimir() {
        System.out.println("--- Dados da Conta Poupança ---");
        System.out.println("Número da Conta: " + getNumero());
        System.out.println("Agência: " + getAgencia().getNumero());
        System.out.println("Saldo: " + getSaldo());
        System.out.println("Rendimento (taxa/fator): " + this.rendimento);
        System.out.println("--------------------------------");
    }

    // Getter for rendimento
    public double getRendimento(){
        return this.rendimento;
    }

    // Setter for rendimento
    public void setRendimento(double rendimento){
        this.rendimento = rendimento;
    }
}