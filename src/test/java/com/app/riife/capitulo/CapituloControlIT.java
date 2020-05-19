/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.capitulo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Edward Reyes
 */
public class CapituloControlIT {
    
    public CapituloControlIT() {
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
     * Test of listar method, of class CapituloControl.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        Model model = null;
        CapituloControl instance = null;
        String expResult = "";
        String result = instance.listar(model);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregar method, of class CapituloControl.
     */
    @Test
    public void testAgregar_Model() {
        System.out.println("agregar");
        Model model = null;
        CapituloControl instance = null;
        String expResult = "";
        String result = instance.agregar(model);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregar method, of class CapituloControl.
     */
    @Test
    public void testAgregar_Capitulo_RedirectAttributes() {
        System.out.println("agregar");
        Capitulo ca = null;
        RedirectAttributes redirectAttrs = null;
        CapituloControl instance = null;
        String expResult = "";
        String result = instance.agregar(ca, redirectAttrs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editar method, of class CapituloControl.
     */
    @Test
    public void testEditar_int_Model() {
        System.out.println("editar");
        int id = 0;
        Model model = null;
        CapituloControl instance = null;
        String expResult = "";
        String result = instance.editar(id, model);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editar method, of class CapituloControl.
     */
    @Test
    public void testEditar_3args() {
        System.out.println("editar");
        int id = 0;
        Capitulo ca = null;
        RedirectAttributes redirectAttrs = null;
        CapituloControl instance = null;
        String expResult = "";
        String result = instance.editar(id, ca, redirectAttrs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class CapituloControl.
     */
    @Test
    public void testEliminar() {
        System.out.println("eliminar");
        int id = 0;
        int idestatus = 0;
        RedirectAttributes redirectAttrs = null;
        CapituloControl instance = null;
        String expResult = "";
        String result = instance.eliminar(id, idestatus, redirectAttrs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
