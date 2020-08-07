/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.kcatalogo;

import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface KcatalogoDAO {
    
    List<Kcatalogo> getRecords();

    List<String> getRecordsOnlyCatalogo();

    int add(Kcatalogo kca);

    int update(Kcatalogo kca);

    Kcatalogo get(String llave);

    int delete(String llave, int opcion);

    List<Kcatalogo> getRecordsEncuesta(String catalogo);
    
}
