/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.bo;

import com.indioxd.perseus.core.dao.RecursoDAO;
import com.indioxd.perseus.core.entity.RecursoEntity;
import java.util.List;

/**
 *
 * @author Huellinton.Mota
 */
public class RecursoBO {
    public List<RecursoEntity> listarRecurso(){
        return new RecursoDAO().listarRecurso();
    }
    
    public void excluirRecurso(Long codigoRecurso){
        new RecursoDAO().excluirRecurso(codigoRecurso);
    }
    
    public RecursoEntity buscarRecursoPorId(Long codigoRecurso){
        return new RecursoDAO().buscarRecursoPorId(codigoRecurso);
    }
    
        public void atualizarRecurso(RecursoEntity recurso){
            new RecursoDAO().atualizarRecurso(recurso);
            
        }
}
