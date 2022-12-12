/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.service;

import com.indioxd.perseus.core.bo.UsuarioBO;
import com.indioxd.perseus.core.entity.UsuarioEntity;
import com.indioxd.perseus.core.util.exception.NegocioException;
import java.util.List;

/**
 *
 * @author Huellinton.Mota
 */
public class UsuarioService {
    
    public String salvarUsuario(UsuarioEntity usuario) throws NegocioException{
        UsuarioBO bo = new UsuarioBO();
        
        return bo.salvarUsuario(usuario);
    }
    
    public List<UsuarioEntity> listarUsuario() throws NegocioException{
        return new UsuarioBO().listarUsuario();
    }
    
    public void excluirUsuario(Long codigoUsuario) throws NegocioException{
        new UsuarioBO().excluirUsuario(codigoUsuario);
    }
    
    public UsuarioEntity buscarUsuarioporId(Long codigoUsuario) throws NegocioException{
        return new UsuarioBO().buscarUsuarioporId(codigoUsuario);
        
    }

    public String atualizaUsuario(UsuarioEntity usuario) throws NegocioException{
        return new UsuarioBO().atualizaUsuario(usuario);
    }
    
    // TODO usuario filtrado
    public List<UsuarioEntity> listarUsuarioFiltrado(UsuarioEntity usuario) throws NegocioException{
        return new UsuarioBO().listarUsuarioFiltrado(usuario);
    }
    
    public UsuarioEntity autenticar(String login, String senha) throws NegocioException{
        return new UsuarioBO().autenticar(login, senha);
    }
    
}
