package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;

@Controller
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	@GetMapping({"/", "viewTodoList"})
	public String viewAllTodoItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list", service.getAllToDoItems());
		model.addAttribute("msg", message);
		
		return "ViewTodoList";
	}
	
	@PostMapping("/updateTodoStatus/{id}")
	public String updateTodoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if(service.updateStatus(id)) {
			redirectAttributes.addFlashAttribute("message", "Update Success");
			return "redirect:/viewTodoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Update Failure");
		return "redirect:/viewTodoList";
	}
	
	@GetMapping("/addToDoItem")
	public String addTodoITem(Model model) {
		model.addAttribute("todo", new Todo());
		
		return "AddToDoItem";
	}
	
	@PostMapping("/saveToDoItem")
	public String saveTodoItem(Todo todo, RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateTodoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Save Success");
			return "redirect:/viewTodoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Save Failure");
		
		return "redirect:/addTodoItem";
	}
	
	@GetMapping("/editTodoItem/{id}")
	public String editTodoItem(@PathVariable Long id, Model model) {
		model.addAttribute("todo", service.getTodoItemById(id));
		
		return "EditTodoItem";
	}
	
	@PostMapping("/editSaveTodoItem")
	public String editSaveTodoItem(Todo todo, RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateTodoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Save Success");
			return "redirect:/viewTodoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Save Failure");
		return "redirect:/viewTodoList";
	}
	
	@GetMapping
	public String deleteTodoItem() {
		
	}
}