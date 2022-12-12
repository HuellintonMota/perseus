/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.dao;

import com.indioxd.perseus.core.dao.connection.ConexaoMySQL;
import com.indioxd.perseus.core.entity.ProdutoEntity;
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
public class ProdutoDAO {
    
    //<editor-fold defaultstate="collapsed" desc="Salvar Produto: String">
    public String salvarProduto(ProdutoEntity produto) throws NegocioException{
        String sql = "INSERT INTO produto (descricao_produto, preco_venda, preco_custo, unidade, id_fornecedor,qt_estoque) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getPrecoVenda());
            ps.setDouble(3, produto.getPrecoCusto());
            ps.setString(4, produto.getUnidade());
            if(produto.getFornecedor()!=null){
                ps.setLong(5, produto.getFornecedor().getCodigo());
            }
            else{
                ps.setNull(5, Types.NULL);
            }
            ps.setDouble(6, produto.getEstoque());
            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage(),"Erro ao encerrar conexão com Banco de dados");
            }
        }
        
        return "Produto cadastrado com sucesso";
        
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Alterar produto: String">
    public String alterarProduto(ProdutoEntity produto) throws NegocioException{
        String sql = "UPDATE produto SET descricao_produto = ?, preco_venda = ?, preco_custo = ?, unidade = ? , id_fornecedor = ?, qt_estoque = ? WHERE id_produto = ?";
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getPrecoVenda());
            ps.setDouble(3, produto.getPrecoCusto());
            ps.setString(4, produto.getUnidade());
            ps.setLong(5, produto.getFornecedor().getCodigo());
            ps.setDouble(6, produto.getEstoque());
            ps.setLong(7, produto.getCodigo());
            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage(),"Erro ao encerrar conexão com Banco de dados");
            }
        }
        
        return "Produto atualizado com sucesso";
        
    }
//</editor-fold>
          
    //<editor-fold defaultstate="collapsed" desc="Excluir Produto: void">
    public void excluirProduto(Long codigoProduto) throws NegocioException{
        String sql = "DELETE FROM produto WHERE id_produto = ?";
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            ps.setLong(1, codigoProduto);
            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage(),"Erro ao fechar conexão");
            }
            
        }
    }
//</editor-fold>
        
    //<editor-fold defaultstate="collapsed" desc="Lista Produtos: List<Produto>">
    public List<ProdutoEntity> listarProdutos() throws NegocioException{
        String sql = "SELECT id_produto, descricao_produto, preco_venda, preco_custo, unidade, id_fornecedor, qt_estoque FROM produto";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<ProdutoEntity> produtos = new ArrayList<ProdutoEntity>();
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoEntity prod = new ProdutoEntity();
                prod.setCodigo(rs.getLong("id_produto"));
                prod.setDescricao(rs.getString("descricao_produto"));
                prod.setPrecoVenda(rs.getDouble("preco_venda"));
                prod.setPrecoCusto(rs.getDouble("preco_custo"));
                prod.setUnidade(rs.getString("unidade"));
                Long codigoFornecedor = rs.getLong("id_fornecedor");
                prod.setFornecedor(new FornecedorDAO().buscarFornecedorPorID(codigoFornecedor));
                prod.setEstoque(rs.getDouble("qt_estoque"));
                
                produtos.add(prod);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
        return produtos;
    }
