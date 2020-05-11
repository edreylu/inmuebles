/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.subCapitulo;

import com.app.inmuebles.util.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class SubCapituloServiceImpl implements SubCapituloService{

    @Autowired
    private SubCapituloDAO subCapituloDAO;
    private Mensaje msg;

    @Override
    public List<SubCapitulo> listAll() {
        return subCapituloDAO.getRecords();
    }

    @Override
    public Mensaje addSubCapitulo(SubCapitulo subCapitulo) {
        int valor = subCapituloDAO.addSubCapitulo(subCapitulo);
        if (valor >= 1) {
            msg = new Mensaje("Agregado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public SubCapitulo getSubCapitulo(int id) {
        return subCapituloDAO.getSubCapitulo(id);
    }

    @Override
    public Mensaje editSubCapitulo(SubCapitulo subCapitulo) {
        int valor = subCapituloDAO.editSubCapitulo(subCapitulo);
        if (valor >= 1) {
            msg = new Mensaje("Editado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo editar", 2);
        }
        return msg;
    }

    @Override
    public Mensaje deleteSubCapitulo(int id, int opcion) {
        int valor = subCapituloDAO.deleteSubCapitulo(id, opcion);
        if (valor >= 1) {
            msg = new Mensaje("Ejecutado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo ejecutar", 2);
        }
        return msg;
    }

    @Override
    public List<SubCapitulo> listByCuestionario(int idCuestionario) {
        return subCapituloDAO.getRecordsByCuestionario(idCuestionario);
    }

}
