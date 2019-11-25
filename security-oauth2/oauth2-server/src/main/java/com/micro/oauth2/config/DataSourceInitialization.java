package com.micro.oauth2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Created by daiguoqing on 2019/11/20.
 * User: daiguoqing 643092822@qq.com
 * Date: 2019/11/20
 * Time: 15:11
 * Version: microservice 1.0.0.0
 * [功能描述].
 */
@Configuration
@PropertySource({ "classpath:jdbc.properties" })
@Slf4j
public class DataSourceInitialization {

    @Autowired
    private Environment environment;

    @Value("classpath:schema.sql")
    private Resource schemaScript;

    @Value("classpath:data.sql")
    private Resource dataScript;

    /**
     * 初始化数据源
     * @return
     */
    @Bean
    public DataSourceInitializer dataSourceInitialize(final DataSource dataSource){
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }


    /**
     * 配置sql脚本
     * @return
     */
    private DatabasePopulator databasePopulator(){
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        //populator.addScript(dataScript);
        return populator;
    }

    /**
     * 配置数据源
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource dataSource(){
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("oauth.jdbc.driverClassName"));
        dataSource.setUrl(environment.getProperty("oauth.jdbc.url"));
        dataSource.setUsername(environment.getProperty("oauth.jdbc.userName"));
        dataSource.setPassword(environment.getProperty("oauth.jdbc.password"));
        return dataSource;
    }

}
