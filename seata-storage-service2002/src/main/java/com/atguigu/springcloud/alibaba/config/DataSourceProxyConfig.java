package com.atguigu.springcloud.alibaba.config;

import org.springframework.context.annotation.Configuration;

/**
 * @auther zzyy
 * @create 2020-02-26 16:24
 * 使用Seata对数据源进行代理
 */
@Configuration
public class DataSourceProxyConfig {

//    @Value("${mybatis.mapperLocations}")
//    private String mapperLocations;
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource druidDataSource(){
//        return new DruidDataSource();
//    }
//
//
////    @Bean
////    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
////        return new DataSourceProxy(dataSource);
////    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
//        DataSourceProxy dataSourceProxy = new DataSourceProxy(dataSource);
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
//        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
//        return sqlSessionFactoryBean.getObject();
//    }

}
