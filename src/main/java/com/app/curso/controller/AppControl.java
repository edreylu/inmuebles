package com.app.curso.controller;

import com.app.curso.dao.UserDAO;
import com.app.curso.model.Curso;
import com.app.curso.model.CursoSede;
import com.app.curso.model.Disponibilidad;
import com.app.curso.model.Login;
import com.app.curso.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AppControl {
    @Autowired
    HttpSession session;
    @Autowired
    UserDAO udao;
    @Value("${google.recaptcha.secret}")
    private String key;
    //accesos a aplicacion desde el login hacia el menu cuando si accede y 403 cuando no hay permisos.
    @GetMapping("/")
    public String main(Model model) {
		return "index";
    }
    
    @GetMapping("/login")
    public String index(Model model) {
        return session.getAttribute("user")==null?"login":"redirect:/menu";
    }
    
    @PostMapping(value = "/loginProcess")
  public String login(@ModelAttribute("login") Login login) {
      
        System.out.println("com.app.curso.controller.AppControl.login() rfc:"+login.getRfc()+" curp:"+login.getCurp());
    boolean isValidUser = false;
    isValidUser= udao.validaDocente(login.getRfc(), login.getCurp());
    if (isValidUser) {
      User us = udao.generaDatosDocente(login.getRfc(), login.getCurp());
      session.setAttribute("user", us);
      System.out.println("com.app.curso.controller.AppControl.login()"+us.getCurp());
    }

    return isValidUser ? "redirect:/menu" : "redirect:/login?error=true";
  }
  
    @GetMapping("/menu")
    public String menu() {
        return session.getAttribute("user")==null?"redirect:/login":"/menu";
    }

    @GetMapping("/403")
    public String Error403() {
        return "403";
    }
    
    @GetMapping("/logout")
    public String logout() {
        System.out.println("com.app.curso.controller.AppControl.logout()");
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/";
    }
    
    //cursos y adelante
    
    @GetMapping("/curso")
    public String listar(Model model) throws SQLException{
    Curso curso = new Curso();
    CursoSede cursosede = new CursoSede();
    Disponibilidad disponibilidad = new Disponibilidad();
    curso = udao.traeCurso();
    cursosede = udao.traeSede();
    disponibilidad=udao.consultaDisponibilidad();
    model.addAttribute("curso",curso);
    model.addAttribute("cursosede",cursosede);
    model.addAttribute("disponibilidad",disponibilidad);
    return session.getAttribute("user")==null?"redirect:/login":"/curso";
    }
    
    @PostMapping(value = "/update")
    public String editar(User us){
        boolean isValid = false;
        User u =(User) session.getAttribute("user");
        System.out.println("com.app.curso.controller.UsersControl.editar()"+u.getCurp());
        u.setCorreo(us.getCorreo());
        u.setTelMovil(us.getTelMovil());
        u.setTelOtro(us.getTelOtro());
    int valor =udao.editaUsuario(u);
    if(valor >=1){
        isValid=true;
        System.out.println("se agrego registro: "+valor);
    }else{
    System.err.println("no se agrego registro");
    }
    
    return isValid? "redirect:/curso" : "redirect:/menu?error=true";
    }
    
    @GetMapping("/siguiente")
    public String siguiente(Model model) {
    return session.getAttribute("user")==null?"redirect:/login":"/siguiente";
    }
    
    @PostMapping(value = "/siguiente")
    public String siguiente(User us){
    User u =(User) session.getAttribute("user");
        u.setIdCurso(1);
        u.setIdSede(1);
    return "/subir";
    }
    
    @GetMapping("/subir")
    public String subir(Model model) {
    return session.getAttribute("user")==null?"redirect:/login":"/subir";
    }
    
    
    @PostMapping("upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<Object>("Seleccionar un archivo", HttpStatus.OK);
        }

        try {
            User u =(User) session.getAttribute("user");
            byte[] bytes = file.getBytes();
            u.setPdfIne(bytes);
            System.out.println("com.app.curso.controller.UsersControl.uploadFile()"+file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);
    }
    
    @PostMapping(value = "/finalizar")
    public String finalizar(User us){
        
        
    return "redirect:/subir";
    }
    
}
