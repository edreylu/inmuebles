/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.capitulo;

import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.usuario.Usuario;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Edward Reyes
 */
public class CapituloIT {
    
    public CapituloIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCuestionario method, of class Capitulo.
     */
    @Test
    public void testGetCuestionario() {
        System.out.println("getCuestionario");
        Capitulo instance = new Capitulo();
        Cuestionario expResult = null;
        Cuestionario result = instance.getCuestionario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdCapitulo method, of class Capitulo.
     */
    @Test
    public void testGetIdCapitulo() {
        System.out.println("getIdCapitulo");
        Capitulo instance = new Capitulo();
        Integer expResult = null;
        Integer result = instance.getIdCapitulo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCapitulo method, of class Capitulo.
     */
    @Test
    public void testGetCapitulo() {
        System.out.println("getCapitulo");
        Capitulo instance = new Capitulo();
        String expResult = "";
        String result = instance.getCapitulo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdEstatus method, of class Capitulo.
     */
    @Test
    public void testGetIdEstatus() {
        System.out.println("getIdEstatus");
        Capitulo instance = new Capitulo();
        int expResult = 0;
        int result = instance.getIdEstatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFechaRegistro method, of class Capitulo.
     */
    @Test
    public void testGetFechaRegistro() {
        System.out.println("getFechaRegistro");
        Capitulo instance = new Capitulo();
        Date expResult = null;
        Date result = instance.getFechaRegistro();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFechaRegistroStr method, of class Capitulo.
     */
    @Test
    public void testGetFechaRegistroStr() {
        System.out.println("getFechaRegistroStr");
        Capitulo instance = new Capitulo();
        String expResult = "";
        String result = instance.getFechaRegistroStr();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuarioRegistro method, of class Capitulo.
     */
    @Test
    public void testGetUsuarioRegistro() {
        System.out.println("getUsuarioRegistro");
        Capitulo instance = new Capitulo();
        Usuario expResult = null;
        Usuario result = instance.getUsuarioRegistro();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFechaModif method, of class Capitulo.
     */
    @Test
    public void testGetFechaModif() {
        System.out.println("getFechaModif");
        Capitulo instance = new Capitulo();
        String expResult = "";
        String result = instance.getFechaModif();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuarioModif method, of class Capitulo.
     */
    @Test
    public void testGetUsuarioModif() {
        System.out.println("getUsuarioModif");
        Capitulo instance = new Capitulo();
        Usuario expResult = null;
        Usuario result = instance.getUsuarioModif();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCuestionario method, of class Capitulo.
     */
    @Test
    public void testSetCuestionario() {
        System.out.println("setCuestionario");
        Cuestionario cuestionario = null;
        Capitulo instance = new Capitulo();
        instance.setCuestionario(cuestionario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdCapitulo method, of class Capitulo.
     */
    @Test
    public void testSetIdCapitulo() {
        System.out.println("setIdCapitulo");
        Integer idCapitulo = null;
        Capitulo instance = new Capitulo();
        instance.setIdCapitulo(idCapitulo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCapitulo method, of class Capitulo.
     */
    @Test
    public void testSetCapitulo() {
        System.out.println("setCapitulo");
        String capitulo = "";
        Capitulo instance = new Capitulo();
        instance.setCapitulo(capitulo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdEstatus method, of class Capitulo.
     */
    @Test
    public void testSetIdEstatus() {
        System.out.println("setIdEstatus");
        int idEstatus = 0;
        Capitulo instance = new Capitulo();
        instance.setIdEstatus(idEstatus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFechaRegistro method, of class Capitulo.
     */
    @Test
    public void testSetFechaRegistro() {
        System.out.println("setFechaRegistro");
        Date fechaRegistro = null;
        Capitulo instance = new Capitulo();
        instance.setFechaRegistro(fechaRegistro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFechaRegistroStr method, of class Capitulo.
     */
    @Test
    public void testSetFechaRegistroStr() {
        System.out.println("setFechaRegistroStr");
        String fechaRegistroStr = "";
        Capitulo instance = new Capitulo();
        instance.setFechaRegistroStr(fechaRegistroStr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsuarioRegistro method, of class Capitulo.
     */
    @Test
    public void testSetUsuarioRegistro() {
        System.out.println("setUsuarioRegistro");
        Usuario usuarioRegistro = null;
        Capitulo instance = new Capitulo();
        instance.setUsuarioRegistro(usuarioRegistro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFechaModif method, of class Capitulo.
     */
    @Test
    public void testSetFechaModif() {
        System.out.println("setFechaModif");
        String fechaModif = "";
        Capitulo instance = new Capitulo();
        instance.setFechaModif(fechaModif);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsuarioModif method, of class Capitulo.
     */
    @Test
    public void testSetUsuarioModif() {
        System.out.println("setUsuarioModif");
        Usuario usuarioModif = null;
        Capitulo instance = new Capitulo();
        instance.setUsuarioModif(usuarioModif);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
