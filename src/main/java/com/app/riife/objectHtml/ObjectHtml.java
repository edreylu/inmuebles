/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.objectHtml;

import com.app.riife.kcatalogo.Kcatalogo;
import com.app.riife.respuesta.Respuesta;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface ObjectHtml {
    
    String create(List<Kcatalogo> catalogos, Respuesta respuesta, int noRespuesta);
    
}
