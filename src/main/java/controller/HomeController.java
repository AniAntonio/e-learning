package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import dto.UserDto;
import service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@GetMapping(value = { "/" })
	public ModelAndView home() {

		return new ModelAndView("login.html");
	}

	@GetMapping(value = "/login")
	public ModelAndView login(@ModelAttribute(name = "User") UserDto user, HttpServletRequest request) {
		user = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (user.getUsername() == null) {
			ModelAndView mv = new ModelAndView("login.html");
			mv.addObject("message", "Wrong username or password!");
			mv.addObject("alertClass", "alert-danger");
			return mv;
		} else {
			request.getSession().setAttribute("user", user);
			return new ModelAndView("redirect:/Home");
		}
	}

	@GetMapping("/Home")
	public ModelAndView adminHome(HttpServletRequest request) {
		UserDto user = (UserDto) request.getSession().getAttribute("user");
		ModelAndView mv = new ModelAndView();
		if (user.getRoleDto().getId() == 1) {
			mv = new ModelAndView("admin/adminHome.html");
		}
		if (user.getRoleDto().getId() == 2) {
			mv = new ModelAndView("pedagog/pedagogHome.html");
		}
		if (user.getRoleDto().getId() == 3) {
			mv = new ModelAndView("student/studentHome.html");
		}
		mv.addObject("user", user);
		return mv;
	}

	@GetMapping("/logout")
	public ModelAndView goToLoginPage(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/");
	}
}