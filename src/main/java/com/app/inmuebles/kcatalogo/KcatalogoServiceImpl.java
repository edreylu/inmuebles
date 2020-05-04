/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.kcatalogo;

import com.app.inmuebles.kcatalogo.Kcatalogo;
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

    @Override
    public List<Kcatalogo> listAll() {
        return kcatalogoDAO.getRegistros();
    }

    @Override
    public int addKcatalogo(Kcatalogo kcatalogo) {
        return kcatalogoDAO.addKcatalogo(kcatalogo);
    }

    @Override
    public Kcatalogo getKcatalogo(String id) {
        return kcatalogoDAO.getKcatalogo(id);
    }

    @Override
    public int editKcatalogo(Kcatalogo kcatalogo) {
        return kcatalogoDAO.editKcatalogo(kcatalogo);
    }

    @Override
    public int deleteKcatalogo(String id, int opcion) {
        return kcatalogoDAO.deleteKcatalogo(id, opcion);
    }

    @Override
    public List<Kcatalogo> getRegistrosEncuesta(String catalogo) {
        return kcatalogoDAO.getRegistrosEncuesta(catalogo);
    }

    @Override
    public List<String> getRegistrosLista() {
        return kcatalogoDAO.getRegistrosLista();
    }

}
