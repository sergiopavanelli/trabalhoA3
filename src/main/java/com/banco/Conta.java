// Conta.java
package com.banco;

/**
 *
 * @author samara
 */
public abstract class Conta implements OperacaoBancaria {
    private int numero;
    private double saldo; // Mantido como private
    private Agencia agencia;

    public Conta(int numero, double saldoInicial, Agencia agencia) {
        this.numero = numero;
        this.saldo = saldoInicial;
        this.agencia = agencia;
    }

    // Construtor auxiliar se quiser iniciar com saldo zero
    public Conta(int numero, Agencia agencia) {
        this(numero, 0.0, agencia);
    }

    // Getter para numero
    public int getNumero() {
        return numero;
    }

    // Setter para numero (se necessário)
    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Getter para saldo
    public double getSaldo() {
        return saldo;
    }

    // Setter para saldo (use com cautela, prefira depositar/sacar)
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Getter para agencia
    public Agencia getAgencia() {
        return agencia;
    }

    // Setter para agencia (se necessário)
    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    // Métodos da interface OperacaoBancaria (implementação básica, pode ser sobrescrita nas subclasses)
    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            // Opcional: System.out.println("Depósito de " + valor + " realizado. Saldo atual: " + this.saldo);
        } else {
            // AJUSTE AQUI: Mude a mensagem para corresponder EXATAMENTE ao que o teste CTF04 espera.
            System.out.println("Valor inválido para depósito.");
        }
    }

    @Override
    public boolean sacar(double valor) { // Tipo de retorno alterado para boolean
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            // Opcional: System.out.println("Saque de " + valor + " realizado. Saldo atual: " + this.saldo);
            return true;
        } else {
            // Se o saque for negativo/zero, você pode querer uma mensagem específica.
            // Para o CTF03, a ContaCorrente já sobrescreve e lida com isso.
            if (valor <= 0) {
                System.out.println("Valor de saque inválido. O valor deve ser positivo.");
            } else {
                System.out.println("Saldo insuficiente ou valor de saque inválido."); // Esta é a mensagem padrão da Conta, CC sobrescreve.
            }
            return false;
        }
    }
    
    // Método imprimir precisa ser abstrato ou ter uma implementação padrão
    @Override
    public abstract void imprimir(); // Já que ContaCorrente o implementa, Conta deve ter um abstract
}