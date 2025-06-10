// PessoaJuridica.java
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
public class PessoaJuridica extends Pessoa{
    private int cnpj;

    // Construtor
    public PessoaJuridica(String nome, ArrayList<Conta> listaContas, int cnpj){
        super(nome, listaContas);
        this.cnpj = cnpj;
    }

    // Getter para o atributo 'cnpj'
    public int getCnpj(){
        return this.cnpj;
    }

    // Setter para o atributo 'cnpj'
    public void setCnpj(int cnpj){
        this.cnpj = cnpj;
    }
}