/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.kcatalogo;

import com.app.riife.util.Mensaje;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface KcatalogoService {
    
    List<Kcatalogo> listAll();

    Mensaje add(Kcatalogo kcatalogo);

    Kcatalogo get(String id);

    Mensaje update(Kcatalogo kcatalogo);
    
    Mensaje delete(String id, int opcion);

    List<Kcatalogo> listCatalogoEncuesta(String catalogo);

    List<String> listOnlyCatalogo();
}
