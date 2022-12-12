/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.service;

import com.indioxd.perseus.core.bo.ClienteBO;
import com.indioxd.perseus.core.entity.PessoaEntity;
import com.indioxd.perseus.core.util.exception.NegocioException;
import java.util.List;

/**
 *
 * @author Huellinton.Mota
 */
public class ClienteService {
    
     public String salvarCliente(PessoaEntity cliente) throws NegocioException{
         return new ClienteBO().salvarCliente(cliente);
     }
     
     public List<PessoaEntity> listarCliente() throws NegocioException{
         return new ClienteBO().listarCliente();
     }
     
     public void excluirCliente(Long codigoCliente) throws NegocioException{
         new ClienteBO().excluirCliente(codigoCliente);
     }
     
     public PessoaEntity buscarClientePorID(Long codigoCliente) throws NegocioException{
         return new ClienteBO().buscarClientePorID(codigoCliente);
     }
     public String atualizaCliente(PessoaEntity cliente) throws NegocioException{
         return new ClienteBO().atualizaCliente(cliente);
     }
     
     public List<PessoaEntity> listarClienteFiltrado(PessoaEntity cliente) throws NegocioException{
         return new ClienteBO().listarClienteFiltrado(cliente);
     }

}
