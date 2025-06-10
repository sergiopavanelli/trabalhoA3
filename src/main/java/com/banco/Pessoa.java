// Pessoa.java
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
public abstract class Pessoa {
    private String nome;
    private ArrayList<Conta> listaContas; // Renomeado para seguir convenção de nomes (camelCase)

    // Construtor corrigido para inicializar 'nome' e 'listaContas'
    public Pessoa(String nome, ArrayList<Conta> listaContas) {
        this.nome = nome;
        this.listaContas = listaContas; // Assume que a lista já vem inicializada
    }

    // Getter para o atributo 'nome'
    public String getNome() {
        return nome;
    }

    // Setter para o atributo 'nome'
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para o atributo 'listaContas'
    public ArrayList<Conta> getListaContas() {
        return this.listaContas;
    }

    // Setter para o atributo 'listaContas' (para substituir a lista inteira, se necessário)
    // Este setter precisa receber uma ArrayList de Conta, não uma única ContaPoupanca
    public void setListaContas(ArrayList<Conta> listaContas) {
        this.listaContas = listaContas;
    }

    // Método para adicionar uma única conta à lista
    public void addConta(Conta conta) {
        if (this.listaContas == null) { // Garante que a lista esteja inicializada
            this.listaContas = new ArrayList<>();
        }
        this.listaContas.add(conta);
    }
}