/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Huellinton.Mota
 */
public class VendaEntity {
    
    private Long codigo;
    private PessoaEntity cliente;
    private Double total;
    private Date dataVenda;
    private List<ProdutoEntity> produtosVendidos;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public PessoaEntity getCliente() {
        return cliente;
    }

    public void setCliente(PessoaEntity cliente) {
        this.cliente = cliente;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<ProdutoEntity> getProdutosVendidos() {
        return produtosVendidos;
    }

    public void setProdutosVendidos(List<ProdutoEntity> produtosVendidos) {
        this.produtosVendidos = produtosVendidos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VendaEntity other = (VendaEntity) obj;
        return Objects.equals(this.codigo, other.codigo);
    }
    
    

}
