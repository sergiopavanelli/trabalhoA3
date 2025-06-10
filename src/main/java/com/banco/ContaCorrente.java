// ContaCorrente.java
package com.banco;

public class ContaCorrente extends Conta {
    private double limiteChequeEspecial;

    // Construtor principal
    public ContaCorrente(int numero, double saldoInicial, Agencia agencia, double limiteChequeEspecial) {
        super(numero, saldoInicial, agencia);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    // Construtor auxiliar sem limite de cheque especial (usa 0.0 como padrão)
    public ContaCorrente(int numero, double saldoInicial, Agencia agencia) {
        this(numero, saldoInicial, agencia, 0.0); // Chama o construtor principal
    }

    // Getter para o atributo 'limiteChequeEspecial'
    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    // Setter para o atributo 'limiteChequeEspecial'
    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    // Sobrescrita do método sacar para incluir o limite do cheque especial
    @Override
    public boolean sacar(double valor) {
        // Verifica se o valor é positivo e se o saldo disponível (saldo + limite) é suficiente
        if (valor > 0 && (getSaldo() + this.limiteChequeEspecial) >= valor) {
            setSaldo(getSaldo() - valor); // Atualiza o saldo usando o setter da classe pai
            System.out.println("Saque de " + valor + " realizado na Conta Corrente " + getNumero() + ". Saldo atual: " + getSaldo());
            return true;
        } else {
            // AJUSTE AQUI: Mude a mensagem para corresponder EXATAMENTE ao que o teste CTF03 espera.
            if (valor <= 0) { // Adiciona uma condição para mensagem de valor inválido
                System.out.println("Valor de saque inválido. O valor deve ser positivo.");
            } else { // Saldo insuficiente
                System.out.println("Saldo insuficiente para o valor do saque solicitado");
            }
            return false;
        }
    }

    @Override
    public void imprimir() {
        System.out.println("--- Dados da Conta Corrente ---");
        System.out.println("Número da Conta: " + getNumero());
        System.out.println("Agência: " + getAgencia().getNumero());
        System.out.println("Saldo: " + getSaldo());
        System.out.println("Limite de Cheque Especial: " + this.limiteChequeEspecial);
        System.out.println("--------------------------------");
    }
}