package com.fanto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/todos")
public class TodoService {

	@Autowired
	TodoRepository todoRepository;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public Collection<Todo> findTodo()
	{
		List<Todo> result=new ArrayList<Todo>();
		
		result = todoRepository.findAll();
		System.out.println("server1--"+result.size());
		try{
		Thread.sleep(1000);
		}
		catch(Exception e)
		{
			
		}
		return result;
	}
	
}
