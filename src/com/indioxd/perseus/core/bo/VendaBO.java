/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.bo;

import com.indioxd.perseus.core.dao.UsuarioDAO;
import com.indioxd.perseus.core.dao.VendaDAO;
import com.indioxd.perseus.core.entity.UsuarioEntity;
import com.indioxd.perseus.core.entity.VendaEntity;
import com.indioxd.perseus.core.util.exception.NegocioException;
import java.util.List;

/**
 *
 * @author Huellinton.Mota
 */
public class VendaBO {

    public String salvarVenda(VendaEntity venda) throws NegocioException{
        
        validarVenda(venda);
        VendaDAO dao = new VendaDAO();
        return dao.salvaVenda(venda);

    }
    
    // TODO filtrar venda
    public List<VendaEntity> listaVendaFiltrada(VendaEntity venda) throws NegocioException{
        return new VendaDAO().listaVendaFiltrada(venda);
    }
    
    public List<VendaEntity> listaVenda() throws NegocioException{
        return new VendaDAO().listarVenda();
    }
    
//    public void excluirUsuario(Long codigoUsuario) throws NegocioException{
//        new UsuarioDAO().excluirUsuario(codigoUsuario);
//    }
    
    public VendaEntity buscarVendaporId(Long codigoVenda) throws NegocioException{
        return new VendaDAO().localizaVendaPorID(codigoVenda);
    }
    
//    public String atualizaUsuario(UsuarioEntity usuario) throws NegocioException{
//        return new UsuarioDAO().atualizaUsuario(usuario);
//    }
    
    public void validarVenda(VendaEntity venda) throws NegocioException{
        if(venda.getTotal()==null || venda.getTotal()<=0){
            throw new NegocioException("Valor da venda não deve ser menor ou igual a R$ 0,00");
        }
        if(venda.getCliente()==null || venda.getCliente().getCodigo()==null || venda.getCliente().getCodigo()==0){
            throw new NegocioException("Cliente deve ser informado");
        }
    }
    
    
}
