/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.service;

import com.indioxd.perseus.core.bo.ProdutoBO;
import com.indioxd.perseus.core.entity.ProdutoEntity;
import com.indioxd.perseus.core.util.exception.NegocioException;
import java.util.List;

/**
 *
 * @author Huellinton.Mota
 */
public class ProdutoService {
    
    public String salvarProduto(ProdutoEntity produto) throws NegocioException{
        return new ProdutoBO().salvarProduto(produto);
    }
    
    public String alterarProduto(ProdutoEntity produto) throws NegocioException{
        return new ProdutoBO().alterarProduto(produto);
    }
    
    public void excluirProduto(Long codigoProduto) throws NegocioException{
        new ProdutoBO().excluirProduto(codigoProduto);
    }

    public List<ProdutoEntity> listarProdutos() throws NegocioException{
        return new ProdutoBO().listarProdutos();
    }
    
    public List<ProdutoEntity> listarProdutoFiltrado(ProdutoEntity produto) throws NegocioException{
        return new ProdutoBO().listarProdutoFiltrado(produto);
    }
    
    public ProdutoEntity buscarProdutoPorID(Long codigoProduto) throws NegocioException{
        return new ProdutoBO().buscarProdutoPorID(codigoProduto);
    }
    
    public void atualizaEstoque(Long codigoProduto, Double qtdadeMovimenta) throws NegocioException{
        new ProdutoBO().atualizaEstoque(codigoProduto, qtdadeMovimenta);
    }
    
    public Double consultaEstoque(Long codigoProduto) throws NegocioException{
        return new ProdutoBO().consultaEstoque(codigoProduto);
    }
}
