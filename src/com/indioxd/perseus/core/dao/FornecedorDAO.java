/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.dao;

import com.indioxd.perseus.core.dao.connection.ConexaoMySQL;
import com.indioxd.perseus.core.entity.PessoaEntity;
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
public class FornecedorDAO {
    
    public String salvarFornecedor(PessoaEntity fornecedor) throws NegocioException{
        String sql = "INSERT INTO fornecedor (razao_social, fantasia, cpf_cnpj, inscricao_estadual,tipo,rg,data_nascto,"
                + "endereco_logradouro, endereco_bairro, endereco_numero,endereco_cidade,endereco_uf, endereco_cep, endereco_complemento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            //razao_social
            ps.setString(1, fornecedor.getRazaoSocial());
            //fantasia
            ps.setString(2, fornecedor.getFantasia());
            //cpf_cnpj
            ps.setString(3, fornecedor.getCpfCnpj());
            //inscricao_estadual
            ps.setString(4, fornecedor.getInscricaoEstadual());
            //tipo
            ps.setString(5, fornecedor.getTipo());
            //rg
            ps.setString(6, fornecedor.getRg());
            //data_nascto
            if(fornecedor.getDataNascto()!=null){
                ps.setDate(7, new Date(fornecedor.getDataNascto().getTime()));
            }else{
                ps.setDate(7, null);
            }
            //endereco_logradouro
            ps.setString(8, fornecedor.getEnderecoLogradouro());
            //endereco_bairro
            ps.setString(9, fornecedor.getEnderecoBairro());
            //endereco_numero
            ps.setString(10, fornecedor.getEnderecoNumero());
            //endereco_cidade
            ps.setString(11, fornecedor.getEnderecoCidade());
            //endereco_uf
            ps.setString(12, fornecedor.getEnderecoUF());
            //endereco_cep
            ps.setString(13, fornecedor.getEnderecoCEP());
            //endereco_complemento
            ps.setString(14, fornecedor.getEnderecoComplemento());
            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
        return "Fornecedor cadastrado com sucesso";
    }
    
