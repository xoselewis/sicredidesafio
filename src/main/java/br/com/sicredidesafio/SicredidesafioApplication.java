package br.com.sicredidesafio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;

@SpringBootApplication
public class SicredidesafioApplication {

//	@Value("${queue.order.name}")
//	private String orderQueue;

	public static void main(String[] args) {
		SpringApplication.run(SicredidesafioApplication.class, args);
	}

//	@Bean
//	public Queue queue() {
//		return new Queue(orderQueue, true);
//	}

}
