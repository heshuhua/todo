package com.fanto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@IntegrationComponentScan
@EnableZuulProxy
@EnableFeignClients
public class TodoclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoclientApplication.class, args);
	}
}


@FeignClient("todoservice")
interface TodoReader {

	@RequestMapping (method = RequestMethod.GET, value = "/todos")
	public Collection<Todo> read();
}

@RestController
@RequestMapping("/clienttodos")
class ClientTodo
{

	private final TodoReader todoReader;
	
	@Autowired
	public ClientTodo(TodoReader reader) {
		this.todoReader=reader;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Collection<Todo> findTodo()
	{
		Collection<Todo> result=new ArrayList<Todo>();
		
		result = todoReader.read();
		
		return result;
	}
}


class Todo
{
	private String title;
	
	private Long id;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}

