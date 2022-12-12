/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.service;

import com.indioxd.perseus.core.bo.VendaBO;
import com.indioxd.perseus.core.entity.VendaEntity;
import com.indioxd.perseus.core.util.exception.NegocioException;
import java.util.List;

/**
 *
 * @author Huellinton.Mota
 */
public class VendaService {
    
    public List<VendaEntity> listaVendas() throws NegocioException{ 
        return new VendaBO().listaVenda();
    }
    
    public List<VendaEntity> listaVendaFiltrada(VendaEntity v) throws NegocioException{
        return new VendaBO().listaVendaFiltrada(v);
    }
    
    public String salvarVenda(VendaEntity venda) throws NegocioException{
        return new VendaBO().salvarVenda(venda);
    }
    
    public VendaEntity buscarVendaporId(Long codigoVenda) throws NegocioException{
        return new VendaBO().buscarVendaporId(codigoVenda);
    }

}
