// OperacaoBancaria.java
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
public interface OperacaoBancaria {
    public void depositar(double valor);    
    public boolean sacar(double valor); // Retorno alterado para boolean para indicar sucesso/falha
    public void imprimir();     
}