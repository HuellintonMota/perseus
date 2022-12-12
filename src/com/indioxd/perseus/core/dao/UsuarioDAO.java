/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.dao;

import com.indioxd.perseus.core.dao.connection.ConexaoMySQL;
import com.indioxd.perseus.core.entity.UsuarioEntity;
import com.indioxd.perseus.core.util.exception.NegocioException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huellinton.Mota
 */
public class UsuarioDAO {
    
    public String salvarUsuario(UsuarioEntity usuario) throws NegocioException{
        String sql = "INSERT INTO usuario (nome_usuario, login_usuario, senha_usuario, email_usuario) VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""), ex.getLocalizedMessage());
        }finally{
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                    throw new NegocioException(ex.getLocalizedMessage());
                }
            }
        }
        
        return "Usuário cadastrado com sucesso!";
    }
    
    //TODO - filtrar usuario
    
    public List<UsuarioEntity> listarUsuarioFiltrado(UsuarioEntity usuario) throws NegocioException{
        String sql = "SELECT id_usuario, nome_usuario, login_usuario, senha_usuario, email_usuario FROM usuario";
        boolean adicionaWhere = true;
        
        if(usuario!=null){
            if(usuario.getCodigo()!=null){
                adicionaWhere = false;
                sql += " WHERE ";
                sql += " id_usuario = ? ";
            }
            if(usuario.getNome()!=null && !usuario.getNome().equals("")){
                if(adicionaWhere){
                    sql += " WHERE ";
                    adicionaWhere = false;
                }else{
                    sql += " AND ";
                }
                sql += " nome_usuario LIKE ?";
            }
            if(usuario.getLogin()!=null && !usuario.getLogin().equals("")){
                if(adicionaWhere){
                    sql += " WHERE ";
                    adicionaWhere = false;
                }else{
                    sql += " AND ";
                }
                sql += " login_usuario LIKE ?";
            }
            if(usuario.getEmail()!=null && !usuario.getEmail().equals("")){
                if(adicionaWhere){
                    sql += " WHERE ";
                    adicionaWhere = false;
                }else{
                    sql += " AND ";
                }
                sql += " email_usuario LIKE ?";
            }
        }
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>();
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            int indice = 0;
            
            if(usuario!=null){
                if(usuario.getCodigo()!=null){
                    indice++;
                    ps.setLong(indice, usuario.getCodigo());
                }if(usuario.getNome()!=null && !usuario.getNome().equals("")){
                    indice++;
                    ps.setString(indice, usuario.getNome()+"%");
                }
                if(usuario.getLogin()!=null && !usuario.getLogin().equals("")){
                    indice++;
                    ps.setString(indice, usuario.getLogin()+"%");
                }
                if(usuario.getEmail()!=null && !usuario.getEmail().equals("")){
                    indice++;
                    ps.setString(indice, usuario.getEmail()+"%");
                }
            }
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                UsuarioEntity usu = new UsuarioEntity();
                usu.setCodigo(rs.getLong("id_usuario"));
                usu.setNome(rs.getString("nome_usuario"));
                usu.setEmail(rs.getString("email_usuario"));
                usu.setLogin(rs.getString("login_usuario"));
                usu.setSenha(rs.getString("senha_usuario"));
                
                usuarios.add(usu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString(), ex.getLocalizedMessage());
        }finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage());
            }
        }
        
        return usuarios;
    }
    public List<UsuarioEntity> listarUsuario() throws NegocioException{
        String sql = "SELECT id_usuario, nome_usuario, login_usuario, senha_usuario, email_usuario FROM usuario";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>();
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                UsuarioEntity usu = new UsuarioEntity();
                usu.setCodigo(rs.getLong("id_usuario"));
                usu.setNome(rs.getString("nome_usuario"));
                usu.setEmail(rs.getString("email_usuario"));
                usu.setLogin(rs.getString("login_usuario"));
                usu.setSenha(rs.getString("senha_usuario"));
                
                usuarios.add(usu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString(), ex.getLocalizedMessage());
        }finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage());
            }
        }
        
        return usuarios;
    }
    
    public void excluirUsuario(Long codigoUsuario) throws NegocioException{
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            ps.setLong(1, codigoUsuario);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage());
            }
        }
    }
    
    
    public UsuarioEntity autenticar(String login, String senha) throws NegocioException{
        String sql = "SELECT id_usuario, nome_usuario, login_usuario, senha_usuario, email_usuario FROM usuario "
                + "WHERE login_usuario = ? AND senha_usuario = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            ps.setString(1, login);
            ps.setString(2, senha);
            
            rs = ps.executeQuery();
            
            UsuarioEntity usuarioAutenticado = null;
            
            if(rs.next()){
                usuarioAutenticado = new UsuarioEntity();
                usuarioAutenticado.setCodigo(rs.getLong("id_usuario"));
                usuarioAutenticado.setNome(rs.getString("nome_usuario"));
                usuarioAutenticado.setLogin(rs.getString("login_usuario"));
                usuarioAutenticado.setSenha(rs.getString("senha_usuario"));
                usuarioAutenticado.setEmail(rs.getString("email_usuario"));
            }
            
            return usuarioAutenticado;
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Erro ao autenticar");
        }finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage());
            }
        }
        
    }
    
    public UsuarioEntity buscarUsuarioporId(Long codigoUsuario) throws NegocioException{
        String sql = "SELECT id_usuario, nome_usuario, login_usuario, senha_usuario, email_usuario FROM usuario WHERE id_usuario = ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setLong(1, codigoUsuario);
            
            rs=ps.executeQuery();
            
            UsuarioEntity usuarioEncontrado = null;
            
            if(rs.next()){
                usuarioEncontrado = new UsuarioEntity();
                usuarioEncontrado.setCodigo(rs.getLong("id_usuario"));
                usuarioEncontrado.setNome(rs.getString("nome_usuario"));
                usuarioEncontrado.setLogin(rs.getString("login_usuario"));
                usuarioEncontrado.setSenha(rs.getString("senha_usuario"));
                usuarioEncontrado.setEmail(rs.getString("email_usuario"));
            }
            
            return usuarioEncontrado;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage());
            }
        }
        
    }
    
    public boolean loginCadastrado(String loginUsuario) throws NegocioException{
        String sql = "SELECT count(*) FROM usuario WHERE login_usuario = ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setString(1, loginUsuario);
            
            rs=ps.executeQuery();
            
            int qtdade = 0;
            
            if(rs.next()){
                qtdade = rs.getInt(1);
            }
            
            if(qtdade > 0){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException( ex.getLocalizedMessage());
            }
        }
        
        
    }
    
    public String atualizaUsuario(UsuarioEntity usuario) throws NegocioException{
        String sql = "UPDATE usuario SET nome_usuario = ?, login_usuario = ?, senha_usuario = ?, email_usuario = ? WHERE id_usuario = ?";
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            ps.setLong(5, usuario.getCodigo());
            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""), ex.getLocalizedMessage());
            
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage());
            }
        }
        
        return "Usuário atualizado com sucesso!";
    }
    
    
    

}
