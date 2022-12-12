/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.bo;

import com.indioxd.perseus.core.dao.ClienteDAO;
import com.indioxd.perseus.core.entity.PessoaEntity;
import com.indioxd.perseus.core.util.exception.NegocioException;
import java.util.List;

/**
 *
 * @author Huellinton.Mota
 */
public class ClienteBO {
    
     public String salvarCliente(PessoaEntity cliente) throws NegocioException{
         validarCliente(cliente);
         return new ClienteDAO().salvarCliente(cliente);
     }
     
     public List<PessoaEntity> listarCliente() throws NegocioException{
         return new ClienteDAO().listarCliente();
     }
     
     public void excluirCliente(Long codigoCliente) throws NegocioException{
         new ClienteDAO().excluirCliente(codigoCliente);
     }
     public PessoaEntity buscarClientePorID(Long codigoCliente) throws NegocioException{
         return new ClienteDAO().buscarClientePorID(codigoCliente);
     }
     public String atualizaCliente(PessoaEntity cliente) throws NegocioException{
         return new ClienteDAO().atualizaCliente(cliente);
     }
     
     public void validarCliente(PessoaEntity cliente) throws NegocioException{
         ClienteDAO dao = new ClienteDAO();
         
         if(cliente.getRazaoSocial()==null || cliente.getRazaoSocial().equals("")){
             throw new NegocioException("Nome / Razão Social precisa ser preenchido");
         }
         if(cliente.getCpfCnpj()==null || cliente.getCpfCnpj().equals("")){
             throw new NegocioException("CPF / CNPJ precisa ser informado");
         }
         if(dao.cnpjCadastrado(cliente.getCpfCnpj().replace(".", "").replace("-", "").replace("/", ""))){
             throw new NegocioException("CPF/CNPJ já cadastrado");
         }
         
     }
     
     public List<PessoaEntity> listarClienteFiltrado(PessoaEntity cliente) throws NegocioException{
         return new ClienteDAO().listarClienteFiltrado(cliente);
     }

}
