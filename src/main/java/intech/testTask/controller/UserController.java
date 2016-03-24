package intech.testTask.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import intech.testTask.dto.MessageDto;
import intech.testTask.dto.UserDto;
import intech.testTask.service.MessageService;
import intech.testTask.service.UserService;
import intech.testTask.util.ServiceResult;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		model.setViewName("index");
		model.addObject("credentials", userService.getCredentials(login));
		model.addObject("friends", userService.getFriends(login));
		model.addObject("messages", messageService.getMessages(login));
		model.addObject("messagesActive", true);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String header(UserDto dto) {
		userService.registerNewUser(dto);
		return "redirect:login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		model.addObject("isAdmin", false);
		return model;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String changePassword(@RequestParam("newPassword") String newPassword) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		userService.updatePassword(login, newPassword);
		return "success";
	}

	@RequestMapping(value = "/addFriend", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addFriend(@RequestParam("friendLogin") String friendLogin) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		ServiceResult serviceResult = userService.addFriend(login, friendLogin);
		return serviceResult.isOK() ? new ResponseEntity<String>(HttpStatus.OK)
				: new ResponseEntity<String>(serviceResult.getErrorMessage(), HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/removeFriend", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String removeFriend(@RequestParam("friendLogin") String friendLogin) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		userService.removeFriend(login, friendLogin);
		return "success";
	}

	@RequestMapping(value = "/newPM", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String newPM(@RequestBody MessageDto message) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		message.setSender(login);
		this.messagingTemplate.convertAndSendToUser(message.getRecipient(), "/pm", messageService.sendMessage(message));
		return "success";
	}
	
	@RequestMapping(value = "/getPM", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MessageDto newPM(@RequestParam("id") Long messageId) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		return messageService.getMessageById(messageId, login);
	}
	
	@RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
	public @ResponseBody Boolean checkLogin(@RequestParam("login") String userLogin) {
		return !userService.userExists(userLogin);
	}
	
	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	public @ResponseBody Boolean checkEmail(@RequestParam("email") String userEmail) {
		return !userService.emailExists(userEmail);
	}

}
