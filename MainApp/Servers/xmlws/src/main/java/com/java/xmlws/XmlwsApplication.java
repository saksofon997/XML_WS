package com.java.xmlws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class XmlwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlwsApplication.class, args);
	}

}
