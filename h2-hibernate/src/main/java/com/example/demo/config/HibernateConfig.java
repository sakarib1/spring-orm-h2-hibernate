package com.example.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class HibernateConfig {
	
	
	
	@Bean
	public DataSource sourceDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setJdbcUrl("jdbc:h2:mem:EMP;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
		dataSource.setUsername("sa");

		return dataSource;

	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
//		sessionFactory.setMappingResources("");
		sessionFactory.setMappingLocations(
				new Resource[] { new ClassPathResource("com/example/demo/model/Employee.hbm.xml")});
		
	    Properties properties = new Properties();
	    properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
	    properties.put("hibernate.format_sql","true");
	    properties.put("hibernate.show_sql","true");

	    //properties.put("hibernate.current_session_context_class","thread");
	    properties.put("hibernate.hbm2ddl.auto","update");
		
		sessionFactory.setHibernateProperties(properties);

		return sessionFactory;
	}
	/*
	 * <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
	 * destroy-method="close">
	 * 
	 * <property name="driverClassName" value="com.mysql.jdbc.Driver" /> <property
	 * name="url" value="jdbc:mysql://localhost:3306/test" /> <property
	 * name="username" value="root" /> <property name="password" value="password" />
	 * </bean>
	 * 
	 * <!-- Hibernate session factory --> <bean id="sessionFactory"
	 * class="org.springframework.orm.hibernate4.LocalSessionFactoryBean"> <property
	 * name="dataSource" ref="dataSource" /> <property name="mappingResources">
	 * <list> <value>orm/Users.hbm.xml</value> <value>orm/UserRoles.hbm.xml</value>
	 * </list> </property> <property name="hibernateProperties"> <props> <prop
	 * key="hibernate.dialect"> org.hibernate.dialect.MySQL5Dialect </prop> <prop
	 * key="hibernate.format_sql">true</prop> <prop
	 * key="hibernate.show_sql">true</prop> </props> </property> </bean>
	 * 
	 * <bean id="userDao" class="com.mkyong.users.dao.UserDaoImpl"> <property
	 * name="sessionFactory" ref="sessionFactory" /> </bean>
	 * 
	 * <bean id="myUserDetailsService"
	 * class="com.mkyong.users.service.MyUserDetailsService"> <property
	 * name="userDao" ref="userDao" /> </bean>
	 * 
	 * <!-- MUST have transaction manager, using aop and aspects --> <bean
	 * id="transactionManager"
	 * class="org.springframework.orm.hibernate4.HibernateTransactionManager">
	 * <property name="sessionFactory" ref="sessionFactory"></property> </bean>
	 * 
	 * <tx:advice id="txAdvice" transaction-manager="transactionManager">
	 * <tx:attributes> <tx:method name="get*" read-only="true" /> <tx:method
	 * name="find*" read-only="true" /> <tx:method name="*" /> </tx:attributes>
	 * </tx:advice>
	 * 
	 * <aop:config> <aop:pointcut id="userServicePointCut"
	 * expression="execution(* com.mkyong.users.service.*Service.*(..))" />
	 * <aop:advisor advice-ref="txAdvice" pointcut-ref="userServicePointCut" />
	 * </aop:config>
	 */

}