//</editor-fold>
      
    //<editor-fold defaultstate="collapsed" desc="Lista Produtos Filtrados: List<Produto>">
    public List<ProdutoEntity> listarProdutoFiltrado(ProdutoEntity produto) throws NegocioException{
        String sql = "SELECT id_produto, descricao_produto, preco_venda, preco_custo, unidade, id_fornecedor, qt_estoque FROM produto ";
        boolean adicionaWhere = true;
        
        if(produto != null){
            if(produto.getCodigo()!=null){
                adicionaWhere = false;
                sql += " WHERE ";
                sql += " id_produto = ? ";
            }
            if(produto.getDescricao()!=null && !produto.getDescricao().equals("")){
                if(adicionaWhere){
                    sql += " WHERE ";
                    adicionaWhere = false;
                }else{
                    sql += " AND ";
                }
                sql += " descricao_produto LIKE ?";
            }
            if(produto.getUnidade()!=null && !produto.getUnidade().equals("")){
                if(adicionaWhere){
                    sql += " WHERE ";
                    adicionaWhere = false;
                }else{
                    sql += " AND ";
                }
                sql += " unidade LIKE ?";
            }
        }
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<ProdutoEntity> produtos = new ArrayList<ProdutoEntity>();
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            int indice = 0;
            if(produto != null){
                if(produto.getCodigo()!=null){
                    indice++;
                    ps.setLong(indice, produto.getCodigo());
                }
                if(produto.getDescricao()!=null && !produto.getDescricao().equals("")){
                    indice++;
                    ps.setString(indice, produto.getDescricao()+"%");
                }
                if(produto.getUnidade()!=null && !produto.getUnidade().equals("")){
                    indice++;
                    ps.setString(indice, produto.getUnidade()+"%");
                }
            }
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoEntity prod = new ProdutoEntity();
                prod.setCodigo(rs.getLong("id_produto"));
                prod.setDescricao(rs.getString("descricao_produto"));
                prod.setPrecoVenda(rs.getDouble("preco_venda"));
                prod.setPrecoCusto(rs.getDouble("preco_custo"));
                prod.setUnidade(rs.getString("unidade"));
                Long codigoFornecedor = rs.getLong("id_fornecedor");
                prod.setFornecedor(new FornecedorDAO().buscarFornecedorPorID(codigoFornecedor));
                prod.setEstoque(rs.getDouble("qt_estoque"));
                
                produtos.add(prod);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
        return produtos;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Buscar Produto Por ID: Produto">
    public ProdutoEntity buscarProdutoPorID(Long codigoProduto) throws NegocioException{
        String sql = "SELECT id_produto, descricao_produto, preco_venda, preco_custo, unidade, id_fornecedor, qt_estoque FROM produto WHERE id_produto = ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setLong(1, codigoProduto);
            rs = ps.executeQuery();
            ProdutoEntity produtoEncontrado = null;
            
            if(rs.next()){
                produtoEncontrado = new ProdutoEntity();
                produtoEncontrado.setCodigo(rs.getLong("id_produto"));
                produtoEncontrado.setDescricao(rs.getString("descricao_produto"));
                produtoEncontrado.setPrecoVenda(rs.getDouble("preco_venda"));
                produtoEncontrado.setPrecoCusto(rs.getDouble("preco_custo"));
                produtoEncontrado.setUnidade(rs.getString("unidade"));
                Long codigoFornecedor = rs.getLong("id_fornecedor");
                produtoEncontrado.setFornecedor(new FornecedorDAO().buscarFornecedorPorID(codigoFornecedor));
                produtoEncontrado.setEstoque(rs.getDouble("qt_estoque"));
            }
            
            return produtoEncontrado;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
//</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="Atualiza Estoque: void">
    public void atualizaEstoque(Long codigoProduto, Double qtdadeMovimenta) throws NegocioException{
        String sql = "UPDATE produto SET qt_estoque = ? WHERE id_produto = ?";
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            Double estoqueAtual = consultaEstoque(codigoProduto);
            Double estoqueNovo = estoqueAtual - qtdadeMovimenta;
            
            ps.setDouble(1, estoqueNovo);
            ps.setLong(2, codigoProduto);
            
            ps.execute();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString(), ex.getLocalizedMessage());
        }finally{
            try {
                
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage());
            }
        }
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Consulta Estoque: Double">
    public Double consultaEstoque(Long codigoProduto) throws NegocioException{
        String sql = "SELECT qt_estoque FROM produto WHERE id_produto = ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            ps.setLong(1, codigoProduto);
            
            rs = ps.executeQuery();
            Double estoque = null;
            if(rs.next()){
                estoque = rs.getDouble("qt_estoque");
            }
            return estoque;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
    }
//</editor-fold>
    

}
