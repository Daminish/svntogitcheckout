package com.capco.living;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// We are writting this call so that from the default root package all the classes will be loaded
// automatically. It helps us to define defines a base “search package” for certain items.
//For example, if you are writing a JPA application, 
//the package of the @EnableAutoConfiguration annotated class will be used to search for @Entity items.

/*@Configuration
@EnableAutoConfiguration
//@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@PropertySource("classpath:application.properties")
@ComponentScan*/
//basePackages = { "src.main.java.com.capco.living" },

/*@ComponentScan( excludeFilters = {@Filter(value = Controller.class, type = FilterType.ANNOTATION)})
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")*/
@SpringBootApplication 
public class Application {

    public static void main(String[] args) {
    	
        SpringApplication.run(Application.class, args);
    }

}