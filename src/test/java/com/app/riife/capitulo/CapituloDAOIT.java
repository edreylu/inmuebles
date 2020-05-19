/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.capitulo;

import com.app.riife.TestDataBaseConfig;
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
public class CapituloDAOIT {
    @Autowired
    CapituloDAO capituloDAO;
    
    public CapituloDAOIT() {
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
     * Test of getRecords method, of class CapituloDAO.
     */
    @Test
    public void testGetRecords() {
        System.out.println("getRecords");
        List<Capitulo> result = capituloDAO.getRecords();
        assertTrue(result.size() > 0);
    }

    /**
     * Test of getRecordsByCuestionario method, of class CapituloDAO.
     
    @Test
    public void testGetRecordsByCuestionario() {
        System.out.println("getRecordsByCuestionario");
        int idCuestionario = 0;
        CapituloDAO instance = new CapituloDAOImpl();
        List<Capitulo> expResult = null;
        List<Capitulo> result = instance.getRecordsByCuestionario(idCuestionario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCapitulo method, of class CapituloDAO.
     
    @Test
    public void testAddCapitulo() {
        System.out.println("addCapitulo");
        Capitulo ca = null;
        CapituloDAO instance = new CapituloDAOImpl();
        int expResult = 0;
        int result = instance.addCapitulo(ca);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editCapitulo method, of class CapituloDAO.
     
    @Test
    public void testEditCapitulo() {
        System.out.println("editCapitulo");
        Capitulo ca = null;
        CapituloDAO instance = new CapituloDAOImpl();
        int expResult = 0;
        int result = instance.editCapitulo(ca);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCapitulo method, of class CapituloDAO.
     
    @Test
    public void testGetCapitulo() {
        System.out.println("getCapitulo");
        int id = 0;
        CapituloDAO instance = new CapituloDAOImpl();
        Capitulo expResult = null;
        Capitulo result = instance.getCapitulo(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCapitulo method, of class CapituloDAO.
     
    @Test
    public void testDeleteCapitulo() {
        System.out.println("deleteCapitulo");
        int id = 0;
        int opcion = 0;
        CapituloDAO instance = new CapituloDAOImpl();
        int expResult = 0;
        int result = instance.deleteCapitulo(id, opcion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    */
    
}
