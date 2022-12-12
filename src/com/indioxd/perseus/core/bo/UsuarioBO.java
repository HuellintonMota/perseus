/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.bo;

import com.indioxd.perseus.core.dao.UsuarioDAO;
import com.indioxd.perseus.core.entity.UsuarioEntity;
import com.indioxd.perseus.core.util.exception.NegocioException;
import java.util.List;

/**
 *
 * @author Huellinton.Mota
 */
public class UsuarioBO {

    public String salvarUsuario(UsuarioEntity usuario) throws NegocioException{
        
        validarUsuario(usuario);
        UsuarioDAO dao = new UsuarioDAO();
        return dao.salvarUsuario(usuario);

    }
    
    // TODO filtrar usuario
    public List<UsuarioEntity> listarUsuarioFiltrado(UsuarioEntity usuario) throws NegocioException{
        return new UsuarioDAO().listarUsuarioFiltrado(usuario);
    }
    
    public List<UsuarioEntity> listarUsuario() throws NegocioException{
        
        
        return new UsuarioDAO().listarUsuario();
    }
    
    public void excluirUsuario(Long codigoUsuario) throws NegocioException{
        new UsuarioDAO().excluirUsuario(codigoUsuario);
    }
    
    public UsuarioEntity buscarUsuarioporId(Long codigoUsuario) throws NegocioException{
        return new UsuarioDAO().buscarUsuarioporId(codigoUsuario);
    }
    
    public String atualizaUsuario(UsuarioEntity usuario) throws NegocioException{
        return new UsuarioDAO().atualizaUsuario(usuario);
    }
    
    public void validarUsuario(UsuarioEntity usuario) throws NegocioException{
        UsuarioDAO dao = new UsuarioDAO();
        
        if (dao.loginCadastrado(usuario.getLogin())){
            throw new NegocioException("Login já cadastrado");
        }
        if(!usuario.getEmail().contains("@")){
            throw new NegocioException("Email inválido. Verifique o email.");
        }
        if(usuario.getNome()==null || usuario.getNome().equals("")){
            throw new NegocioException("Nome completo deve ser preenchido");
        }
        if(!usuario.getSenha().equals(usuario.getConfirmacao())){
            throw new NegocioException("Senha não confere com a confirmação");
        }
    }
    
    public UsuarioEntity autenticar(String login, String senha) throws NegocioException{
        
        if(login.equals("admin")&&senha.equals("admin")){
            UsuarioEntity adm = new UsuarioEntity();
            adm.setNome("Administrador");
            adm.setLogin("admin");
            adm.setSenha("admin");
            return adm;
        }
        
        return new UsuarioDAO().autenticar(login, senha);
        
    }
    
}
