/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.service;

import com.indioxd.perseus.core.bo.RecursoBO;
import com.indioxd.perseus.core.entity.RecursoEntity;
import java.util.List;

/**
 *
 * @author Huellinton.Mota
 */
public class RecursoService {
    public List<RecursoEntity> listarRecurso(){
        return new RecursoBO().listarRecurso();
    }
    
     public void excluirRecurso(Long codigoRecurso){
         new RecursoBO().excluirRecurso(codigoRecurso);
     }
     
     public RecursoEntity buscarRecursoPorId(Long codigoRecurso){
         return new RecursoBO().buscarRecursoPorId(codigoRecurso);
     }
     
       public void atualizarRecurso(RecursoEntity recurso){
           new RecursoBO().atualizarRecurso(recurso);
       }
}
