/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.service;

import com.indioxd.perseus.core.bo.FornecedorBO;
import com.indioxd.perseus.core.entity.PessoaEntity;
import com.indioxd.perseus.core.util.exception.NegocioException;
import java.util.List;

/**
 *
 * @author Huellinton.Mota
 */
public class FornecedorService {
    
     public String salvarFornecedor(PessoaEntity fornecedor) throws NegocioException{
         return new FornecedorBO().salvarFornecedor(fornecedor);
     }
     
     public List<PessoaEntity> listarFornecedor() throws NegocioException{
         return new FornecedorBO().listarFornecedor();
     }
     
     public void excluirFornecedor(Long codigoFornecedor) throws NegocioException{
         new FornecedorBO().excluirFornecedor(codigoFornecedor);
     }
     
     public PessoaEntity buscarFornecedorPorID(Long codigoFornecedor) throws NegocioException{
         return new FornecedorBO().buscarFornecedorPorID(codigoFornecedor);
     }
     public String atualizaFornecedor(PessoaEntity fornecedor) throws NegocioException{
         return new FornecedorBO().atualizaFornecedor(fornecedor);
     }
     
     public List<PessoaEntity> listarFornecedorFiltrado(PessoaEntity fornecedor) throws NegocioException{
         return new FornecedorBO().listarFornecedorFiltrado(fornecedor);
     }

}
