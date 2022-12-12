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
public class LiberacaoEntity {
    
    private Long codigo;
    private UsuarioEntity usuario;
    private GrupoUsuarioEntity grupoUsuario;
    private RecursoEntity recurso;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public GrupoUsuarioEntity getGrupoUsuario() {
        return grupoUsuario;
    }

    public void setGrupoUsuario(GrupoUsuarioEntity grupoUsuario) {
        this.grupoUsuario = grupoUsuario;
    }

    public RecursoEntity getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoEntity recurso) {
        this.recurso = recurso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.codigo);
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
        final LiberacaoEntity other = (LiberacaoEntity) obj;
        return Objects.equals(this.codigo, other.codigo);
    }
    
    
    
}
