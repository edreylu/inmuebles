/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.capitulo;

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
public class CapituloDAOImpl implements CapituloDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private List lista = null;
    private String sql;
    
    @Override
    public List<Capitulo> getRecords() {
        sql = "select ca.idcuestionario, "
                + "(select cu.cuestionario from cuestionario cu where cu.idcuestionario = ca.idcuestionario) cuestionario,"
                + "ca.idcapitulo, "
                + "ca.capitulo,"
                + "ca.idestatus,"
                + "ca.fecharegistro,"
                + "ca.nousuarioregistro,"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = ca.nousuarioregistro) nomUsuarioRegistro,"
                + "ca.fechamodif,"
                + "ca.nousuariomodif,"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = ca.nousuariomodif) nomUsuarioModif "
                + " from capitulo ca\n"
                + "               order by 1";
        try {
            lista = jdbcTemplate.query(sql, new CapituloRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return lista;
    }
    
    @Override
    public List<Capitulo> getRecordsByCuestionario(int idCuestionario) {
        sql = "select ca.idcuestionario, "
                + "(select cu.cuestionario from cuestionario cu where cu.idcuestionario = ca.idcuestionario) cuestionario,"
                + "ca.idcapitulo, "
                + "ca.capitulo,"
                + "ca.idestatus,"
                + "ca.fecharegistro,"
                + "ca.nousuarioregistro,"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = ca.nousuarioregistro) nomUsuarioRegistro,"
                + "ca.fechamodif,"
                + "ca.nousuariomodif,"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = ca.nousuariomodif) nomUsuarioModif "
                + " from capitulo ca where ca.idcuestionario = ? \n"
                + "               order by 1";
        try {
            lista = jdbcTemplate.query(sql, new Object[]{idCuestionario}, new CapituloRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return lista;
    }
    
    @Override
    public int addCapitulo(Capitulo ca) {
        int valor = 0;
        int idCapitulo = jdbcTemplate.queryForObject("select nvl(max(idcapitulo),0)+ 1 from capitulo", Integer.class);
        ca.setIdCapitulo(idCapitulo);
        sql = "INSERT INTO capitulo (idcuestionario, "
                + "idcapitulo, "
                + "capitulo,"
                + "idestatus,"
                + "fecharegistro,"
                + "nousuarioregistro,"
                + "fechamodif,"
                + "nousuariomodif) "
                + "VALUES (?,?,?,?,SYSDATE,?,?,?)";
        try {
            valor = jdbcTemplate.update(sql,
                    ca.getCuestionario().getIdCuestionario(),
                    ca.getIdCapitulo(),
                    ca.getCapitulo().toUpperCase(),
                    1,
                    ca.getUsuarioRegistro().getNoUsuario(),
                    null,
                    null);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }
    
    @Override
    public int editCapitulo(Capitulo ca) {
        int valor = 0;
        sql = "UPDATE capitulo SET idcuestionario = ?, capitulo = ?, fechamodif = SYSDATE, nousuariomodif = ? WHERE idcapitulo = ? ";
        try {
            valor = jdbcTemplate.update(sql,
                    ca.getCuestionario().getIdCuestionario(),
                    ca.getCapitulo().toUpperCase(),
                    ca.getUsuarioModif().getNoUsuario(),
                    ca.getIdCapitulo());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }
    
    @Override
    public Capitulo getCapitulo(int id) {

        Capitulo ca = null;
        sql = "select ca.idcuestionario, "
                + "(select cu.cuestionario from cuestionario cu where cu.idcuestionario = ca.idcuestionario) cuestionario,"
                + "ca.idcapitulo, "
                + "ca.capitulo,"
                + "ca.idestatus,"
                + "ca.fecharegistro,"
                + "ca.nousuarioregistro,"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = ca.nousuarioregistro) nomUsuarioRegistro,"
                + "ca.fechamodif,"
                + "ca.nousuariomodif,"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = ca.nousuariomodif) nomUsuarioModif "
                + "from capitulo ca WHERE idcapitulo =" + id;
        try {
            ca = jdbcTemplate.queryForObject(sql, new CapituloRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return ca;
    }
    
    @Override
    public int deleteCapitulo(int id, int opcion) {
        int valor = 0;
        sql = "UPDATE capitulo SET IDESTATUS= ? where idcapitulo = ?";
        try {
            valor = jdbcTemplate.update(sql, opcion, id);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }


}
