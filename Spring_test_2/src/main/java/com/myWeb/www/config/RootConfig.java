package com.myWeb.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.myWeb.www.repository"})
@ComponentScan(basePackages = {"com.myWeb.www.service"})
public class RootConfig {
	// DB 설정 부분
	// hikariCP 사용 / log4jdbc-log4j2 사용
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean
	public DataSource dataSource() {
		// 여기서 히카리CP 사용
		HikariConfig hikariConfig = new HikariConfig();
		// log4jdbc-log4j2 를 사용해서 
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/mywebdb");                                           
		hikariConfig.setUsername("mywebUser");
		hikariConfig.setPassword("mysql");
		
		hikariConfig.setMaximumPoolSize(5); // 최대 커넥션 개수
		hikariConfig.setMinimumIdle(5); // 최소 유휴 커넥션 개수(반드시 최대 커넥션 갯수와 동일하게 설정)
		
		hikariConfig.setConnectionTestQuery("SELECT NOW()"); // Test 쿼리
		hikariConfig.setPoolName("springHikariCP");
		
		// 추가 설정
		// cachePrepStmts : cache 사용 여부 설정
		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		// 데이터베이스의 Prepared Statement를 캐시에 저장하도록 지시하는 것입니다. 
		// Prepared Statement는 SQL 쿼리를 미리 컴파일하여 데이터베이스에 쿼리를 실행할 때의 성능을 향상시키는 데 사용
		// 캐시를 사용하면 동일한 쿼리가 여러 번 실행될 때 이점이 있음
		
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
		// mysql 드라이버가 연결당 cache statement의 수에 관한 설정 : 250 ~ 500 사이 권장
		
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "true");
		// Connection 당 캐싱할 preparStatement의 개수 지정 옵션 : default 256 => true 설정시 자동 적용
		
		hikariConfig.addDataSourceProperty("dataSource.userServertPropStmts", "true");
		// mysql 서버에서 최신 이슈가 있을 경우 지원받을 설정
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		return hikariDataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		
		sqlFactoryBean.setDataSource(dataSource());
		sqlFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/*.xml"));
		
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/MybatisConfig.xml"));
		
		return sqlFactoryBean.getObject();
	}
	
	// 트렌젝션 매니저 빈 설정
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
