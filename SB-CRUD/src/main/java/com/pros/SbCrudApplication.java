package com.pros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@RestController
@EnableCaching
public class SbCrudApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SbCrudApplication.class, args);

					// ProducerConfig producerConfig = context.getBean(ProducerConfig.class);
					// producerConfig.sendMessage("Message");;
	}



	//	@GetMapping(value = "/h")
//	public String sayHello(){
//		return "Hello Wolf";
//	}
}
