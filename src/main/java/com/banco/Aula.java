package com.banco;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Aula {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Random random = new Random();

        String nome;
        long cnpjLong, cpfLong;
        int numAgencia;
        PessoaJuridica p1 = null;
        PessoaFisica p2 = null;
        ArrayList<Conta> listaContasGeral = new ArrayList<>();

        Agencia ag1 = new Agencia(1);
        Agencia ag2 = new Agencia(2);
        Agencia ag3 = new Agencia(3);
        Agencia escolhaAgencia = null;

        // Seleção da agência
        while (true) {
            System.err.println("DEBUG: Tentando ler a AGÊNCIA..."); // DEBUG
            numAgencia = readInt(entrada, "Informe qual a agência você deseja criar conta. Agências disponíveis: 1, 2 e 3");

            if (numAgencia >= 1 && numAgencia <= 3) {
                switch (numAgencia) {
                    case 1:
                        escolhaAgencia = ag1;
                        break;
                    case 2:
                        escolhaAgencia = ag2;
                        break;
                    case 3:
                        escolhaAgencia = ag3;
                        break;
                }
                break;
            } else {
                System.out.println("Agência inválida. Por favor, digite 1, 2 ou 3.");
            }
        }

        System.out.println("Informe o nome da Pessoa:");
        System.err.println("DEBUG: Tentando ler o NOME..."); // DEBUG
        nome = entrada.nextLine();

        // Escolha do tipo de pessoa
        while (true) {
            System.err.println("DEBUG: Tentando ler OPÇÃO TIPO DE PESSOA..."); // DEBUG
            int opcao = readInt(entrada, "Deseja criar a conta para pessoa jurídica ou física? \nDigite \n1: Jurídica \n2: Física");

            if (opcao == 1 || opcao == 2) {
                ArrayList<Conta> contasDaPessoa = new ArrayList<>();

                if (opcao == 1) {
                    System.err.println("DEBUG: Tentando ler CNPJ..."); // DEBUG
                    cnpjLong = readLong(entrada, "Informe o CNPJ:");
                    p1 = new PessoaJuridica(nome, contasDaPessoa, (int) cnpjLong); // Casting to int might truncate, consider using long for CNPJ

                    criarContas(entrada, random, escolhaAgencia, p1, listaContasGeral);

                } else { // Pessoa Física
                    System.err.println("DEBUG: Tentando ler CPF..."); // DEBUG
                    cpfLong = readLong(entrada, "Informe o CPF:");
                    p2 = new PessoaFisica(nome, contasDaPessoa, (int) cpfLong); // Casting to int might truncate, consider using long for CPF

                    criarContas(entrada, random, escolhaAgencia, p2, listaContasGeral);
                }
                break;
            } else {
                System.out.println("Opção inválida. Por favor, digite 1 ou 2.");
            }
        }

        // entrada.close(); // NÃO FECHAR PARA TESTES JUNIT
    }

    // Método para criar contas corrente e poupança para PessoaJuridica ou PessoaFisica
private static void criarContas(Scanner entrada, Random random, Agencia agencia, Pessoa pessoa,
                                 ArrayList<Conta> listaContasGeral) {
    ContaCorrente c1;
    ContaPoupanca c2;
    double salario, rendimento;

    System.err.println("DEBUG: Tentando ler OPÇÃO CRIAR CONTA CORRENTE..."); // DEBUG
    int tipoConta = readInt(entrada, "Deseja criar conta corrente? Digite 1 - sim e 2 - não");

    if (tipoConta == 1) { // Este é o bloco ORIGINAL para criar Conta Corrente
        System.err.println("DEBUG: Tentando ler SALÁRIO..."); // DEBUG
        salario = readDouble(entrada, "Informe o salário:");

        c1 = new ContaCorrente(random.nextInt(1_000_000), salario, agencia);
        pessoa.addConta(c1);
        listaContasGeral.add(c1);
        System.out.println("O número da sua conta corrente é: " + c1.getNumero());
        System.out.println("O número da sua agência é: " + c1.getAgencia().getNumero());
        c1.imprimir();
    } // FIM DO BLOCO IF DA CONTA CORRENTE ORIGINAL

    // A próxima leitura é para a conta poupança, e ela deve vir APÓS o bloco da conta corrente.
    System.err.println("DEBUG: Tentando ler OPÇÃO CRIAR CONTA POUPANÇA..."); // DEBUG
    int tipoConta2 = readInt(entrada, "Deseja criar conta poupança? Digite 1 - sim e 2 - não");

    if (tipoConta2 == 1) {
        System.err.println("DEBUG: Tentando ler RENDIMENTO..."); // DEBUG
        rendimento = readDouble(entrada, "Informe o rendimento (ex: 0.005 para 0.5%):");

        c2 = new ContaPoupanca(random.nextInt(1_000_000), 0.0, agencia);
        c2.setRendimento(rendimento);
        pessoa.addConta(c2);
        listaContasGeral.add(c2);
        System.out.println("O número da sua conta poupança é: " + c2.getNumero());
        System.out.println("O número da sua agência é: " + c2.getAgencia().getNumero());
        c2.imprimir();
    }
}

    // --- Helper methods for robust input reading ---
    private static int readInt(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            try {
                int value = sc.nextInt();
                sc.nextLine(); // Consume the rest of the line
                System.err.println("DEBUG: LIDO INT: " + value); // DEBUG
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                sc.nextLine(); // Discard the incorrect input
            }
        }
    }

    private static long readLong(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            try {
                long value = sc.nextLong();
                sc.nextLine(); // Consume the rest of the line
                System.err.println("DEBUG: LIDO LONG: " + value); // DEBUG
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número longo.");
                sc.nextLine(); // Discard the incorrect input
            }
        }
    }

    private static double readDouble(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            try {
                double value = sc.nextDouble();
                sc.nextLine(); // Consume the rest of the line
                System.err.println("DEBUG: LIDO DOUBLE: " + value); // DEBUG
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número decimal.");
                sc.nextLine(); // Discard the incorrect input
            }
        }
    }
}