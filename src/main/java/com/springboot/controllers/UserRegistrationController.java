package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.dto.UserRegistrationDto;
import com.springboot.models.User;
import com.springboot.repository.UserRepository;
import com.springboot.services.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping
	public String showRegistrationPage(Model model) {
		model.addAttribute("user", new UserRegistrationDto());
		return "registration";
	}

//	@PostMapping
//	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto) {
//		
//		userService.save(userRegistrationDto);
//		return "redirect:/login";
//	}
	
	@PostMapping
	public ModelAndView registerUserAccount(ModelAndView modelAndView, @ModelAttribute("user") UserRegistrationDto userRegistrationDto, BindingResult bindingResult) {
		
		User userExists = userRepo.findByEmail(userRegistrationDto.getEmail());
		
		if(userExists!=null) {
			
			modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("registration");
			bindingResult.reject("email");
		}
		
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("registration");		
		}
		else {
		userService.save(userRegistrationDto);
		modelAndView.setViewName("login");
		}
		return modelAndView;
	}
}
