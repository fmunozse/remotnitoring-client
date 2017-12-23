package com.remotnitoring.client;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.remotnitoring.client.service.RemoteService;

@ComponentScan
@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class RemotnitoringClientApplication {

	@Autowired
	RemoteService remoteService;
	
	public static void main(String[] args) {
		SpringApplication.run(RemotnitoringClientApplication.class, args);

	}
	
	   @Bean
	    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	        return args -> {

	        	/*
	            System.out.println("Let's inspect the beans provided by Spring Boot:");

	            String[] beanNames = ctx.getBeanDefinitionNames();
	            Arrays.sort(beanNames);
	            for (String beanName : beanNames) {
	                System.out.println(beanName);
	            }
	            */

	            //remoteService.call();
	            
	        };
	    }
	   
}
