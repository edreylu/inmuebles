/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.roles;

import com.app.inmuebles.formasMenu.FormasMenu;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *
 * @author usuario
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolFormas {
  List<FormasMenu> formas = new ArrayList<>();
  String rol;
}