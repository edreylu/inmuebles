/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.formasMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author usuario
 */
@Repository
public class FormasMenuDAOImpl implements FormasMenuDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private List lista = null;
    private String sql = "";
    private final int estatus = 1;

    @Override
    public List<FormasMenu> getRecords() {
        sql = "SELECT ME.NO_FORMA as ID_MENU,ME.TITULO as DESCRIPCION,ME.URL as ENLACE,ME.ICONO as ICONO,\n"
                + "                        DECODE((SELECT ME1.NO_FORMA_PADRE  \n"
                + "                                    FROM FORMAS_MENU ME1 \n"
                + "                                WHERE ME1.NO_FORMA = ME.NO_FORMA_PADRE),null,'PADRE',\n"
                + "                        (SELECT ME1.TITULO \n"
                + "                                    FROM FORMAS_MENU ME1 \n"
                + "                                    WHERE ME1.NO_FORMA = ME.NO_FORMA_PADRE)) as PADRE,\n"
                + "                                    ME.NO_FORMA_PADRE AS ID_MENU_PADRE \n"
                + "                             FROM FORMAS_MENU me WHERE ME.NO_FORMA<>0 order by 1";

        try {
            lista = jdbcTemplate.query(sql, new FormasMenuRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return lista;
    }

    @Override
    public List<FormasMenu> getRecordsFather() {
        List listaFormas = null;
        sql = "SELECT NO_FORMA as ID_MENU, TITULO as DESCRIPCION,'' as ENLACE,'' as ICONO, '' as PADRE, '' AS ID_MENU_PADRE\n"
                + "              FROM FORMAS_MENU            \n"
                + "              WHERE (NO_FORMA_PADRE IS NULL OR NO_FORMA_PADRE =0)\n"
                + "              AND NO_TIPO_FORMA=1 AND NO_FORMA<>0 order by 1";

        try {
            listaFormas = jdbcTemplate.query(sql, new FormasMenuRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return listaFormas;
    }

    @Override
    public int addFormaMenu(FormasMenu menu) {
        int valor = 0;
        int noUsuario = jdbcTemplate.queryForObject("select nvl(max(no_forma),0)+ 1 from formas_menu ", Integer.class);
        menu.setNoFormaMenu(noUsuario);
        int tipoForma = menu.getNoFormaPadre() > 0 ? 3 : 1;
        sql = "INSERT INTO FORMAS_MENU (NO_FORMA, TITULO ,URL ,ICONO ,IDESTATUS,NO_TIPO_FORMA,NO_FORMA_PADRE ) "
                + "VALUES (?,?,?,?,?,?,?)";
        try {
            valor = jdbcTemplate.update(sql, menu.getNoFormaMenu(),
                    Objects.nonNull(menu.getTitulo()) ? menu.getTitulo().toUpperCase() : menu.getTitulo(),
                    menu.getUrl(),
                    menu.getIcono(),
                    estatus,
                    tipoForma,
                    menu.getNoFormaPadre());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public int editFormaMenu(FormasMenu menu) {
        int valor = 0;
        int tipoForma = menu.getNoFormaPadre() > 0 ? 3 : 1;
        sql = "UPDATE FORMAS_MENU set TITULO = ? ,URL = ? ,ICONO = ? ,NO_TIPO_FORMA = ? , NO_FORMA_PADRE = ? "
                + " where NO_FORMA = ? ";
        try {
            valor = jdbcTemplate.update(sql, Objects.nonNull(menu.getTitulo()) 
                    ? menu.getTitulo().toUpperCase() : menu.getTitulo(),
                    menu.getUrl(),
                    menu.getIcono(),
                    tipoForma,
                    menu.getNoFormaPadre(),
                    menu.getNoFormaMenu());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public FormasMenu getFormaMenu(int id) {

        FormasMenu menu = null;
        sql = "SELECT ME.NO_FORMA as ID_MENU,ME.TITULO as DESCRIPCION,ME.URL as ENLACE,ME.ICONO as ICONO,\n"
                + "                        DECODE((SELECT ME1.NO_FORMA_PADRE  \n"
                + "                                    FROM FORMAS_MENU ME1 \n"
                + "                                WHERE ME1.NO_FORMA = ME.NO_FORMA_PADRE),null,'PADRE',\n"
                + "                        (SELECT ME1.TITULO \n"
                + "                                    FROM FORMAS_MENU ME1 \n"
                + "                                    WHERE ME1.NO_FORMA = ME.NO_FORMA_PADRE)) as PADRE,\n"
                + "                                    ME.NO_FORMA_PADRE AS ID_MENU_PADRE \n"
                + "                             FROM FORMAS_MENU me WHERE ME.NO_FORMA=" + id;
        try {
            menu = jdbcTemplate.queryForObject(sql, new FormasMenuRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return menu;
    }

    @Override
    public int deleteFormaMenu(int id) {
        int valor = 0;
        sql = "DELETE FROM FORMAS_MENU WHERE NO_FORMA = ? ";
        try {
            valor = jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public List<String> getPermissionToPages(int noUsuario) {
        
        List<String> permisos = new ArrayList<>();
        sql = "  select fm.url \n"
                + "from formas_menu fm,\n"
                + "     roles_formas_menu rfm,\n"
                + "     roles_usuarios ru\n"
                + "     where rfm.no_forma = fm.no_forma\n"
                + "     and ru.no_rol = rfm.no_rol\n"
                + "     and ru.no_usuario= ? and ru.idestatus=1 ";
        try {
            permisos = jdbcTemplate.query(sql, new Object[]{noUsuario}, 
                    (rs) -> {
                List<String> per = new ArrayList<>();
                while (rs.next()) {
                    per.add(rs.getString("url"));
                }
                return per;
            });
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return permisos;
    }

    @Override
    public List<FormasMenu> getMenu(int noUsuario) {
        List<FormasMenu> listaMenu = new ArrayList<>();
        sql = "SELECT ME.TITULO, \n"
                + "                           ME.ICONO, \n"
                + "                           ME.URL, \n"
                + "                           (SELECT me2.TITULO \n"
                + "                              FROM FORMAS_MENU ME2 \n"
                + "                             WHERE ME.NO_FORMA_PADRE = ME2.NO_FORMA) nombre_papa,\n"
                + "                           (SELECT me2.ICONO \n"
                + "                              FROM FORMAS_MENU ME2 \n"
                + "                             WHERE ME.NO_FORMA_PADRE = ME2.NO_FORMA) icono_papa\n"
                + "                     from USUARIOS ua, \n"
                + "                          ROLES ro, \n"
                + "                          FORMAS_MENU ME,\n"
                + "                          ROLES_USUARIOS RU,\n"
                + "                          ROLES_FORMAS_MENU rfm\n"
                + "                      WHERE UA.NO_USUARIO =  RU.NO_USUARIO\n"
                + "                      AND   RU.NO_ROL = RO.NO_ROL\n"
                + "                      AND   ME.NO_FORMA = RFM.NO_FORMA\n"
                + "                      AND   RO.NO_ROL   = RFM.NO_ROL\n"
                + "                      AND   RU.IDESTATUS   = ?\n"
                + "                      AND   UA.IDESTATUS   = ?\n"
                + "                      and   RU.NO_USUARIO =" + noUsuario
                + "                      AND   NVL(ME.NO_FORMA_PADRE,0) > 0 \n"
                + "                      order by 4, 1";

        System.out.println(sql);
        try {
            listaMenu = jdbcTemplate.query(sql, new Object[]{estatus, estatus}, (rs) -> {
                List<FormasMenu> listaFormas = new ArrayList<>();
                while (rs.next()) {
                    FormasMenu menu = new FormasMenu();
                    menu.setTitulo(" " + rs.getString(1));
                    menu.setIcono(rs.getString(2));
                    menu.setUrl(rs.getString(3));
                    menu.setNombrePapa(" " + rs.getString(4));
                    menu.setIconoPapa(rs.getString(5));
                    listaFormas.add(menu);
                }
                return listaFormas;
            });
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return listaMenu;
    }

    @Override
    public boolean existsHijos(int clave) {
        int valor = 0;
        sql = "  SELECT COUNT(*) "
                + "  FROM FORMAS_MENU "
                + "  WHERE NO_FORMA_PADRE =  ?   ";
        try {
            valor = jdbcTemplate.queryForObject(sql, new Object[]{clave}, Integer.class);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        boolean existe = valor > 0;
        return existe;
    }

    @Override
    public boolean existsRolFormas(int noForma) {
        int valor = 0;
        sql = "  SELECT COUNT(*) "
                + "  FROM roles_formas_menu "
                + "  WHERE no_forma =  ?   ";
        try {
            valor = jdbcTemplate.queryForObject(sql, new Object[]{noForma}, Integer.class);

        } catch (DataAccessException e) {
            System.err.print(e);
        }
        boolean existe = valor > 0;
        return existe;
    }

}