    //TODO
    public String atualizaFornecedor(PessoaEntity fornecedor) throws NegocioException{
        String sql = "UPDATE fornecedor SET razao_social = ?, fantasia = ?, cpf_cnpj = ?, "
                + "inscricao_estadual = ?, tipo = ?, rg = ?, data_nascto = ?, "
                + "endereco_logradouro = ?, endereco_bairro = ?, endereco_numero = ?,endereco_cidade = ?, "
                + "endereco_uf = ?, endereco_cep = ?, endereco_complemento = ? "
                + "WHERE id_fornecedor = ?";
        
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            //razao_social
            ps.setString(1, fornecedor.getRazaoSocial());
            //fantasia
            ps.setString(2, fornecedor.getFantasia());
            //cpf_cnpj
            ps.setString(3, fornecedor.getCpfCnpj());
            //inscricao_estadual
            ps.setString(4, fornecedor.getInscricaoEstadual());
            //tipo
            ps.setString(5, fornecedor.getTipo());
            //rg
            ps.setString(6, fornecedor.getRg());
            //data_nascto
            if(fornecedor.getDataNascto()!=null){
                ps.setDate(7, new Date(fornecedor.getDataNascto().getTime()));
            }else{
                ps.setDate(7, null);
            }
            //endereco_logradouro
            ps.setString(8, fornecedor.getEnderecoLogradouro());
            //endereco_bairro
            ps.setString(9, fornecedor.getEnderecoBairro());
            //endereco_numero
            ps.setString(10, fornecedor.getEnderecoNumero());
            //endereco_cidade
            ps.setString(11, fornecedor.getEnderecoCidade());
            //endereco_uf
            ps.setString(12, fornecedor.getEnderecoUF());
            //endereco_cep
            ps.setString(13, fornecedor.getEnderecoCEP());
            //endereco_complemento
            ps.setString(14, fornecedor.getEnderecoComplemento());
            //id_fornecedor
            ps.setLong(15, fornecedor.getCodigo());
            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
        return "Fornecedor atualizado com sucesso";
    }
    
    
    public List<PessoaEntity> listarFornecedor() throws NegocioException{
        String sql = "SELECT id_fornecedor, razao_social, fantasia, cpf_cnpj, inscricao_estadual,tipo,rg,data_nascto,"
                + "endereco_logradouro, endereco_bairro, endereco_numero,endereco_cidade,endereco_uf, endereco_cep, endereco_complemento, data_cadastro "
                + "FROM fornecedor";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<PessoaEntity> fornecedores = new ArrayList<PessoaEntity>();
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                PessoaEntity forn = new PessoaEntity();
                forn.setCodigo(rs.getLong("id_fornecedor"));
                forn.setRazaoSocial(rs.getString("razao_social"));
                forn.setFantasia(rs.getString("fantasia"));
                forn.setCpfCnpj(rs.getString("cpf_cnpj"));
                forn.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                //tipo
                forn.setTipo(rs.getString("tipo"));
                //rg
                forn.setRg(rs.getString("rg"));
                //data_nascto
                forn.setDataNascto(rs.getDate("data_nascto"));
                //endereco_logradouro
                forn.setEnderecoLogradouro(rs.getString("endereco_logradouro"));
                //endereco_bairro
                forn.setEnderecoBairro(rs.getString("endereco_bairro"));
                //endereco_numero
                forn.setEnderecoNumero(rs.getString("endereco_numero"));
                //endereco_cidade
                forn.setEnderecoCidade(rs.getString("endereco_cidade"));
                //endereco_uf
                forn.setEnderecoUF(rs.getString("endereco_uf"));
                //endereco_cep
                forn.setEnderecoCEP(rs.getString("endereco_cep"));
                //endereco_complemento
                forn.setEnderecoComplemento(rs.getString("endereco_complemento"));
                //data_cadastro
                forn.setDataCadastro(rs.getTimestamp("data_cadastro"));
                
                fornecedores.add(forn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
        return fornecedores;
    }
    public List<PessoaEntity> listarFornecedorFiltrado(PessoaEntity fornecedor) throws NegocioException{
        String sql = "SELECT id_fornecedor, razao_social, fantasia, cpf_cnpj, inscricao_estadual,tipo,rg,data_nascto,"
                + "endereco_logradouro, endereco_bairro, endereco_numero,endereco_cidade,endereco_uf, endereco_cep, endereco_complemento, data_cadastro "
                + "FROM fornecedor ";
        boolean adicionaWhere = true;
        
        if(fornecedor!=null){
            if(fornecedor.getCodigo()!=null){
                adicionaWhere = false;
                sql += " WHERE ";
                sql += " id_fornecedor = ? ";
            }
            if(fornecedor.getRazaoSocial()!=null && !fornecedor.getRazaoSocial().equals("")){
                if(adicionaWhere){
                    sql += "WHERE ";
                    adicionaWhere = false;
                }else{
                    sql += " AND ";
                }
                sql+= " razao_social LIKE ? ";
            }
            if(fornecedor.getCpfCnpj()!=null && !fornecedor.getCpfCnpj().equals("")){
                if(adicionaWhere){
                    sql += "WHERE ";
                    adicionaWhere = false;
                }else{
                    sql += " AND ";
                }
                sql+= " REPLACE(REPLACE(REPLACE(cpf_cnpj,'/',''),'.',''),'-','') LIKE REPLACE(REPLACE(REPLACE( ? ,'/',''),'.',''),'-','') ";
            }
            if(fornecedor.getFantasia()!=null && !fornecedor.getFantasia().equals("")){
                if(adicionaWhere){
                    sql += "WHERE ";
                    adicionaWhere = false;
                }else{
                    sql += " AND ";
                }
                sql+= " fantasia LIKE ? ";
            }
            
        }
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<PessoaEntity> fornecedores = new ArrayList<PessoaEntity>();
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            int indice = 0;
            if(fornecedor!=null){
                if(fornecedor.getCodigo()!=null){
                    indice++;
                    ps.setLong(indice, fornecedor.getCodigo());
                }
                if(fornecedor.getRazaoSocial()!=null && !fornecedor.getRazaoSocial().equals("")){
                    indice++;
                    ps.setString(indice, fornecedor.getRazaoSocial()+"%");
                }
                if(fornecedor.getCpfCnpj()!=null && !fornecedor.getCpfCnpj().equals("")){
                    indice++;
                    ps.setString(indice, fornecedor.getCpfCnpj()+"%");
                }
                if(fornecedor.getFantasia()!=null && !fornecedor.getFantasia().equals("")){
                    indice++;
                    ps.setString(indice, fornecedor.getFantasia()+"%");
                }

            }
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                PessoaEntity forn = new PessoaEntity();
                forn.setCodigo(rs.getLong("id_fornecedor"));
                forn.setRazaoSocial(rs.getString("razao_social"));
                forn.setFantasia(rs.getString("fantasia"));
                forn.setCpfCnpj(rs.getString("cpf_cnpj"));
                forn.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                //tipo
                forn.setTipo(rs.getString("tipo"));
                //rg
                forn.setRg(rs.getString("rg"));
                //data_nascto
                forn.setDataNascto(rs.getDate("data_nascto"));
                //endereco_logradouro
                forn.setEnderecoLogradouro(rs.getString("endereco_logradouro"));
                //endereco_bairro
                forn.setEnderecoBairro(rs.getString("endereco_bairro"));
                //endereco_numero
                forn.setEnderecoNumero(rs.getString("endereco_numero"));
                //endereco_cidade
                forn.setEnderecoCidade(rs.getString("endereco_cidade"));
                //endereco_uf
                forn.setEnderecoUF(rs.getString("endereco_uf"));
                //endereco_cep
                forn.setEnderecoCEP(rs.getString("endereco_cep"));
                //endereco_complemento
                forn.setEnderecoComplemento(rs.getString("endereco_complemento"));
                //data_cadastro
                forn.setDataCadastro(rs.getTimestamp("data_cadastro"));
                
                fornecedores.add(forn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
        return fornecedores;
    }
    
    public void excluirFornecedor(Long codigoFornecedor) throws NegocioException{
        String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            ps.setLong(1, codigoFornecedor);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement:", ""), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage());  
            }
        }
        
    }
    
