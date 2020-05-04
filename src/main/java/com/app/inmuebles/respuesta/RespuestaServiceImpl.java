/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.respuesta;

import com.app.inmuebles.cuestionario.CuestionarioDAO;
import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.util.Procedure;
import com.app.inmuebles.respuesta.Respuesta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edward Reyes
 */
@Service
public class RespuestaServiceImpl implements RespuestaService{

    @Autowired
    private RespuestaDAO respuestaDAO;
    @Autowired
    private CuestionarioDAO cuestionarioDAO;
    
    @Override
    public List<Cuestionario> listAll() {
        return cuestionarioDAO.getRegistros();
    }
    
    @Override
    public Procedure ActRespuesta(List<Respuesta> respuestas){
    return respuestaDAO.ActRespuesta(respuestas);
    }
    
    @Override
    public List<Respuesta> getRegistrosRespuestas(int idCuestionario, int noUsuario) {
    return respuestaDAO.getRegistrosRespuestas(idCuestionario, noUsuario);
    }
    
    @Override
    public int countResponses(int idCuestionario, int noUsuario) {
    return respuestaDAO.countResponses(idCuestionario, noUsuario);
    }

    @Override
    public Respuesta getRespuesta(int idCuestionario, int noUsuario, int idPregunta) {
     return respuestaDAO.getRespuesta(idCuestionario, noUsuario,idPregunta);
    }
}