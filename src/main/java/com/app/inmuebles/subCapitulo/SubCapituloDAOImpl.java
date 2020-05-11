/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.subCapitulo;

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
public class SubCapituloDAOImpl implements SubCapituloDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private List lista = null;
    private String sql = "";
    private final int estatus = 1;

    @Override
    public List<SubCapitulo> getRecords() {
        sql = "select sc.idcuestionario,\n"
                + "       (select cu.cuestionario from cuestionario cu where cu.idcuestionario = sc.idcuestionario) cuestionario,\n"
                + "       sc.idsubcapitulo, \n"
                + "       sc.subcapitulo, \n"
                + "       sc.idestatus \n"
                + "       from subcapitulo sc\n"
                + "               order by 1";
        try {
            lista = jdbcTemplate.query(sql, new SubCapituloRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return lista;
    }

    @Override
    public List<SubCapitulo> getRecordsByCuestionario(int idCuestionario) {
        sql = "select sc.idcuestionario,\n"
                + "       (select cu.cuestionario from cuestionario cu where cu.idcuestionario = sc.idcuestionario) cuestionario,\n"
                + "       sc.idsubcapitulo, \n"
                + "       sc.subcapitulo, \n"
                + "       sc.idestatus \n"
                + "       from subcapitulo sc where sc.idcuestionario = ?\n"
                + "               order by 1";
        try {
            lista = jdbcTemplate.query(sql, new Object[]{idCuestionario}, new SubCapituloRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return lista;
    }

    @Override
    public int addSubCapitulo(SubCapitulo sc) {
        int valor = 0;
        int idSubCapitulo = jdbcTemplate.queryForObject("select nvl(max(idsubcapitulo),0)+ 1 from subcapitulo", Integer.class);
        sc.setIdSubCapitulo(idSubCapitulo);
        sql = "INSERT INTO subcapitulo (idcuestionario, "
                + "idsubcapitulo, "
                + "subcapitulo,"
                + "idestatus) "
                + "VALUES (?,?,?,?)";
        try {
            valor = jdbcTemplate.update(sql,
                    sc.getCuestionario().getIdCuestionario(),
                    sc.getIdSubCapitulo(),
                    sc.getSubCapitulo().toUpperCase(),
                    estatus);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public int editSubCapitulo(SubCapitulo sc) {
        int valor = 0;
        sql = "UPDATE subcapitulo SET idcuestionario = ?, subcapitulo = ? WHERE idsubcapitulo = ? ";
        try {
            valor = jdbcTemplate.update(sql,
                    sc.getCuestionario().getIdCuestionario(),
                    sc.getSubCapitulo().toUpperCase(),
                    sc.getIdSubCapitulo());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public SubCapitulo getSubCapitulo(int id) {

        SubCapitulo sc = null;
        sql = "select sc.idcuestionario,\n"
                + "       (select cu.cuestionario from cuestionario cu where cu.idcuestionario = sc.idcuestionario) cuestionario,\n"
                + "       sc.idsubcapitulo, \n"
                + "       sc.subcapitulo, \n"
                + "       sc.idestatus \n"
                + "       from subcapitulo sc WHERE sc.idsubcapitulo =" + id;
        try {
            sc = jdbcTemplate.queryForObject(sql, new SubCapituloRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return sc;
    }

    @Override
    public int deleteSubCapitulo(int id, int opcion) {
        int valor = 0;
        sql = "UPDATE subcapitulo SET IDESTATUS= ? where idsubcapitulo = ?";

        try {
            valor = jdbcTemplate.update(sql, opcion, id);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

}
