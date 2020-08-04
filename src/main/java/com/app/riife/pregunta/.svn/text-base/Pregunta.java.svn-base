/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.pregunta;

import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.capitulo.Capitulo;
import com.app.riife.subCapitulo.SubCapitulo;
import com.app.riife.usuario.Usuario;
import java.util.Date;
import java.util.Objects;
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
    private String enCatalogo = "N";
    private String catalogo;
    private Integer claveCatalogo;
    private String especificarxCatalogo = "N";
    private String tipoDeDatoxCatalogo;
    private Integer longitudMaximaxCatalogo;
    private Integer decimalesLongMaxCat;
    private String subirImagen = "N";
    private String otroEspecificar = "N";
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
    
    
    public static Pregunta preguntaAddAjustes(Pregunta pregunta){
        
        if (Objects.equals(pregunta.getEspecificarPor(), "C"))
            pregunta.setEspecificarxCatalogo("S");
         else if (Objects.equals(pregunta.getEspecificarPor(), "O"))
            pregunta.setOtroEspecificar("S");
        
        return pregunta;
    }

    public static Pregunta preguntaEditAjustes(Pregunta pregunta){
        pregunta.setEspecificarPor(Objects.isNull(pregunta.getEspecificarPor()) ? "O" : pregunta.getEspecificarPor());
        pregunta.setOtroEspecificar("N");
        pregunta.setEspecificarxCatalogo("N");
        if (Objects.equals(pregunta.getEspecificarPor(),"C")) {
            pregunta.setEspecificarxCatalogo("S");
        } else if (Objects.equals(pregunta.getEspecificarPor(),"O")) {
            pregunta.setOtroEspecificar("S");
        }

        return pregunta;
    }

    @Override
    public String toString() {
        return "Pregunta{" + "cuestionario=" + cuestionario + ", idPregunta=" + idPregunta + ", pregunta=" + pregunta + ", idEstatus=" + idEstatus + ", capitulo=" + capitulo + ", subCapitulo=" + subCapitulo + ", ordenMostrar=" + ordenMostrar + ", inciso=" + inciso + ", opcion=" + opcion + ", instruccionesLlenado=" + instruccionesLlenado + ", opcionMultiple=" + opcionMultiple + ", enCatalogo=" + enCatalogo + ", catalogo=" + catalogo + ", claveCatalogo=" + claveCatalogo + ", especificarxCatalogo=" + especificarxCatalogo + ", tipoDeDatoxCatalogo=" + tipoDeDatoxCatalogo + ", longitudMaximaxCatalogo=" + longitudMaximaxCatalogo + ", decimalesLongMaxCat=" + decimalesLongMaxCat + ", subirImagen=" + subirImagen + ", otroEspecificar=" + otroEspecificar + ", tipoDeDatoOtro=" + tipoDeDatoOtro + ", longitudMaximaOtro=" + longitudMaximaOtro + ", decimalesLongMaxOtro=" + decimalesLongMaxOtro + ", idCuestionarioRef=" + idCuestionarioRef + ", idPreguntaRef=" + idPreguntaRef + ", usuarioRegistro=" + usuarioRegistro + ", fechaRegistro=" + fechaRegistro + ", usuarioModif=" + usuarioModif + ", fechaModif=" + fechaModif + ", especificarPor=" + especificarPor + '}';
    }
    
    
}