package org.myCountriesApplication.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = "org.myCountriesApplication")
@PropertySource(value = "classpath:application.properties")
public class SpringCoreConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