    public boolean cnpjCadastrado(String cnpj) throws NegocioException{
        String sql = "SELECT count(*) FROM fornecedor WHERE REPLACE(REPLACE(REPLACE(cpf_cnpj,'/',''),'.',''),'-','') = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(cnpj);
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setString(1, cnpj);
            
            rs = ps.executeQuery();
            
            int qtdade = 0;
            
            if(rs.next()){
                qtdade = rs.getInt(1);
            }
            
            if(qtdade > 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException("Erro ao encerrar conexão\n" + ex.getLocalizedMessage());
            }
        }
    }
    
    
    public PessoaEntity buscarFornecedorPorID(Long codigoFornecedor) throws NegocioException{
        String sql = "SELECT id_fornecedor, razao_social, fantasia, cpf_cnpj, inscricao_estadual,tipo,rg,data_nascto,"
                + "endereco_logradouro, endereco_bairro, endereco_numero,endereco_cidade,endereco_uf, endereco_cep, endereco_complemento, data_cadastro "
                + "FROM fornecedor WHERE id_fornecedor = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setLong(1, codigoFornecedor);
            
            rs = ps.executeQuery();
            PessoaEntity forn = null;
            if(rs.next()){
                forn = new PessoaEntity();
                forn.setCodigo(rs.getLong("id_fornecedor"));
                forn.setRazaoSocial(rs.getString("razao_social"));
                forn.setFantasia(rs.getString("fantasia"));
                forn.setCpfCnpj(rs.getString("cpf_cnpj"));
                forn.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                //tipo
                forn.setTipo(rs.getString("tipo"));
                //rg
                forn.setRg(rs.getString("rg"));
                //data_nascto
                forn.setDataNascto(rs.getDate("data_nascto"));
                //endereco_logradouro
                forn.setEnderecoLogradouro(rs.getString("endereco_logradouro"));
                //endereco_bairro
                forn.setEnderecoBairro(rs.getString("endereco_bairro"));
                //endereco_numero
                forn.setEnderecoNumero(rs.getString("endereco_numero"));
                //endereco_cidade
                forn.setEnderecoCidade(rs.getString("endereco_cidade"));
                //endereco_uf
                forn.setEnderecoUF(rs.getString("endereco_uf"));
                //endereco_cep
                forn.setEnderecoCEP(rs.getString("endereco_cep"));
                //endereco_complemento
                forn.setEnderecoComplemento(rs.getString("endereco_complemento"));
                //data_cadastro
                forn.setDataCadastro(rs.getTimestamp("data_cadastro"));
            }
            return forn;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement:", ""), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw  new NegocioException(ex.getLocalizedMessage());
            }
        }
                
        
    }

}
