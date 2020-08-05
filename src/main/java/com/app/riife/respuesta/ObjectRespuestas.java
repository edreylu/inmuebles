/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.respuesta;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ObjectRespuestas {
List<Respuesta> respuestas = new ArrayList<>();

    public ObjectRespuestas() {
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

}
