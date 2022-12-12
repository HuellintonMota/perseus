/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.bo;

import com.indioxd.perseus.core.dao.ProdutoDAO;
import com.indioxd.perseus.core.entity.ProdutoEntity;
import com.indioxd.perseus.core.util.exception.NegocioException;
import java.util.List;

/**
 *
 * @author Huellinton.Mota
 */
public class ProdutoBO {
    
    public String salvarProduto(ProdutoEntity produto) throws NegocioException{
        validarProduto(produto);
        return new ProdutoDAO().salvarProduto(produto);
    }
    
    public String alterarProduto(ProdutoEntity produto) throws NegocioException{
        validarProduto(produto);
        return new ProdutoDAO().alterarProduto(produto);
    }
    
    public void excluirProduto(Long codigoProduto) throws NegocioException{
        new ProdutoDAO().excluirProduto(codigoProduto);
    }
    
    public List<ProdutoEntity> listarProdutos() throws NegocioException{
        return new ProdutoDAO().listarProdutos();
    }
    
    public List<ProdutoEntity> listarProdutoFiltrado(ProdutoEntity produto) throws NegocioException{
        return new ProdutoDAO().listarProdutoFiltrado(produto);
    }
    
    public ProdutoEntity buscarProdutoPorID(Long codigoProduto) throws NegocioException{
        return new ProdutoDAO().buscarProdutoPorID(codigoProduto);
    }
    
    public void atualizaEstoque(Long codigoProduto, Double qtdadeMovimenta) throws NegocioException{
        new ProdutoDAO().atualizaEstoque(codigoProduto, qtdadeMovimenta);
    }
    
    public Double consultaEstoque(Long codigoProduto) throws NegocioException{
        return new ProdutoDAO().consultaEstoque(codigoProduto);
    }
    
    private void validarProduto(ProdutoEntity produto) throws NegocioException{
        if(produto.getDescricao()==null||produto.getDescricao().equals("")){
            throw new NegocioException("Descrição do Produto deve ser informada");
        }
    }

}
