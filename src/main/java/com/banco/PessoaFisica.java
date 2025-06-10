// PessoaFisica.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banco;
import java.util.ArrayList;

/**
 *
 * @author samara
 */
public class PessoaFisica extends Pessoa {
    private int cpf;

    // Construtor corrigido para chamar super(nome, listacontas)
    public PessoaFisica(String nome, ArrayList<Conta> listaContas, int cpf) {
        super(nome, listaContas);
        this.cpf = cpf;
    }

    // Getter para o atributo 'cpf'
    public int getCpf() {
        return this.cpf;
    }

    // Setter para o atributo 'cpf'
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
}