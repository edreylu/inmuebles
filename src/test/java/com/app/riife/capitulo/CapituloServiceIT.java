/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.capitulo;

import com.app.riife.TestDataBaseConfig;
import com.app.riife.util.Mensaje;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Edward Reyes
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
public class CapituloServiceIT {
    @Autowired
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of listAll method, of class CapituloService.
     */
    @Test
    public void testListAll() {
        System.out.println("listAll");
        List<Capitulo> result = capituloService.listAll();
        assertTrue(result.size() >= 1);
    }

    /**
     * Test of addCapitulo method, of class CapituloService.
     */
    @Test
    public void testAddCapitulo() {
        System.out.println("addCapitulo");
        Capitulo capitulo = null;
        Mensaje expResult = null;
        Mensaje result = capituloService.add(capitulo);
        assertEquals(expResult, result);
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
        Capitulo result = capituloService.get(id);
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
        Mensaje result = capituloService.update(capitulo);
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
        Mensaje result = capituloService.delete(id, opcion);
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
