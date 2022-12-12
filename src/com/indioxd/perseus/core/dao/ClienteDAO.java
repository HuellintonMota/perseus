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
public class ClienteDAO {
    
    public String salvarCliente(PessoaEntity cliente) throws NegocioException{
        String sql = "INSERT INTO cliente (razao_social, fantasia, cpf_cnpj, inscricao_estadual,tipo,rg,data_nascto,"
                + "endereco_logradouro, endereco_bairro, endereco_numero,endereco_cidade,endereco_uf, endereco_cep, endereco_complemento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            //razao_social
            ps.setString(1, cliente.getRazaoSocial());
            //fantasia
            ps.setString(2, cliente.getFantasia());
            //cpf_cnpj
            ps.setString(3, cliente.getCpfCnpj());
            //inscricao_estadual
            ps.setString(4, cliente.getInscricaoEstadual());
            //tipo
            ps.setString(5, cliente.getTipo());
            //rg
            ps.setString(6, cliente.getRg());
            //data_nascto
            if(cliente.getDataNascto()!=null){
                ps.setDate(7, new Date(cliente.getDataNascto().getTime()));
            }else{
                ps.setDate(7, null);
            }
            //endereco_logradouro
            ps.setString(8, cliente.getEnderecoLogradouro());
            //endereco_bairro
            ps.setString(9, cliente.getEnderecoBairro());
            //endereco_numero
            ps.setString(10, cliente.getEnderecoNumero());
            //endereco_cidade
            ps.setString(11, cliente.getEnderecoCidade());
            //endereco_uf
            ps.setString(12, cliente.getEnderecoUF());
            //endereco_cep
            ps.setString(13, cliente.getEnderecoCEP());
            //endereco_complemento
            ps.setString(14, cliente.getEnderecoComplemento());
            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
        return "Cliente cadastrado com sucesso";
    }
    
