package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Todo;
import com.example.demo.repo.ITodoRepo;

@Service
public class TodoService {
	
	@Autowired
	ITodoRepo repo;
	
	public List<Todo> getAllToDoItems(){
		ArrayList<Todo> todoList = new ArrayList<>();
		repo.findAll().forEach(todo -> todoList.add(todo));
		
		return todoList;
	}
	
	public Todo getTodoItemById(Long id){
		return repo.findById(id).get();
	}
	
	public boolean updateStatus(Long id) {
		Todo todo = getTodoItemById(id);
		todo.setStatus("Completed");
		
		return saveOrUpdateTodoItem(todo);
	}
	
	public boolean saveOrUpdateTodoItem(Todo todo) {
		Todo updatedObj = repo.save(todo);
		
		if(getTodoItemById(updatedObj.getId()) != null) {
			return true;
		}
		
		return false;
	}
	
	public boolean deleteTodoItem(Long id) {
		repo.deleteById(id);
		
		if(getTodoItemById(id) == null) {
			return true;
		}
		
		return false;
	}
}
