/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.subCapitulo;

import com.app.inmuebles.subCapitulo.SubCapitulo;
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

    @Override
    public List<SubCapitulo> listAll() {
        return subCapituloDAO.getRegistros();
    }

    @Override
    public int addSubCapitulo(SubCapitulo subCapitulo) {
        return subCapituloDAO.addSubCapitulo(subCapitulo);
    }

    @Override
    public SubCapitulo getSubCapitulo(int id) {
        return subCapituloDAO.getSubCapitulo(id);
    }

    @Override
    public int editSubCapitulo(SubCapitulo subCapitulo) {
        return subCapituloDAO.editSubCapitulo(subCapitulo);
    }

    @Override
    public int deleteSubCapitulo(int id, int opcion) {
        return subCapituloDAO.deleteSubCapitulo(id, opcion);
    }

    @Override
    public List<SubCapitulo> getRegistrosPorCuestionario(int idCuestionario) {
        return subCapituloDAO.getRegistrosPorCuestionario(idCuestionario);
    }

}
