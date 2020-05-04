/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.pregunta;

import com.app.inmuebles.pregunta.Pregunta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PreguntaServiceImpl implements PreguntaService{

    @Autowired
    private PreguntaDAO preguntaDAO;

    @Override
    public List<Pregunta> listAll() {
        return preguntaDAO.getRegistros();
    }

    @Override
    public int addPregunta(Pregunta pr) {
        return preguntaDAO.addPregunta(pr);
    }

    @Override
    public Pregunta getPregunta(int id) {
        return preguntaDAO.getPregunta(id);
    }

    @Override
    public int editPregunta(Pregunta pr) {
        return preguntaDAO.editPregunta(pr);
    }

    @Override
    public int deletePregunta(int id, int opcion) {
        return preguntaDAO.deletePregunta(id, opcion);
    }

    @Override
    public List<Pregunta> getRegistrosPreguntas(int idCuestionario) {
        return preguntaDAO.getRegistrosPreguntas(idCuestionario);
    }

}
