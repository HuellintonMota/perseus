/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.util.exception;

import javax.swing.JOptionPane;

/**
 *
 * @author Huellinton.Mota
 */
public class NegocioException extends Exception {
    
    
    private String mensagemDeErro;
    private String comandoExecutado;
    
    public NegocioException(String exception){
        this.mensagemDeErro = exception;
    }
    
    public NegocioException(String comando, String exception){
        this.mensagemDeErro = exception;
        this.comandoExecutado = comando;
    }

    public String getMensagemDeErro() {
        return mensagemDeErro;
    }

    public void setMensagemDeErro(String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }

    public String getComandoExecutado() {
        return comandoExecutado;
    }

    public void setComandoExecutado(String comandoExecutado) {
        this.comandoExecutado = comandoExecutado;
    }
    
    public void ExibeErro(){
        JOptionPane.showMessageDialog(null, "Comando Executado: \n"+getComandoExecutado()+
                "\n\n-------------------\nErro retornardo:\n"+getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

}
