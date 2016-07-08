package com.fanto;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableDiscoveryClient

public class TodoserviceApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(TodoserviceApplication.class, args);
	}
}

@Component
class DummyCLR implements CommandLineRunner {

	private final TodoRepository todoRepository;

	@Autowired
	public DummyCLR(TodoRepository toRepository) {
		this.todoRepository = toRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		Stream.of("todo1", "todo2", "todo3", "todo4", "todo5", "todo6", "todo7", "todo8")
				.forEach(n -> todoRepository.save(new Todo(n)));
		todoRepository.findAll().forEach(System.out::println);
	}
}

