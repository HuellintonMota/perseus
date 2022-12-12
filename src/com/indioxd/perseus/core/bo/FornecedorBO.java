/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.bo;

import com.indioxd.perseus.core.dao.FornecedorDAO;
import com.indioxd.perseus.core.entity.PessoaEntity;
import com.indioxd.perseus.core.util.exception.NegocioException;
import java.util.List;

/**
 *
 * @author Huellinton.Mota
 */
public class FornecedorBO {
    
     public String salvarFornecedor(PessoaEntity fornecedor) throws NegocioException{
         validarFornecedor(fornecedor);
         return new FornecedorDAO().salvarFornecedor(fornecedor);
     }
     
     public List<PessoaEntity> listarFornecedor() throws NegocioException{
         return new FornecedorDAO().listarFornecedor();
     }
     
     public void excluirFornecedor(Long codigoFornecedor) throws NegocioException{
         new FornecedorDAO().excluirFornecedor(codigoFornecedor);
     }
     public PessoaEntity buscarFornecedorPorID(Long codigoFornecedor) throws NegocioException{
         return new FornecedorDAO().buscarFornecedorPorID(codigoFornecedor);
     }
     public String atualizaFornecedor(PessoaEntity fornecedor) throws NegocioException{
         return new FornecedorDAO().atualizaFornecedor(fornecedor);
     }
     
     public void validarFornecedor(PessoaEntity fornecedor) throws NegocioException{
         FornecedorDAO dao = new FornecedorDAO();
         
         if(fornecedor.getRazaoSocial()==null || fornecedor.getRazaoSocial().equals("")){
             throw new NegocioException("Nome / Razão Social precisa ser preenchido");
         }
         if(fornecedor.getCpfCnpj()==null || fornecedor.getCpfCnpj().equals("")){
             throw new NegocioException("CPF / CNPJ precisa ser informado");
         }
         if(dao.cnpjCadastrado(fornecedor.getCpfCnpj().replace(".", "").replace("-", "").replace("/", ""))){
             throw new NegocioException("CPF/CNPJ já cadastrado");
         }
         
     }
     
     public List<PessoaEntity> listarFornecedorFiltrado(PessoaEntity fornecedor) throws NegocioException{
         return new FornecedorDAO().listarFornecedorFiltrado(fornecedor);
     }

}
