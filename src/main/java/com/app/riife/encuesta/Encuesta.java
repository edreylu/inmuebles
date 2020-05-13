/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.encuesta;

import com.app.riife.usuario.Usuario;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Encuesta {
 private Integer idCuestionario;
 private String cuestionario;
 private Date fechaRegistro;
 private String fechaRegistroStr;
 private Usuario usuarioRegistro = new Usuario();
 private String fechaModif;
 private Usuario usuarioModif = new Usuario();
 private int idEstatus;
}
