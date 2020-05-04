/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.pregunta;

import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.capitulo.Capitulo;
import com.app.inmuebles.subCapitulo.SubCapitulo;
import com.app.inmuebles.usuario.Usuario;
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
public class Pregunta {
    private Cuestionario cuestionario = new Cuestionario();
    private int idPregunta;
    private String pregunta;
    private int idEstatus;
    private Capitulo capitulo = new Capitulo();
    private SubCapitulo subCapitulo = new SubCapitulo();
    private int ordenMostrar;
    private String inciso;
    private String opcion;
    private String instruccionesLlenado;
    private String opcionMultiple;
    private String enCatalogo;
    private String catalogo;
    private Integer claveCatalogo;
    private String especificarxCatalogo;
    private String tipoDeDatoxCatalogo;
    private Integer longitudMaximaxCatalogo;
    private Integer decimalesLongMaxCat;
    private String subirImagen;
    private String otroEspecificar;
    private String tipoDeDatoOtro;
    private Integer longitudMaximaOtro;
    private Integer decimalesLongMaxOtro;
    private Integer idCuestionarioRef;
    private Integer idPreguntaRef;
    private Usuario usuarioRegistro = new Usuario();
    private Date fechaRegistro;
    private Usuario usuarioModif = new Usuario();
    private Date fechaModif;
    private String especificarPor;
}