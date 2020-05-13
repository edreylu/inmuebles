/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.capitulo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Edward Reyes
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.app.inmuebles.capitulo.CapituloControlIT.class, com.app.inmuebles.capitulo.CapituloIT.class, com.app.inmuebles.capitulo.CapituloServiceIT.class, com.app.inmuebles.capitulo.CapituloDAOIT.class})
public class CapituloITSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
