/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.dao;

import com.indioxd.perseus.core.dao.connection.ConexaoMySQL;
import com.indioxd.perseus.core.entity.ProdutoEntity;
import com.indioxd.perseus.core.entity.VendaEntity;
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
public class VendaDAO {
    
    public String salvaVenda(VendaEntity venda) throws NegocioException{
        String sql = "INSERT INTO venda (id_cliente, total_venda) VALUES (?,?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long codVenda = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if(venda.getCliente()!=null){
                ps.setLong(1, venda.getCliente().getCodigo());
            }else{
                ps.setNull(1, Types.NULL);
            }
            ps.setDouble(2, venda.getTotal());
            
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                codVenda = rs.getLong(1);
            }
            salvaItensVenda(venda.getProdutosVendidos(), codVenda);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString(), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return "Venda Cadastrada com sucesso!";
    }
    
    public void salvaItensVenda(List<ProdutoEntity> produtos, Long codigoVenda) throws NegocioException{
        String sql = "INSERT INTO venda_produto (id_venda, id_produto, valor_unitario_produto,qt_produto,valor_total_produto) values (?,?,?,?,?)";
        
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            for (ProdutoEntity produto : produtos) {
                ps.setLong(1, codigoVenda); //id_venda
                ps.setLong(2, produto.getCodigo()); //id_produto
                ps.setDouble(3, produto.getPrecoVenda()); //valor_unitario_produto
                ps.setDouble(4, produto.getEstoque()); //qt_produto
                ps.setDouble(5, produto.getPrecoCusto()); //valor_total_produto
                
                ps.execute();
                
                new ProdutoDAO().atualizaEstoque(produto.getCodigo(), produto.getEstoque());
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString(), ex.getLocalizedMessage());
            
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    public VendaEntity localizaVendaPorID(Long codigoVenda) throws NegocioException{
        String sql = "SELECT id_venda, id_cliente, total_venda, data_venda FROM venda where id_venda = ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setLong(1, codigoVenda);
            
            rs = ps.executeQuery();
            
            VendaEntity vendaEncontrada = null;
            
            if(rs.next()){
                vendaEncontrada = new VendaEntity();
                vendaEncontrada.setCodigo(rs.getLong("id_venda"));
                vendaEncontrada.setTotal(rs.getDouble("total_venda"));
                vendaEncontrada.setCliente(new ClienteDAO().buscarClientePorID(rs.getLong("id_cliente")));
                vendaEncontrada.setDataVenda(rs.getDate("data_venda"));
                //setItens
                vendaEncontrada.setProdutosVendidos(listarItensPorVenda(codigoVenda));
            }
            
            return vendaEncontrada;
        } catch (SQLException e) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE,null, e);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""), e.getLocalizedMessage());
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage());
            }
        }
    }
    
    public List<ProdutoEntity> listarItensPorVenda(Long codigoVenda) throws NegocioException{
        
        String sql = "SELECT id_venda_produto, id_venda, id_produto, valor_unitario_produto, "
                + "qt_produto, valor_total_produto FROM venda_produto WHERE id_venda = ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<ProdutoEntity> itensVendidos = new ArrayList<ProdutoEntity>();
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setLong(1, codigoVenda);
            rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoEntity prod = new ProdutoDAO().buscarProdutoPorID(rs.getLong("id_produto"));
                prod.setCodigo(rs.getLong("id_produto"));
                //prod.setDescricao(rs.getString("descricao_produto"));
                prod.setPrecoVenda(rs.getDouble("valor_unitario_produto"));
                prod.setPrecoCusto(rs.getDouble("valor_total_produto"));               
                //prod.setUnidade(rs.getString("unidade"));
                prod.setEstoque(rs.getDouble("qt_produto")); 
                itensVendidos.add(prod);
            }
            
            return itensVendidos;
            
            /*
            codigoVenda // id_venda
            produto.Codigo // id_produto
            produto.PrecoVenda // valor_unitario_produto
            produto.Estoque // qt_produto
            produto.PrecoCusto // valor_total_produto
            */
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""), ex.getLocalizedMessage());
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage());
            }
        }
    }
    
    public List<VendaEntity> listarVenda() throws NegocioException{
        String sql = "SELECT id_venda, id_cliente, total_venda, data_venda FROM venda ";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<VendaEntity> vendas = new ArrayList<VendaEntity>();
        
        try{
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                VendaEntity v = new VendaEntity();
                v.setCodigo(rs.getLong("id_venda"));
                v.setCliente(new ClienteDAO().buscarClientePorID(rs.getLong("id_cliente")));
                v.setTotal(rs.getDouble("total_venda"));
                v.setDataVenda(rs.getDate("data_venda"));
                //TODO itens Venda
                vendas.add(v);
            }
        }catch (SQLException ex) {
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
        
        return vendas;
    }
    
    //TODO
    public List<VendaEntity> listaVendaFiltrada(VendaEntity venda) throws NegocioException{
        String sql = "SELECT id_venda, id_cliente, total_venda, data_venda FROM venda ";
        boolean adicionaWhere = true;
        
        if(venda != null){
            if(venda.getCodigo()!=null){
                adicionaWhere = false;
                sql += " WHERE ";
                sql += "id_venda = ? ";
            }
            if(venda.getCliente()!=null){
                if(adicionaWhere){
                    sql +=" WHERE ";
                    adicionaWhere = false;
                }else{
                    sql += " AND ";
                }
                sql+= " id_cliente = ? ";
            }
            if(venda.getDataVenda()!=null){
                if(adicionaWhere){
                    sql +=" WHERE ";
                    adicionaWhere = false;
                }else{
                    sql += " AND ";
                }
                sql+= " data_venda = ? ";
            }
            
            
        }
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
        List<VendaEntity> vendas = new ArrayList<VendaEntity>();
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            int indice = 0;
            if(venda!= null){
                if(venda.getCodigo()!=null){
                    indice++;
                    ps.setLong(indice, venda.getCodigo());
                }
                if(venda.getCliente()!=null){
                    indice++;
                    ps.setLong(indice, venda.getCliente().getCodigo());
                }
                if(venda.getDataVenda()!=null){
                    indice++;
                    ps.setDate(indice, new Date(venda.getDataVenda().getTime()));
                    System.out.println(new Date(venda.getDataVenda().getTime()) + " | "+ venda.getDataVenda().toString() );
                }
            }
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                VendaEntity v = new VendaEntity();
                v.setCodigo(rs.getLong("id_venda"));
                v.setCliente(new ClienteDAO().buscarClientePorID(rs.getLong("id_cliente")));
                v.setTotal(rs.getDouble("total_venda"));
                v.setDataVenda(rs.getDate("data_venda"));
                //TODO itens Venda
                System.out.println(v.getCodigo()+ " - " + v.getTotal() + " - " +v.getDataVenda().toString());
                vendas.add(v);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vendas;
    }

}
