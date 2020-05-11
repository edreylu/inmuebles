/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.usuario;

import com.app.inmuebles.inicio.Login;
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
public class UsuarioDAOImpl implements UsuarioDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql = "";
    private final int estatus = 1;
    private List lista = null;
    
    @Override
    public Usuario existsUsuario(Login login) {
        Usuario us = null;
        sql = "  SELECT NO_USUARIO, NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO, CLAVE, CORREO, TELEFONO "
                + "  FROM USUARIOS "
                + " WHERE CLAVE = ? "
                + "  AND  ENC.DESENCRIP(PASAPORTE) =  ?  AND IDESTATUS = ? ";
        try {
            us = jdbcTemplate.query(sql, new Object[]{login.getUsuario(), login.getContraseña(), estatus}, (rs) -> {
                Usuario usa = new Usuario();
                while (rs.next()) {
                    usa.setNoUsuario(rs.getInt(1));
                    usa.setNombre(rs.getString(2));
                    usa.setApellidoPaterno(rs.getString(3));
                    usa.setApellidoMaterno(rs.getString(4));
                    usa.setNomUsuario(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                    usa.setClave(rs.getString(5));
                    usa.setCorreo(rs.getString(6));
                    usa.setTelefono(rs.getString(7));
                }
                return usa;
            }
            );
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return us;
    }

    @Override
    public List<Usuario> getRecords() {
        sql = "select ua.no_usuario as no_usuario, ua.CLAVE as clave , ua.correo as correo, ua.nombre as nombre, \n"
                + "                  UA.APELLIDO_PATERNO AS APELLIDO_PATERNO, UA.APELLIDO_MATERNO AS APELLIDO_MATERNO, UA.TELEFONO AS TELEFONO,UA.TELEFONO2 AS TELEFONO2, \n"
                + "               (SELECT RU.NO_ROL FROM ROLES_USUARIOS RU WHERE RU.NO_USUARIO=UA.NO_USUARIO AND RU.idestatus =?) AS rol,\n"
                + "               (SELECT RO.DESCRIPCION FROM ROLES_USUARIOS RU, ROLES RO\n"
                + "               WHERE RU.NO_USUARIO=UA.NO_USUARIO\n"
                + "               AND RO.NO_ROL=RU.NO_ROL\n"
                + "               AND RU.idestatus =?\n"
                + "               AND RO.idestatus =?) AS nombreRol, UA.idestatus\n"
                + "               FROM USUARIOS UA\n"
                + "               WHERE UA.idestatus in (?,?)\n"
                + "               order by 1";
        try {
            //listaPersonas = jdbctemplate.query(query,new BeanPropertyRowMapper(Usuarios.class)); para clases bien ordenadas
            lista = jdbcTemplate.query(sql, new Object[]{1, 1, 1, 1, 2}, new UsuarioRowMapper());

        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return lista;
    }

    @Override
    public int addUsuario(Usuario usuario) {
        int valor = 0;
        int noUsuario = jdbcTemplate.queryForObject("select nvl(max(no_usuario),0)+ 1 from USUARIOS", Integer.class);
        usuario.setNoUsuario(noUsuario);
        sql = "INSERT INTO USUARIOS (NO_USUARIO, CLAVE, PASAPORTE, NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO, "
                + "   CORREO, TELEFONO, IDESTATUS, FECHA_AUDITORIA,TELEFONO2) "
                + "VALUES (?,?,ENC.ENCRIP(?),?,?,?,?,?,?,SYSDATE,?)";

        try {
            valor = jdbcTemplate.update(sql, usuario.getNoUsuario(),
                    usuario.getClave().toUpperCase(),
                    usuario.getPasaporte().toUpperCase(),
                    usuario.getNombre().toUpperCase(),
                    usuario.getApellidoPaterno().toUpperCase(),
                    usuario.getApellidoMaterno().toUpperCase(),
                    usuario.getCorreo(),
                    usuario.getTelefono(),
                    estatus,
                    usuario.getTelefono2());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public int editUsuario(Usuario usuario) {
        int valor = 0;
        sql = "UPDATE USUARIOS set CLAVE = ?, NOMBRE = ? ,"
                + " APELLIDO_PATERNO = ? ,APELLIDO_MATERNO = ? ,CORREO = ? , TELEFONO = ?, TELEFONO2 = ? "
                + " where NO_USUARIO = ? ";
        try {
            valor = jdbcTemplate.update(sql, usuario.getClave().toUpperCase(),
                    usuario.getNombre().toUpperCase(),
                    usuario.getApellidoPaterno().toUpperCase(),
                    usuario.getApellidoMaterno().toUpperCase(),
                    usuario.getCorreo(),
                    usuario.getTelefono(),
                    usuario.getTelefono2(),
                    usuario.getNoUsuario());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public Usuario getUsuario(int id) {

        Usuario usuario = null;
        sql = "select UA.no_usuario,UA.clave,UA.nombre,UA.apellido_paterno,UA.apellido_materno,UA.correo,"
                + "          UA.telefono,UA.telefono2,(SELECT RU.NO_ROL FROM ROLES_USUARIOS RU WHERE RU.NO_USUARIO=UA.NO_USUARIO AND RU.idestatus =1) rol, '' nombreRol, idestatus from usuarios UA where no_usuario =" + id;
        try {
            usuario = jdbcTemplate.queryForObject(sql, new UsuarioRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return usuario;
    }

    @Override
    public int deleteUsuario(int id, int opcion) {
        int valor = 0;
        sql = "UPDATE usuarios SET IDESTATUS= ? where NO_USUARIO = ?";
        try {
            valor = jdbcTemplate.update(sql, opcion, id);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public int assignRolToUsuario(Usuario usuario) {
        int valor = 0;
        Boolean existe = existsRolUsuarioAsignar(usuario);
        if (existe) {
            sql = "UPDATE ROLES_USUARIOS SET IDESTATUS = ? WHERE no_usuario = ? AND no_rol = ?";
        } else {
            sql = "INSERT INTO ROLES_USUARIOS (idestatus,no_usuario, no_rol) "
                    + "VALUES (?,?,?)";
        }
        try {
            valor = jdbcTemplate.update(sql, estatus, usuario.getNoUsuario(), usuario.getRol().getNoRol());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public int deleteRolToUsuario(int id) {
        int valor = 0;
        sql = "UPDATE ROLES_USUARIOS set IDESTATUS = 2 WHERE no_usuario = ?";
        try {
            valor = jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public int resetPasaporte(int us) {
        int valor = 0;
        sql = "UPDATE USUARIOS set PASAPORTE = ENC.ENCRIP(clave) "
                + " where NO_USUARIO = ? ";
        try {
            valor = jdbcTemplate.update(sql, new Object[]{us});
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public int changePasaporte(int noUsuario, String contraseñaNueva) {
        int valor = 0;
        sql = "UPDATE USUARIOS set PASAPORTE = ENC.ENCRIP(?) "
                + " where NO_USUARIO = ? ";
        try {
            valor = jdbcTemplate.update(sql, new Object[]{contraseñaNueva, noUsuario});
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public boolean existsRolAssignedToUsuario(int noUsuario) {
        int valor = 0;
        sql = "  SELECT COUNT(1) "
                + "  FROM ROLES_USUARIOS "
                + "  WHERE NO_USUARIO =  ? AND IDESTATUS = ?  ";
        try {
            valor = jdbcTemplate.queryForObject(sql, new Object[]{noUsuario, estatus}, Integer.class);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        boolean existe = valor > 0;
        return existe;
    }

    public boolean existsRolUsuarioAsignar(Usuario usuario) {
        int valor = 0;
        sql = "  SELECT COUNT(1) "
                + "  FROM ROLES_USUARIOS "
                + "  WHERE NO_USUARIO =  ? AND NO_ROL = ? AND IDESTATUS = 2  ";
        try {
            valor = jdbcTemplate.queryForObject(sql, 
                    new Object[]{usuario.getNoUsuario(), usuario.getRol().getNoRol()}, Integer.class);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        boolean existe = valor > 0;
        return existe;
    }

}
