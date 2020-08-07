/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.roles;

import com.app.riife.formasMenu.FormasMenu;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author usuario
 */
public class RolFormas {
  List<FormasMenu> formas = new ArrayList<>();
  String rol;
  int noRol;

    public RolFormas(String rol, int noRol) {
        this.rol = rol;
        this.noRol = noRol;
    }

    public RolFormas() {
    }

    public List<FormasMenu> getFormas() {
        return formas;
    }

    public void setFormas(List<FormasMenu> formas) {
        this.formas = formas;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getNoRol() {
        return noRol;
    }

    public void setNoRol(int noRol) {
        this.noRol = noRol;
    }
  
  
}