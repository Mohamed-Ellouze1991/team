package com.omicrone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.function.client.WebClient;  

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.omicrone.feignClient")
//@ComponentScan({"com.omicrone.controller", "com.omicrone.service"})
//@EntityScan("com.omicrone.entity")
//@EnableJpaRepositories("com.omicrone.repository")
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}


//	private String addressServiceUrl = "http://localhost:6064/api/address/";
//
//	
//	@Bean
//	public WebClient webClient () {
//	WebClient webClient =WebClient.builder().baseUrl(addressServiceUrl ).build();
//	return webClient;
//	}
}
