/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.dao;

import com.indioxd.perseus.core.dao.connection.ConexaoMySQL;
import com.indioxd.perseus.core.entity.RecursoEntity;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huellinton.Mota
 */
public class RecursoDAO {
    
    public List<RecursoEntity> listarRecurso(){
        String sql = "SELECT * FROM recurso";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<RecursoEntity> resultado = new ArrayList<RecursoEntity>();
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(RecursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(RecursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return resultado;
    }
    
    public void excluirRecurso(Long codigoRecurso){
        String sql = "DELETE FROM recurso WHERE id_recurso = ?";
        
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setLong(1, codigoRecurso);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RecursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(RecursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    public RecursoEntity buscarRecursoPorId(Long codigoRecurso){
        String sql = "SELECT * FROM recurso WHERE id_recurso = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setLong(1, codigoRecurso);
            
            rs = ps.executeQuery();
            
            RecursoEntity  recursoEncontrado = null;
            
            if(rs.next()){
                recursoEncontrado = new RecursoEntity();
                recursoEncontrado.setCodigo(rs.getLong("id_recurso"));
                recursoEncontrado.setNomeRecurso(rs.getString("nome_recurso"));
                recursoEncontrado.setCaminhoTela(rs.getString("caminho_tela"));
            }
            
            return recursoEncontrado;
        } catch (SQLException ex) {
            Logger.getLogger(RecursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(RecursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    
    public void atualizarRecurso(RecursoEntity recurso){
        String sql = "UPDATE recurso SET nome_recurso = ?, caminho_tela = ? WHERE id_recurso = ?";
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setString(1, recurso.getNomeRecurso());
            ps.setString(2, recurso.getCaminhoTela());
            ps.setLong(3, recurso.getCodigo());
            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RecursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(RecursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
