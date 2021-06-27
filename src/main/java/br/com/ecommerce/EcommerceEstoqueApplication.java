package br.com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class EcommerceEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceEstoqueApplication.class, args);
	}

}
