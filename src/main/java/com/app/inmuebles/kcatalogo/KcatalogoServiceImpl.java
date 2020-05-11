/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.kcatalogo;

import com.app.inmuebles.util.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class KcatalogoServiceImpl implements KcatalogoService{

    @Autowired
    private KcatalogoDAO kcatalogoDAO;
    private Mensaje msg;
    @Override
    public List<Kcatalogo> listAll() {
        return kcatalogoDAO.getRecords();
    }

    @Override
    public Mensaje addKcatalogo(Kcatalogo kcatalogo) {
        int valor = kcatalogoDAO.addKcatalogo(kcatalogo);
        if (valor >= 1) {
            msg = new Mensaje("Agregado correctamente", 1);
            System.out.println("se agrego registro: " + valor);
        } else {
            msg = new Mensaje("No se pudo agregar", 2);
            System.err.println("no se agrego registro");
        }
        return msg;
    }

    @Override
    public Kcatalogo getKcatalogo(String id) {
        return kcatalogoDAO.getKcatalogo(id);
    }

    @Override
    public Mensaje editKcatalogo(Kcatalogo kcatalogo) {
        int valor = kcatalogoDAO.editKcatalogo(kcatalogo);
        if (valor >= 1) {
            msg = new Mensaje("Editado correctamente", 1);
            System.out.println("se edito registro: " + valor);
        } else {
            msg = new Mensaje("No se pudo editar", 2);
            System.err.println("no se edito registro");
        }
        return msg;
    }

    @Override
    public Mensaje deleteKcatalogo(String id, int opcion) {
        int valor = kcatalogoDAO.deleteKcatalogo(id, opcion);
        if (valor >= 1) {
            msg = new Mensaje("Ejecutado correctamente", 1);
            System.out.println("se elimino registro: ");
        } else {
            msg = new Mensaje("No se pudo ejecutar", 2);
            System.err.println("no se elimino registro");
        }
        return msg;
    }

    @Override
    public List<Kcatalogo> listCatalogoEncuesta(String catalogo) {
        return kcatalogoDAO.getRecordsEncuesta(catalogo);
    }

    @Override
    public List<String> listOnlyCatalogo() {
        return kcatalogoDAO.getRecordsOnlyCatalogo();
    }

}
