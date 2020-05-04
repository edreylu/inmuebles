/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.service;

import com.app.inmuebles.formasMenu.FormasMenuServiceImpl;
import com.app.inmuebles.formasMenu.FormasMenu;
import java.util.ArrayList;
import java.util.List;
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
public class FormasMenuServiceImplTest {
    
    public FormasMenuServiceImplTest() {
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
     * Test of listAll method, of class FormasMenuServiceImpl.
     */
    @Test
    public void testListAll() {
        System.out.println("listAll");
        FormasMenuServiceImpl instance = new FormasMenuServiceImpl();
        List<FormasMenu> expResult = null;
        List<FormasMenu> result = instance.listAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listAllFathers method, of class FormasMenuServiceImpl.
     */
    @Test
    public void testListAllFathers() {
        System.out.println("listAllFathers");
        FormasMenuServiceImpl instance = new FormasMenuServiceImpl();
        List<FormasMenu> expResult = null;
        List<FormasMenu> result = instance.listAllFathers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFormasMenu method, of class FormasMenuServiceImpl.
     */
    @Test
    public void testAddFormasMenu() {
        System.out.println("addFormasMenu");
        FormasMenu formasMenu = null;
        FormasMenuServiceImpl instance = new FormasMenuServiceImpl();
        int expResult = 0;
        int result = instance.addFormasMenu(formasMenu);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFormasMenu method, of class FormasMenuServiceImpl.
     */
    @Test
    public void testGetFormasMenu() {
        System.out.println("getFormasMenu");
        int id = 0;
        FormasMenuServiceImpl instance = new FormasMenuServiceImpl();
        FormasMenu expResult = null;
        FormasMenu result = instance.getFormasMenu(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editFormasMenu method, of class FormasMenuServiceImpl.
     */
    @Test
    public void testEditFormasMenu() {
        System.out.println("editFormasMenu");
        FormasMenu formasMenu = null;
        FormasMenuServiceImpl instance = new FormasMenuServiceImpl();
        int expResult = 0;
        int result = instance.editFormasMenu(formasMenu);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteFormasMenu method, of class FormasMenuServiceImpl.
     */
    @Test
    public void testDeleteFormasMenu() {
        System.out.println("deleteFormasMenu");
        int id = 0;
        FormasMenuServiceImpl instance = new FormasMenuServiceImpl();
        int expResult = 0;
        int result = instance.deleteFormasMenu(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPermisoPantalla method, of class FormasMenuServiceImpl.
     */
    @Test
    public void testGetPermisoPantalla() {
        System.out.println("getPermisoPantalla");
        int noUsuario = 0;
        FormasMenuServiceImpl instance = new FormasMenuServiceImpl();
        List<String> expResult = null;
        List<String> result = instance.getPermisoPantalla(noUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMenu method, of class FormasMenuServiceImpl.
     */
    @Test
    public void testGetMenu() {
        System.out.println("getMenu");
        int noUsuario = 0;
        FormasMenuServiceImpl instance = new FormasMenuServiceImpl();
        List<FormasMenu> expResult = null;
        List<FormasMenu> result = instance.getMenu(noUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existsHijos method, of class FormasMenuServiceImpl.
     */
    @Test
    public void testExistsHijos() {
        System.out.println("existsHijos");
        int clave = 0;
        FormasMenuServiceImpl instance = new FormasMenuServiceImpl();
        boolean expResult = false;
        boolean result = instance.existsHijos(clave);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existsRolFormas method, of class FormasMenuServiceImpl.
     */
    @Test
    public void testExistsRolFormas() {
        System.out.println("existsRolFormas");
        int noForma = 0;
        FormasMenuServiceImpl instance = new FormasMenuServiceImpl();
        boolean expResult = false;
        boolean result = instance.existsRolFormas(noForma);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
