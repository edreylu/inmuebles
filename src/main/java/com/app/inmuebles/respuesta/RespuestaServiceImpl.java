/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.respuesta;

import com.app.inmuebles.cuestionario.CuestionarioDAO;
import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.util.Procedure;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edward Reyes
 */
@Service
public class RespuestaServiceImpl implements RespuestaService{

    private final RespuestaDAO respuestaDAO;
    private final CuestionarioDAO cuestionarioDAO;

    @Autowired
    public RespuestaServiceImpl(RespuestaDAO respuestaDAO, CuestionarioDAO cuestionarioDAO) {
        this.respuestaDAO = respuestaDAO;
        this.cuestionarioDAO = cuestionarioDAO;
    }
    
    @Override
    public List<Cuestionario> listAll() {
        return cuestionarioDAO.getRecords();
    }
    
    @Override
    public Procedure ActRespuesta(List<Respuesta> respuestas){
    return respuestaDAO.ActRespuesta(respuestas);
    }
    
    @Override
    public List<Respuesta> listRespuestasByIdAndUsuario(int idCuestionario, int noUsuario) {
    return respuestaDAO.getRecordsRespuestas(idCuestionario, noUsuario);
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