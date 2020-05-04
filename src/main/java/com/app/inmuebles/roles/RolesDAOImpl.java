/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.roles;

import com.app.inmuebles.formasMenu.FormasMenu;
import com.app.inmuebles.roles.Roles;
import com.app.inmuebles.roles.RolesRowMapper;
import java.util.ArrayList;
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
public class RolesDAOImpl implements RolesDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    List lista = null;
    String sql = "";

    @Override
    public List<Roles> getRegistros() {
        sql = "SELECT  NO_ROL, DESCRIPCION, INSERTAR, ACTUALIZAR, ELIMINAR, CONSULTAR, DESCARGAR \n"
                + "      FROM ROLES order by 1 ";

        try {
            lista = jdbcTemplate.query(sql, new RolesRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return lista;
    }

    @Override
    public int addRol(Roles rol) {
        int valor = 0;
        int noUsuario = jdbcTemplate.queryForObject("select nvl(max(no_rol),0)+ 1 from ROLES", Integer.class);
        rol.setNoRol(noUsuario);
        int estatus = 1;
        sql = "INSERT INTO ROLES (no_rol, descripcion, consultar, insertar, actualizar, eliminar, descargar, idestatus ) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            valor = jdbcTemplate.update(sql, rol.getNoRol(),
                    rol.getDescripcion().toUpperCase(),
                    rol.getConsultar(),
                    rol.getInsertar(),
                    rol.getActualizar(),
                    rol.getEliminar(),
                    rol.getDescargar(),
                    estatus);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public int editRol(Roles rol) {
        int valor = 0;
        sql = "UPDATE ROLES set descripcion = ? , insertar =  ? , actualizar = ? , eliminar = ? , consultar = ?, descargar = ?  "
                + " where no_rol = ? ";
        try {
            valor = jdbcTemplate.update(sql,
                    rol.getDescripcion().toUpperCase(),
                    rol.getInsertar(),
                    rol.getActualizar(),
                    rol.getEliminar(),
                    rol.getConsultar(),
                    rol.getDescargar(),
                    rol.getNoRol());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public Roles getRol(int id) {
        Roles rol = null;
        sql = "SELECT  NO_ROL, DESCRIPCION, INSERTAR, ACTUALIZAR, ELIMINAR, CONSULTAR, DESCARGAR \n"
                + "      FROM ROLES where no_rol =" + id;
        try {
            rol = jdbcTemplate.queryForObject(sql, new RolesRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return rol;
    }

    @Override
    public int deleteRol(int id) {
        int valor = 0;
        sql = "delete from ROLES where no_rol = ?";
        try {
            valor = jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public void assignFormaMenu(int rol, int forma) {
        sql = "INSERT INTO ROLES_FORMAS_MENU (no_rol, no_forma, prioridad, idestatus) "
                + "VALUES (?,?,?,?)";
        try {
            jdbcTemplate.update(sql, new Object[]{
                rol,
                forma, 0, 1});
        } catch (DataAccessException e) {
            System.err.print(e);
        }
    }

    @Override
    public void deleteFormaMenu(int rol) {
        sql = "DELETE FROM ROLES_FORMAS_MENU WHERE no_rol = ? ";
        try {
            jdbcTemplate.update(sql, new Object[]{
                rol});
        } catch (DataAccessException e) {
            System.err.print(e);
        }
    }

    @Override
    public int deleteRolUsuario(int id) {
        int valor = 0;
        sql = "DELETE FROM ROLES_USUARIOS WHERE no_usuario = ?";
        try {
            valor = jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    public boolean existsRolUsuarios(int noRol) {
        int valor = 0;
        sql = "  SELECT COUNT(1) "
                + "  FROM ROLES_USUARIOS "
                + "  WHERE NO_ROL =  ?  and IDESTATUS = 1 ";
        try {
            valor = jdbcTemplate.queryForObject(sql, new Object[]{noRol}, Integer.class);

        } catch (DataAccessException e) {
            System.err.print(e);
        }
        boolean existe = valor > 0;
        return existe;
    }

    @Override
    public boolean existsRolFormas(int noRol) {
        int valor = 0;
        sql = "  SELECT COUNT(1) "
                + "  FROM ROLES_FORMAS_MENU "
                + "  WHERE NO_ROL =  ? and IDESTATUS = 1 ";
        try {
            valor = jdbcTemplate.queryForObject(sql, new Object[]{noRol}, Integer.class);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        boolean existe = valor > 0;
        return existe;
    }

    @Override
    public List<FormasMenu> getRegistrosFormas(int noRol) {
        
        List<FormasMenu> listaFormas = new ArrayList<>();
        sql = "SELECT  ME.NO_FORMA AS ID_MENU , ME.TITULO AS DESCRIPCION, ME.URL AS ENLACE, ME.ICONO AS ICONO, ME.NO_FORMA_PADRE AS ID_MENU_PADRE,\n"
                + "                        (SELECT PER.NO_FORMA FROM ROLES_FORMAS_MENU PER WHERE PER.NO_FORMA = ME.NO_FORMA AND PER.NO_ROL= ? ) ROL\n"
                + "                FROM FORMAS_MENU ME  \n"
                + "               WHERE (ME.NO_FORMA_PADRE IS NOT NULL and ME.NO_FORMA_PADRE <>0)\n"
                + "               \n"
                + "                ORDER BY 1";

        try {
            listaFormas = jdbcTemplate.query(sql, new Object[]{noRol}, (rs) -> {
                List<FormasMenu> lista = new ArrayList<>();
                while (rs.next()) {
                    FormasMenu menu = new FormasMenu();
                    menu.setNoFormaMenu(rs.getShort("ID_MENU"));
                    menu.setTitulo(rs.getString("DESCRIPCION"));
                    menu.setUrl(rs.getString("ENLACE"));
                    menu.setIcono(rs.getString("ICONO"));
                    menu.setNoFormaPadre(rs.getShort("ID_MENU_PADRE"));
                    menu.setMenuSeleccionado(rs.getBoolean("ROL"));
                    lista.add(menu);
                }
                return lista;
            });
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return listaFormas;
    }

}
