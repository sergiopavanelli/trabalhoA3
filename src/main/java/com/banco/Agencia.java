// Agencia.java
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
public class Agencia {
    private int numero;

    public Agencia(int numero){
        this.numero = numero;
    }

    // Getter para 'numero'
    public int getNumero() {
        return numero;
    }

    // Setter para 'numero'
    public void setNumero(int numero) {
        this.numero = numero;
    }
}