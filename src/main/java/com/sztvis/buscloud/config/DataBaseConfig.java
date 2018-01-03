package com.sztvis.buscloud.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/2 上午9:10
 */
@Configuration
//扫描实体类的包
@ComponentScan(basePackages = "com.sztvis.buscloud.model.domain")
//扫描仓库类的包，在mybatis里面被称为Mapper，一般用来完成数据库的操作
@MapperScan(basePackages = "com.sztvis.buscloud.mapper")
public class DataBaseConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String DriverName;
    @Value("${spring.datasource.url}")
    private String Url;
    @Value("${spring.datasource.username}")
    private String UserName;
    @Value("${spring.datasource.password}")
    private String PassWord;


    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        // 数据库连接配置
        dataSource.setDriverClassName(DriverName);
        dataSource.setUrl(Url);
        dataSource.setUsername(UserName);
        dataSource.setPassword(PassWord);
        return dataSource;
    }

    //事务管理
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(getDataSource());
        return sqlSessionFactory.getObject();
    }
}
