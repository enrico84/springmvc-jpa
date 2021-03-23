package it.capone.spring.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "it.capone.spring", entityManagerFactoryRef = "entityManagerFactory")
@EnableTransactionManagement
public class JpaConfig {

    /**
     * Il metodo crea una instanza di "EntityManagerFactory" per gestire la unita'
     * di persistenza "PlayerDB" definita nel file persistence.xml
     * 
     * @return factoryBean
     */
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
	LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
	factoryBean.setPersistenceUnitName("PlayerDB");

	return factoryBean;
    }

    /**
     * Il metodo torna una instanza di "JpaTransactionManager" per la
     * EntityManagerFactory creata nel metodo precedente
     * 
     * @param entityManagerFactory
     * @return transactionManager
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(entityManagerFactory);

	return transactionManager;
    }

}
