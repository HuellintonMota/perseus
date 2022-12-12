/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indioxd.perseus.core.entity;

import java.util.Objects;

/**
 *
 * @author heto1
 */
public class RecursoEntity {
    
    private Long codigo;
    private String nomeRecurso;
    private String caminhoTela;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNomeRecurso() {
        return nomeRecurso;
    }

    public void setNomeRecurso(String nomeRecurso) {
        this.nomeRecurso = nomeRecurso;
    }

    public String getCaminhoTela() {
        return caminhoTela;
    }

    public void setCaminhoTela(String caminhoTela) {
        this.caminhoTela = caminhoTela;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RecursoEntity other = (RecursoEntity) obj;
        return Objects.equals(this.codigo, other.codigo);
    }
    
    
    
}