    //TODO
    public String atualizaCliente(PessoaEntity Cliente) throws NegocioException{
        String sql = "UPDATE cliente SET razao_social = ?, fantasia = ?, cpf_cnpj = ?, "
                + "inscricao_estadual = ?, tipo = ?, rg = ?, data_nascto = ?, "
                + "endereco_logradouro = ?, endereco_bairro = ?, endereco_numero = ?,endereco_cidade = ?, "
                + "endereco_uf = ?, endereco_cep = ?, endereco_complemento = ? "
                + "WHERE id_cliente = ?";
        
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            //razao_social
            ps.setString(1, Cliente.getRazaoSocial());
            //fantasia
            ps.setString(2, Cliente.getFantasia());
            //cpf_cnpj
            ps.setString(3, Cliente.getCpfCnpj());
            //inscricao_estadual
            ps.setString(4, Cliente.getInscricaoEstadual());
            //tipo
            ps.setString(5, Cliente.getTipo());
            //rg
            ps.setString(6, Cliente.getRg());
            //data_nascto
            if(Cliente.getDataNascto()!=null){
                ps.setDate(7, new Date(Cliente.getDataNascto().getTime()));
            }else{
                ps.setDate(7, null);
            }
            //endereco_logradouro
            ps.setString(8, Cliente.getEnderecoLogradouro());
            //endereco_bairro
            ps.setString(9, Cliente.getEnderecoBairro());
            //endereco_numero
            ps.setString(10, Cliente.getEnderecoNumero());
            //endereco_cidade
            ps.setString(11, Cliente.getEnderecoCidade());
            //endereco_uf
            ps.setString(12, Cliente.getEnderecoUF());
            //endereco_cep
            ps.setString(13, Cliente.getEnderecoCEP());
            //endereco_complemento
            ps.setString(14, Cliente.getEnderecoComplemento());
            //id_cliente
            ps.setLong(15, Cliente.getCodigo());
            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
        return "Cliente atualizado com sucesso";
    }
    
    
    public List<PessoaEntity> listarCliente() throws NegocioException{
        String sql = "SELECT id_cliente, razao_social, fantasia, cpf_cnpj, inscricao_estadual,tipo,rg,data_nascto,"
                + "endereco_logradouro, endereco_bairro, endereco_numero,endereco_cidade,endereco_uf, endereco_cep, endereco_complemento, data_cadastro "
                + "FROM cliente";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<PessoaEntity> clientes = new ArrayList<PessoaEntity>();
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                PessoaEntity forn = new PessoaEntity();
                forn.setCodigo(rs.getLong("id_cliente"));
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
                
                clientes.add(forn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
        return clientes;
    }
    public List<PessoaEntity> listarClienteFiltrado(PessoaEntity cliente) throws NegocioException{
        String sql = "SELECT id_cliente, razao_social, fantasia, cpf_cnpj, inscricao_estadual,tipo,rg,data_nascto,"
                + "endereco_logradouro, endereco_bairro, endereco_numero,endereco_cidade,endereco_uf, endereco_cep, endereco_complemento, data_cadastro "
                + "FROM cliente ";
        boolean adicionaWhere = true;
        
        if(cliente!=null){
            if(cliente.getCodigo()!=null){
                adicionaWhere = false;
                sql += " WHERE ";
                sql += " id_cliente = ? ";
            }
            if(cliente.getRazaoSocial()!=null && !cliente.getRazaoSocial().equals("")){
                if(adicionaWhere){
                    sql += "WHERE ";
                    adicionaWhere = false;
                }else{
                    sql += " AND ";
                }
                sql+= " razao_social LIKE ? ";
            }
            if(cliente.getCpfCnpj()!=null && !cliente.getCpfCnpj().equals("")){
                if(adicionaWhere){
                    sql += "WHERE ";
                    adicionaWhere = false;
                }else{
                    sql += " AND ";
                }
                sql+= " REPLACE(REPLACE(REPLACE(cpf_cnpj,'/',''),'.',''),'-','') LIKE REPLACE(REPLACE(REPLACE( ? ,'/',''),'.',''),'-','') ";
            }
            if(cliente.getFantasia()!=null && !cliente.getFantasia().equals("")){
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
        
        List<PessoaEntity> clientes = new ArrayList<PessoaEntity>();
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            int indice = 0;
            if(cliente!=null){
                if(cliente.getCodigo()!=null){
                    indice++;
                    ps.setLong(indice, cliente.getCodigo());
                }
                if(cliente.getRazaoSocial()!=null && !cliente.getRazaoSocial().equals("")){
                    indice++;
                    ps.setString(indice, cliente.getRazaoSocial()+"%");
                }
                if(cliente.getCpfCnpj()!=null && !cliente.getCpfCnpj().equals("")){
                    indice++;
                    ps.setString(indice, cliente.getCpfCnpj()+"%");
                }
                if(cliente.getFantasia()!=null && !cliente.getFantasia().equals("")){
                    indice++;
                    ps.setString(indice, cliente.getFantasia()+"%");
                }

            }
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                PessoaEntity forn = new PessoaEntity();
                forn.setCodigo(rs.getLong("id_cliente"));
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
                
                clientes.add(forn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
        return clientes;
    }
    
    public void excluirCliente(Long codigoCliente) throws NegocioException{
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        PreparedStatement ps = null;
        
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            
            ps.setLong(1, codigoCliente);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement:", ""), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException(ex.getLocalizedMessage());  
            }
        }
        
    }
    
    public boolean cnpjCadastrado(String cnpj) throws NegocioException{
        String sql = "SELECT count(*) FROM cliente WHERE REPLACE(REPLACE(REPLACE(cpf_cnpj,'/',''),'.',''),'-','') = ?";
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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new NegocioException("Erro ao encerrar conexão\n" + ex.getLocalizedMessage());
            }
        }
    }
    
    
    public PessoaEntity buscarClientePorID(Long codigoCliente) throws NegocioException{
        String sql = "SELECT id_cliente, razao_social, fantasia, cpf_cnpj, inscricao_estadual,tipo,rg,data_nascto,"
                + "endereco_logradouro, endereco_bairro, endereco_numero,endereco_cidade,endereco_uf, endereco_cep, endereco_complemento, data_cadastro "
                + "FROM cliente WHERE id_cliente = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setLong(1, codigoCliente);
            
            rs = ps.executeQuery();
            PessoaEntity forn = null;
            if(rs.next()){
                forn = new PessoaEntity();
                forn.setCodigo(rs.getLong("id_cliente"));
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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ps.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement:", ""), ex.getLocalizedMessage());
        }finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw  new NegocioException(ex.getLocalizedMessage());
            }
        }
                
        
    }

}
