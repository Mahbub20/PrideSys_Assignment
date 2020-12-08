package com.springboot.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.models.Task;
import com.springboot.services.TaskService;

@Controller
public class MainController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/login")
	public String login(Model model) {
		
		return "login";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		List<Task> listTask = taskService.taskList();
		model.addAttribute("listTask", listTask);
		return "index";
	}
	
	@RequestMapping("/new")
	public String showAddNewTaskPage(Model model) {
		
		Task task = new Task();
		model.addAttribute("task", task);
		return "new_task";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveTask(@ModelAttribute("task") Task task) {

		taskService.save(task);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
    public ModelAndView showEditPage(@PathVariable(name="id") Long id){
        ModelAndView mav = new ModelAndView("edit_task");
        Task task = taskService.get(id);
        mav.addObject("task", task);
        return mav;
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){
    	taskService.deleteTask(id);
        return "redirect:/";
    }

}
