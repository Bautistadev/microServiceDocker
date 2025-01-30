package com.spring.Client1;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.coyote.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
@RefreshScope
public class Client1Application {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(Client1Application.class, args);

		ClientRest clientRest = ctx.getBean(ClientRest.class);

		System.out.println(clientRest.echoRest().getStatusCode());
	}

}

@FeignClient(name="Client2",path = "/api/v1")
interface ClientRest{
	@GetMapping(value = "/echo")
	public ResponseEntity<String> echoRest();
}


