/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.kcatalogo;

import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface KcatalogoDAO {
    
    List<Kcatalogo> getRecords();

    List<String> getRecordsOnlyCatalogo();

    int addKcatalogo(Kcatalogo kca);

    int editKcatalogo(Kcatalogo kca);

    Kcatalogo getKcatalogo(String llave);

    int deleteKcatalogo(String llave, int opcion);

    List<Kcatalogo> getRecordsEncuesta(String catalogo);
    
}
