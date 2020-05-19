/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife;

import com.app.riife.capitulo.CapituloDAO;
import com.app.riife.capitulo.CapituloDAOImpl;
import com.app.riife.capitulo.CapituloService;
import com.app.riife.capitulo.CapituloServiceImpl;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Edward Reyes
 */
@Configuration
@Qualifier("testDataBaseConfig")
public class TestDataBaseConfig {
    
    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
        dataSource.setUsername("SYSTEM");
        dataSource.setPassword("meteoralp");
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate getJdbcTemplate(){
    JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    return jdbcTemplate;
    }
    
    @Bean
    public CapituloDAO getCapituloDAO(){
    return new CapituloDAOImpl(getJdbcTemplate());
    }
    
    @Bean
    public CapituloService getCapituloService(){
    return new CapituloServiceImpl(getCapituloDAO());
    }
}
