/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.capitulo;

import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.usuario.Usuario;
import com.app.inmuebles.util.Mensaje;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author Edward Reyes
 */
public class CapituloServiceIT {
    
    CapituloDAO capituloDao;
    CapituloService capituloService;
    public CapituloServiceIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        capituloDao = Mockito.mock(CapituloDAO.class);
        capituloService = Mockito.mock(CapituloService.class);
    }
    
    @After
    public void tearDown() {
    }

    

    /**
     * Test of addCapitulo method, of class CapituloService.
     */
    @Test
    public void testAddCapitulo() {
        System.out.println("addCapitulo");
        Capitulo capitulo = new Capitulo(new Cuestionario(), Integer.MIN_VALUE, "EDEDED", 0, null, null, new Usuario(), "ED", null);
        Mensaje expResult = new Mensaje();
        expResult.setResult(1);
        Mensaje result = capituloService.addCapitulo(capitulo);
        assertEquals(expResult.getResult(), result.getResult());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCapitulo method, of class CapituloService.
     */
    @Test
    public void testGetCapitulo() {
        System.out.println("getCapitulo");
        int id = 0;
        Capitulo expResult = null;
        Capitulo result = capituloService.getCapitulo(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editCapitulo method, of class CapituloService.
     */
    @Test
    public void testEditCapitulo() {
        System.out.println("editCapitulo");
        Capitulo capitulo = null;
        Mensaje expResult = null;
        Mensaje result = capituloService.editCapitulo(capitulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCapitulo method, of class CapituloService.
     */
    @Test
    public void testDeleteCapitulo() {
        System.out.println("deleteCapitulo");
        int id = 0;
        int opcion = 0;
        Mensaje expResult = null;
        Mensaje result = capituloService.deleteCapitulo(id, opcion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listByCuestionario method, of class CapituloService.
     */
    @Test
    public void testListByCuestionario() {
        System.out.println("listByCuestionario");
        int idCuestionario = 0;
        List<Capitulo> expResult = null;
        List<Capitulo> result = capituloService.listByCuestionario(idCuestionario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
