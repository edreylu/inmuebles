/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.kcatalogo;

import com.app.riife.util.Mensaje;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class KcatalogoServiceImpl implements KcatalogoService{

    private final KcatalogoDAO kcatalogoDAO;
    private Mensaje msg;
    
    @Autowired
    public KcatalogoServiceImpl(KcatalogoDAO kcatalogoDAO) {
        this.kcatalogoDAO = kcatalogoDAO;
    }
    
    @Override
    public List<Kcatalogo> listAll() {
        return kcatalogoDAO.getRecords();
    }

    @Override
    public Mensaje add(Kcatalogo kcatalogo) {
        int valor = kcatalogoDAO.add(kcatalogo);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Agregado correctamente", 1);
            System.out.println("se agrego registro: " + valor);
        } else {
            msg = Mensaje.CREATE("No se pudo agregar", 2);
            System.err.println("no se agrego registro");
        }
        return msg;
    }

    @Override
    public Kcatalogo get(String id) {
        return kcatalogoDAO.get(id);
    }

    @Override
    public Mensaje update(Kcatalogo kcatalogo) {
        int valor = kcatalogoDAO.update(kcatalogo);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Editado correctamente", 1);
            System.out.println("se edito registro: " + valor);
        } else {
            msg = Mensaje.CREATE("No se pudo editar", 2);
            System.err.println("no se edito registro");
        }
        return msg;
    }

    @Override
    public Mensaje delete(String id, int opcion) {
        int valor = kcatalogoDAO.delete(id, opcion);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Ejecutado correctamente", 1);
            System.out.println("se elimino registro: ");
        } else {
            msg = Mensaje.CREATE("No se pudo ejecutar", 2);
            System.err.println("no se elimino registro");
        }
        return msg;
    }

    @Override
    public List<Kcatalogo> listCatalogoEncuesta(String catalogo) {
        String catalogoPregunta = Objects.isNull(catalogo) ? "" : catalogo;
        return kcatalogoDAO.getRecordsEncuesta(catalogoPregunta);
    }

    @Override
    public List<String> listOnlyCatalogo() {
        return kcatalogoDAO.getRecordsOnlyCatalogo();
    }

}
