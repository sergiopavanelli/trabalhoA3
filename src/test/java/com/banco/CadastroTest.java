package com.banco;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CadastroTest {

    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    // --- CTF01: Cadastrar Pessoa Física e Conta Corrente com Depósito Inicial ---
    @Test
    void CTF01_cadastrarPessoaFisicaContaCorrenteComDepositoInicial() {
        String simulatedInput = "1\nJoão Silva\n2\n12345678901\n1\n1000,00\n2\n\n\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Aula.main(new String[]{});
        String consoleOutput = outputStreamCaptor.toString().trim();

        assertTrue(consoleOutput.contains("Informe qual a agência"), "Deve pedir a agência.");
        assertTrue(consoleOutput.contains("Informe o nome da Pessoa"), "Deve pedir o nome.");
        assertTrue(consoleOutput.contains("Deseja criar a conta para pessoa jurídica ou física?"), "Deve pedir o tipo de pessoa.");
        assertTrue(consoleOutput.contains("Informe o CPF"), "Deve pedir o CPF.");
        assertTrue(consoleOutput.contains("Deseja criar conta corrente?"), "Deve perguntar sobre Conta Corrente.");
        assertTrue(consoleOutput.contains("Informe o salário"), "Deve pedir o salário.");
        assertTrue(consoleOutput.contains("Deseja criar conta poupança?"), "Deve perguntar sobre Conta Poupança.");
        assertTrue(consoleOutput.contains("O número da sua agência é: 1"), "Deve confirmar a agência 1.");
        assertTrue(consoleOutput.contains("Saldo: 1000.0"), "Deve exibir o saldo inicial de 1000.0.");
        assertFalse(consoleOutput.contains("Informe o rendimento"), "Não deve pedir rendimento da poupança.");
    }

    // --- CTF02: Cadastrar Pessoa Jurídica e Conta Poupança ---
    @Test
    void CTF02_cadastrarPessoaJuridicaContaPoupanca() {
        String simulatedInput = "2\nEmpresa X\n1\n12345678000199\n2\n1\n0,005\n\n\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Aula.main(new String[]{});
        String consoleOutput = outputStreamCaptor.toString().trim();

        assertTrue(consoleOutput.contains("Informe qual a agência"), "Deve pedir a agência.");
        assertTrue(consoleOutput.contains("Informe o nome da Pessoa"), "Deve pedir o nome.");
        assertTrue(consoleOutput.contains("Deseja criar a conta para pessoa jurídica ou física?"), "Deve pedir o tipo de pessoa.");
        assertTrue(consoleOutput.contains("Informe o CNPJ"), "Deve pedir o CNPJ.");
        assertTrue(consoleOutput.contains("Deseja criar conta corrente?"), "Deve perguntar sobre Conta Corrente.");
        assertTrue(consoleOutput.contains("Deseja criar conta poupança?"), "Deve perguntar sobre Conta Poupança.");
        assertTrue(consoleOutput.contains("Informe o rendimento (ex: 0.005 para 0.5%):"), "Deve pedir o rendimento da poupança.");
        assertTrue(consoleOutput.contains("O número da sua agência é: 2"), "Deve confirmar a agência 2.");
        assertTrue(consoleOutput.contains("O número da sua conta poupança é:"), "Deve informar o número da conta poupança criada.");
        assertTrue(consoleOutput.contains("Saldo: 0.0"), "A conta poupança deve começar com saldo 0.0.");
        assertFalse(consoleOutput.contains("Informe o salário"), "Não deve pedir salário (não criou CC).");
        assertFalse(consoleOutput.contains("O número da sua conta corrente é:"), "Não deve informar número da CC.");
    }

    // --- CTF03: Tentar sacar valor maior que o saldo disponível ---
    @Test
    void CTF03_tentarSacarValorMaiorQueSaldoContaCorrente() {
        Agencia agencia = new Agencia(1);
        ContaCorrente conta = new ContaCorrente(12345, 500.0, agencia, 0.0);
        double valorSaque = 600.0;
        
        ByteArrayOutputStream tempOutputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(tempOutputStreamCaptor));

        boolean saqueRealizado = conta.sacar(valorSaque);

        System.setOut(originalSystemOut);
        String capturedOutput = tempOutputStreamCaptor.toString().trim();

        assertFalse(saqueRealizado, "O saque não deve ser realizado com saldo insuficiente.");
        assertEquals(500.0, conta.getSaldo(), 0.001, "O saldo da conta deve permanecer inalterado.");
        assertTrue(capturedOutput.contains("Saldo insuficiente para o valor do saque solicitado"),
                   "Deve exibir a mensagem de saldo insuficiente.");
    }

    // --- CTF04: Tentar depositar valor negativo ---
    @Test
    void CTF04_tentarDepositarValorNegativoContaCorrente() {
        Agencia agencia = new Agencia(1);
        double saldoInicial = 200.0;
        ContaCorrente conta = new ContaCorrente(54321, saldoInicial, agencia, 0.0);
        
        double valorDepositoInvalido = -100.0;

        ByteArrayOutputStream tempOutputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(tempOutputStreamCaptor));

        conta.depositar(valorDepositoInvalido);

        System.setOut(originalSystemOut);
        String capturedOutput = tempOutputStreamCaptor.toString().trim();

        assertEquals(saldoInicial, conta.getSaldo(), 0.001, "O saldo da conta não deve ser alterado por depósito inválido.");
        assertTrue(capturedOutput.contains("Valor inválido para depósito."),
                   "Deve exibir a mensagem de valor inválido para depósito.");
    }

    // --- CTF05: Criar Múltiplas Contas para a Mesma Pessoa ---
    @Test
    void CTF05_criarMultiplasContasParaMesmaPessoaFisica() {
        String simulatedInput = "1\nAna Paula Silva\n2\n10987654321\n1\n2500,00\n1\n0,004\n\n\n";

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Aula.main(new String[]{});
        String consoleOutput = outputStreamCaptor.toString().trim();

        assertTrue(consoleOutput.contains("Informe qual a agência"), "Deve pedir a agência.");
        assertTrue(consoleOutput.contains("Informe o nome da Pessoa"), "Deve pedir o nome da Pessoa.");
        assertTrue(consoleOutput.contains("Deseja criar a conta para pessoa jurídica ou física?"), "Deve pedir o tipo de pessoa.");
        assertTrue(consoleOutput.contains("Informe o CPF"), "Deve pedir o CPF.");
        assertTrue(consoleOutput.contains("Deseja criar conta corrente? Digite 1 - sim e 2 - não"), "Deve perguntar sobre a Conta Corrente.");
        assertTrue(consoleOutput.contains("Informe o salário"), "Deve pedir o salário para a Conta Corrente.");
        assertTrue(consoleOutput.contains("O número da sua conta corrente é:"), "Deve informar o número da Conta Corrente.");
        assertTrue(consoleOutput.contains("O número da sua agência é: 1"), "Deve confirmar a agência 1 para a Conta Corrente.");
        assertTrue(consoleOutput.contains("Saldo: 2500.0"), "Deve exibir o saldo inicial de 2500.0 para a Conta Corrente.");
        assertTrue(consoleOutput.contains("Deseja criar conta poupança? Digite 1 - sim e 2 - não"), "Deve perguntar sobre a Conta Poupança.");
        assertTrue(consoleOutput.contains("Informe o rendimento (ex: 0.005 para 0.5%):"), "Deve pedir o rendimento para a Conta Poupança.");
        assertTrue(consoleOutput.contains("O número da sua conta poupança é:"), "Deve informar o número da Conta Poupança.");
        assertTrue(consoleOutput.contains("O número da sua agência é: 1"), "Deve confirmar a agência 1 para a Conta Poupança.");
        assertTrue(consoleOutput.contains("Saldo: 0.0"), "Deve exibir o saldo inicial de 0.0 para a Conta Poupança.");
        assertTrue(consoleOutput.contains("Rendimento (taxa/fator): 0.004"), "Deve exibir o rendimento configurado para a Conta Poupança.");
        assertFalse(consoleOutput.contains("Agência inválida."), "Não deve exibir erro de agência inválida.");
        assertFalse(consoleOutput.contains("Opção inválida."), "Não deve exibir erro de opção inválida.");
        assertFalse(consoleOutput.contains("Valor inválido para depósito."), "Não deve exibir erro de depósito inválido.");
        assertFalse(consoleOutput.contains("Saldo insuficiente para o valor do saque solicitado"), "Não deve exibir erro de saque insuficiente.");
    }

    // --- NOVO TESTE: CTF06: Tentar escolher agência inexistente ---
    @Test
    void CTF06_tentarEscolherAgenciaInexistente() {
        // Sequência de entrada para simular uma agência inválida e depois corrigir
        // 1. Agência: 4 (inválida)
        // 2. Agência: 1 (válida, para continuar o fluxo)
        // 3. Nome: Teste Agência
        // 4. Tipo Pessoa: 2 (Física)
        // 5. Criar CC: 2 (não)
        // 6. Criar CP: 2 (não)
        String simulatedInput = "4\n1\nTeste Agência\n2\n98765432109\n2\n2\n\n\n";

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Aula.main(new String[]{});

        String consoleOutput = outputStreamCaptor.toString().trim();

        // --- Asserções para verificar o comportamento esperado ---

        // Deve conter a mensagem de erro da agência inválida
        assertTrue(consoleOutput.contains("Agência inválida. Por favor, digite 1, 2 ou 3."),
                   "Deve exibir a mensagem de erro para agência inválida.");
        
        // Deve conter o prompt da agência novamente (indicando que o loop de validação funcionou)
        // Note: o prompt da agência aparecerá DUAS VEZES na saída: uma para a primeira tentativa e outra para a segunda.
        int countAgencyPrompts = countOccurrences(consoleOutput, "Informe qual a agência você deseja criar conta. Agências disponíveis: 1, 2 e 3");
        assertTrue(countAgencyPrompts >= 2, "O prompt da agência deve aparecer pelo menos duas vezes.");

        // Deve prosseguir com o fluxo normal após a correção
        assertTrue(consoleOutput.contains("Informe o nome da Pessoa:"), "Deve pedir o nome após a correção da agência.");
        assertTrue(consoleOutput.contains("Deseja criar a conta para pessoa jurídica ou física?"), "Deve prosseguir para pedir o tipo de pessoa.");

        // Deve indicar que o processo terminou sem criar contas (se as opções finais foram '2' para não)
        assertFalse(consoleOutput.contains("O número da sua conta corrente é:"), "Não deve criar conta corrente.");
        assertFalse(consoleOutput.contains("O número da sua conta poupança é:"), "Não deve criar conta poupança.");

        // Verificação de Ausência de Outros Erros Graves
        assertFalse(consoleOutput.contains("Entrada inválida."), "Não deve exibir erro de entrada inválida (geral).");
        assertFalse(consoleOutput.contains("NoSuchElementException"), "Não deve ocorrer NoSuchElementException.");

        System.err.println("\n--- Saída Capturada para CTF06 (DEBUG) ---");
        System.err.println(consoleOutput);
        System.err.println("-------------------------------------------\n");
    }

    // Helper method to count string occurrences (useful for verifying repeated prompts)
    private int countOccurrences(String text, String subtext) {
        int count = 0;
        int lastIndex = 0;
        while ((lastIndex = text.indexOf(subtext, lastIndex)) != -1) {
            count++;
            lastIndex += subtext.length();
        }
        return count;
    }
}