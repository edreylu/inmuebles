/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.capitulo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CapituloServiceImpl implements CapituloService{

    @Autowired
    private CapituloDAO capituloDAO;

    @Override
    public List<Capitulo> listAll() {
        return capituloDAO.getRegistros();
    }

    @Override
    public int addCapitulo(Capitulo capitulo) {
        return capituloDAO.addCapitulo(capitulo);
    }

    @Override
    public Capitulo getCapitulo(int id) {
        return capituloDAO.getCapitulo(id);
    }

    @Override
    public int editCapitulo(Capitulo capitulo) {
        return capituloDAO.editCapitulo(capitulo);
    }

    @Override
    public int deleteCapitulo(int id, int opcion) {
        return capituloDAO.deleteCapitulo(id, opcion);
    }

    @Override
    public List<Capitulo> getRegistrosPorCuestionario(int idCuestionario) {
        return capituloDAO.getRegistrosPorCuestionario(idCuestionario);
    }

}
