# 🏦 Sistema de Gestão de Contas Bancárias

Este repositório contém a implementação de um sistema simples de **gestão de contas bancárias em Java**.
O projeto foi desenvolvido como atividade final de uma disciplina e demonstra conceitos de:

- Programação Orientada a Objetos (POO)
- Herança, polimorfismo e encapsulamento
- Testes de software com JUnit
- Cobertura de código com JaCoCo

---

## 📌 Descrição do Programa

O sistema simula um ambiente bancário básico onde o usuário pode **cadastrar clientes** e **criar contas bancárias**.

### Funcionalidades principais

-   **Cadastro de Pessoas**
    -   Pessoa Física (com CPF)
    -   Pessoa Jurídica (com CNPJ)
        Ambas herdam de uma classe abstrata `Pessoa`.
-   **Criação de Contas**
    -   `ContaCorrente` e `ContaPoupanca`, que herdam de `Conta` e implementam a interface `OperacaoBancaria`.
-   **Agências Bancárias**
    -   As contas são vinculadas a uma das **três agências pré-definidas**.
-   **Operações Bancárias**
    -   Suporte a **depósito** e **saque** para ambos os tipos de conta.
-   **Geração Automática de Número de Conta**
    -   Os números das contas são gerados aleatoriamente.
-   **Interface de Console**
    -   A interação com o usuário é realizada por meio de menus no terminal.

---

## 📘 Atividades Realizadas (Trabalho Final da Disciplina)

### ✅ Implementações e Correções

-   Inclusão de métodos `get` e `set` para os atributos relevantes.
-   Correção de:
    -   Construtores
    -   Retornos de métodos
    -   Assinatura da interface `OperacaoBancaria`

### 🧪 Plano de Testes Funcionais

Foram elaborados **6 casos de teste funcionais** para validar o comportamento do sistema através da interface de console. O objetivo é simular interações do usuário e verificar se as operações são executadas conforme o esperado.

1.  **Cenário:** Cadastro de Pessoa Física e Criação de Conta Corrente
    * **Passos:**
        1.  Acessar o menu de cadastro de clientes.
        2.  Selecionar a opção para Pessoa Física.
        3.  Informar dados válidos (nome, CPF, etc.).
        4.  Acessar o menu de criação de contas.
        5.  Selecionar a opção para Conta Corrente.
        6.  Vincular ao cliente recém-cadastrado e a uma agência.
    * **Resultado Esperado:** Mensagem de sucesso no cadastro do cliente e na criação da conta. A conta deve ser listada corretamente.

2.  **Cenário:** Cadastro de Pessoa Jurídica e Criação de Conta Poupança
    * **Passos:**
        1.  Acessar o menu de cadastro de clientes.
        2.  Selecionar a opção para Pessoa Jurídica.
        3.  Informar dados válidos (razão social, CNPJ, etc.).
        4.  Acessar o menu de criação de contas.
        5.  Selecionar a opção para Conta Poupança.
        6.  Vincular ao cliente recém-cadastrado e a uma agência.
    * **Resultado Esperado:** Mensagem de sucesso no cadastro do cliente e na criação da conta. A conta deve ser listada corretamente.

3.  **Cenário:** Realização de Depósito em Conta Corrente
    * **Passos:**
        1.  Criar uma Conta Corrente (se ainda não existir).
        2.  Acessar o menu de operações bancárias.
        3.  Selecionar a opção de depósito.
        4.  Informar o número da conta e um valor positivo para depósito.
    * **Resultado Esperado:** Mensagem de sucesso no depósito e o saldo da conta deve ser atualizado corretamente.

4.  **Cenário:** Realização de Saque em Conta Poupança (saldo suficiente)
    * **Passos:**
        1.  Criar uma Conta Poupança (se ainda não existir) e depositar um valor inicial.
        2.  Acessar o menu de operações bancárias.
        3.  Selecionar a opção de saque.
        4.  Informar o número da conta e um valor para saque menor ou igual ao saldo disponível.
    * **Resultado Esperado:** Mensagem de sucesso no saque e o saldo da conta deve ser atualizado corretamente.

5.  **Cenário:** Tentativa de Saque em Conta Corrente (saldo insuficiente)
    * **Passos:**
        1.  Criar uma Conta Corrente (se ainda não existir) com um saldo baixo.
        2.  Acessar o menu de operações bancárias.
        3.  Selecionar a opção de saque.
        4.  Informar o número da conta e um valor para saque maior que o saldo disponível.
    * **Resultado Esperado:** Mensagem de erro informando saldo insuficiente e o saldo da conta deve permanecer inalterado.

6.  **Cenário:** Listagem de Contas e Clientes
    * **Passos:**
        1.  Cadastrar pelo menos um cliente (PF ou PJ) e criar pelo menos uma conta.
        2.  Acessar o menu de listagem de clientes.
        3.  Acessar o menu de listagem de contas.
    * **Resultado Esperado:** Todos os clientes e contas cadastrados devem ser exibidos corretamente no console.

### 🧷 Testes Unitários (JUnit) e Cobertura de Código (JaCoCo)

-   Classe de teste: `ContaTest.java`, com **7 casos de teste** unitários.
-   Framework utilizado: **JUnit 5**
-   Ferramenta de cobertura: **JaCoCo**
-   Relatório gerado via Maven em `target/site/jacoco/index.html`

### 🛠️ Otimizações e Refatorações

Foram realizadas **5 melhorias** principais:
1.  **Extração de métodos** auxiliares para lógica repetitiva
2.  **Renomeação de variáveis** para maior clareza
3.  **Remoção de código morto**
4.  **Uso de operador ternário** em substituição a condicionais simples
5.  **Eliminação de duplicações**

---

## ▶️ Como Executar o Projeto (com Maven)

Este projeto é configurado com Maven. Certifique-se de ter o Maven instalado em sua máquina.

### 1. Clone o Repositório

```bash
git clone [https://github.com/sergiopavanelli/trabalhoA3.git](https://github.com/sergiopavanelli/trabalhoA3.git)