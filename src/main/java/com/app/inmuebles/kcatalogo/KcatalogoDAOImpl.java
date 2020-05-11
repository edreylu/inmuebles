/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.kcatalogo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author usuario
 */
@Repository
public class KcatalogoDAOImpl implements KcatalogoDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private List lista = null;
    private String sql = "";
    private int estatus = 1,
            valor = 0;

    @Override
    public List<Kcatalogo> getRecords() {
        sql = "select kca.idcuestionario,\n"
                + "       (select cu.cuestionario from cuestionario cu where cu.idcuestionario = kca.idcuestionario) cuestionario,\n"
                + "       kca.catalogo,\n"
                + "       kca.clavecatalogo, \n"
                + "       kca.descripcorta ,\n"
                + "       kca.descripcion, \n"
                + "       kca.observaciones, \n"
                + "       kca.idestatus \n"
                + "       from kcatalogo kca\n"
                + "               order by 1";

        try {
            lista = jdbcTemplate.query(sql, new KcatalogoRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }

        return lista;
    }

    @Override
    public List<String> getRecordsOnlyCatalogo() {
        sql = "select catalogo from kcatalogo GROUP BY catalogo order by 1";

        try {
            lista = jdbcTemplate.queryForList(sql);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return lista;
    }

    @Override
    public int addKcatalogo(Kcatalogo kca) {
        valor = 0;
        int claveCatalogo = jdbcTemplate.queryForObject("select nvl(max(clavecatalogo),0)+ 1 from kcatalogo where catalogo =" + kca.getCatalogo(), Integer.class);
        kca.setClaveCatalogo(claveCatalogo);
        sql = "INSERT INTO kcatalogo (idcuestionario, "
                + "catalogo, "
                + "clavecatalogo,"
                + "descripcorta,"
                + "descripcion,"
                + "observaciones,"
                + " idestatus) "
                + "VALUES (?,?,?,?,?,?,?)";

        try {
            valor = jdbcTemplate.update(sql,
                    kca.getCuestionario().getIdCuestionario(),
                    kca.getCatalogo().toUpperCase(),
                    kca.getClaveCatalogo(),
                    kca.getDescripCorta().toUpperCase(),
                    kca.getDescripcion().toUpperCase(),
                    kca.getObservaciones().toUpperCase(),
                    estatus);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public int editKcatalogo(Kcatalogo kca) {
        valor = 0;
        sql = "UPDATE kcatalogo SET idcuestionario = ?, catalogo = ?, descripcorta = ?, descripcion = ?, observaciones = ? WHERE idcuestionario||clavecatalogo||catalogo = ? ";
        try {
            valor = jdbcTemplate.update(sql,
                    kca.getCuestionario().getIdCuestionario(),
                    kca.getCatalogo().toUpperCase(),
                    kca.getDescripCorta().toUpperCase(),
                    kca.getDescripcion().toUpperCase(),
                    kca.getObservaciones().toUpperCase(),
                    kca.getLlave());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public Kcatalogo getKcatalogo(String llave) {

        Kcatalogo kca = null;
        sql = "select kca.idcuestionario,\n"
                + "       (select cu.cuestionario from cuestionario cu where cu.idcuestionario = kca.idcuestionario) cuestionario,\n"
                + "       kca.catalogo,\n"
                + "       kca.clavecatalogo, \n"
                + "       kca.descripcorta ,\n"
                + "       kca.descripcion, \n"
                + "       kca.observaciones, \n"
                + "       kca.idestatus \n"
                + "       from kcatalogo kca WHERE kca.idcuestionario||kca.clavecatalogo||kca.catalogo ='" + llave + "'";

        try {
            kca = jdbcTemplate.queryForObject(sql, new KcatalogoRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return kca;
    }

    @Override
    public int deleteKcatalogo(String llave, int opcion) {
        valor = 0;
        sql = "UPDATE kcatalogo SET IDESTATUS= ? where idcuestionario||clavecatalogo||catalogo = ?";
        try {
            valor = jdbcTemplate.update(sql, opcion, llave);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public List<Kcatalogo> getRecordsEncuesta(String catalogo) {
        sql = "select kca.idcuestionario,\n"
                + "       (select cu.cuestionario from cuestionario cu where cu.idcuestionario = kca.idcuestionario) cuestionario,\n"
                + "       kca.catalogo,\n"
                + "       kca.clavecatalogo, \n"
                + "       kca.descripcorta ,\n"
                + "       kca.descripcion, \n"
                + "       kca.observaciones, \n"
                + "       kca.idestatus \n"
                + "       from kcatalogo kca where kca.catalogo = ?\n"
                + "               order by 1";

        try {
            lista = jdbcTemplate.query(sql, new Object[]{catalogo}, new KcatalogoRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return lista;
    }

}
