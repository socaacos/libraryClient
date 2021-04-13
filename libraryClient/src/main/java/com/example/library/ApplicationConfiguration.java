package com.example.library;

import org.modelmapper.ModelMapper;
import org.openapitools.client.api.AuthorControllerApi;
import org.openapitools.client.api.BookControllerApi;
import org.openapitools.client.api.CityControllerApi;
import org.openapitools.client.api.LibraryControllerApi;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.example.library.aspects.LogsAspect;

@Configuration
@EnableAspectJAutoProxy
@EnableScheduling
@EnableEurekaClient

public class ApplicationConfiguration {
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	public AuthorControllerApi authorApi() {
	    return new AuthorControllerApi();
	}
	
	@Bean
	@LoadBalanced
	public BookControllerApi bookApi() {
	    return new BookControllerApi();
	}
	
	@Bean
	public CityControllerApi cityApi() {
	    return new CityControllerApi();
	}
	
	@Bean
	public LibraryControllerApi libraryApi() {
	    return new LibraryControllerApi();
	}
}
