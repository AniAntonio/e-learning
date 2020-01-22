package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dto.UserDto;
import entities.Faculty;
import service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final String MESSAGE = "message";
	private static final String ALERT_CLASS = "alertClass";
	private static final String ALERT_SUCCESS = "alert alert-success";
	private static final String REDIRECT_TO_STUDENT_LIST = "redirect:/admin/studentList";
	private static final String REDIRECT_TO_PEDAGOGUE_LIST = "redirect:/admin/pedagogueList";
	private static final String ADD_USER_URL = "admin/addUser.html";

	@Autowired
	private UserService userService;

	@GetMapping(value = "/studentList")
	public ModelAndView goToStudentkList() {
		ModelAndView mv = new ModelAndView("admin/studentList.html");
		List<UserDto> students = userService.getAllStudents();
		mv.addObject("students", students);
		return mv;
	}

	@GetMapping(value = "/pedagogueList")
	public ModelAndView goToPedagogueList() {
		ModelAndView mv = new ModelAndView("admin/pedagogueList.html");
		List<UserDto> pedagogues = userService.getAllPedagogues();
		mv.addObject("pedagogues", pedagogues);
		return mv;
	}

	@GetMapping("/goToAddStudent")
	public ModelAndView goToAddStudentPage(UserDto studentDto) {
		ModelAndView mv = new ModelAndView(ADD_USER_URL);
		List<Faculty> faculties = userService.getAllFaculties();
		mv.addObject("user", studentDto);
		mv.addObject("faculties", faculties);
		mv.addObject("role", "student");
		return mv;
	}

	@GetMapping("/goToAddPedagogue")
	public ModelAndView goToAddPedagoguePage(UserDto pedagogueDto) {
		ModelAndView mv = new ModelAndView(ADD_USER_URL);
		List<Faculty> faculties = userService.getAllFaculties();
		mv.addObject("user", pedagogueDto);
		mv.addObject("faculties", faculties);
		mv.addObject("role", "pedagogue");
		return mv;
	}

	@GetMapping("/user/delete/{id}")
	public ModelAndView deleteStudent(@PathVariable("id") Long id, RedirectAttributes redirectAttrs) {
		userService.deleteUser(userService.getUserById(id));
		redirectAttrs.addFlashAttribute(MESSAGE, "User deleted successfully!");
		redirectAttrs.addFlashAttribute(ALERT_CLASS, ALERT_SUCCESS);
		return new ModelAndView(REDIRECT_TO_STUDENT_LIST);
	}

	@PostMapping("/addUser")
	public ModelAndView saveStudent(@ModelAttribute UserDto user, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		ModelAndView mv = new ModelAndView(ADD_USER_URL);
		mv.addObject("user", user);
		mv.addObject(MESSAGE, "User with this username already exists!");
		mv.addObject(ALERT_CLASS, "alert-danger");

		List<Faculty> faculties = userService.getAllFaculties();
		mv.addObject("faculties", faculties);
		if (!userService.addUser(user)) {
			if (user.getIdRole() == 2L) {
				mv.addObject("role", "pedagogue");

			} else {
				mv.addObject("role", "student");
			}
			return mv;
		} else {
			redirectAttrs.addFlashAttribute(MESSAGE, "User edited successfully!!");
			redirectAttrs.addFlashAttribute(ALERT_CLASS, ALERT_SUCCESS);
		}
		if (user.getIdRole() == 2L) {
			return new ModelAndView(REDIRECT_TO_PEDAGOGUE_LIST);
		}
		return new ModelAndView(REDIRECT_TO_STUDENT_LIST);
	}

}
