package intech.testTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import intech.testTask.dto.UserDto;
import intech.testTask.service.MessageService;
import intech.testTask.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		model.setViewName("users");
		model.addObject("credentials", userService.getCredentials(login));
		model.addObject("users", userService.getAllUsers());
		model.addObject("usersActive", true);
		return model;
	}
	
	@RequestMapping(value = "/removeUser", method = RequestMethod.POST)
	public @ResponseBody Boolean removeUser(@RequestParam Long userId){
		return userService.deleteUser(userId);
	}
	
	@RequestMapping(value = "/grantAdmin", method = RequestMethod.POST)
	public @ResponseBody Boolean grantAdmin(@RequestParam Long userId){
		return userService.grantAdmin(userId);
	}
	
	@RequestMapping(value = "/revokeAdmin", method = RequestMethod.POST)
	public @ResponseBody Boolean revokeAdmin(@RequestParam Long userId){
		return userService.revokeAdmin(userId);
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public ModelAndView createUser() {
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		model.addObject("isAdmin", true);
		return model;
	}
	
	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String header(UserDto dto) {
		userService.registerNewUser(dto);
		return "redirect:users";
	}
	
}
