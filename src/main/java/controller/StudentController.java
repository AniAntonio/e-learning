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

import dto.CourseDto;
import dto.QuestionDto;
import dto.TestDto;
import dto.UserDto;
import entities.Faculty;
import service.CourseService;
import service.TestService;
import service.UserService;

@Controller
@RequestMapping("/student")
public class StudentController {

	private static final String MESSAGE = "message";
	private static final String ALERT_CLASS = "alertClass";
	private static final String ALERT_SUCCESS = "alert alert-success";

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private TestService testService;

	@GetMapping("/studentProfile/edit/{id}")
	public ModelAndView goToEditProfilePage(@PathVariable("id") Long id) {
		UserDto studentDto = userService.getUserById(id);
		List<Faculty> faculties = userService.getAllFaculties();
		ModelAndView mv = new ModelAndView("student/editStudent.html");
		mv.addObject("student", studentDto);
		mv.addObject("addStatus", Boolean.FALSE);
		mv.addObject("faculties", faculties);
		return mv;
	}

	@PostMapping("/editStudent")
	public ModelAndView saveStudent(@ModelAttribute UserDto student, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		request.getSession().setAttribute("user", student);
		ModelAndView mv = new ModelAndView("student/editStudent.html");
		mv.addObject("student", student);
		mv.addObject(MESSAGE, "Student with this username already exists!");
		mv.addObject(ALERT_CLASS, "alert-danger");
		if (!userService.editUser(student)) {
			return mv;
		} else {
			redirectAttrs.addFlashAttribute(MESSAGE, "Profile edited successfully!!");
			redirectAttrs.addFlashAttribute(ALERT_CLASS, ALERT_SUCCESS);
		}
		UserDto user = userService.getUserById(student.getId());
		request.getSession().setAttribute("user", user);
		ModelAndView mv1 = new ModelAndView("redirect:/Home");
		return mv1;
	}

	@GetMapping(value = "/courses")
	public ModelAndView goToCoursesList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("student/courses.html");
		UserDto student = (UserDto) request.getSession().getAttribute("user");
		List<CourseDto> courses = courseService.getAllStudentCourses(student.getId());
		mv.addObject("courses", courses);
		return mv;
	}

	@GetMapping(value = "/courseTests/{id}")
	public ModelAndView goToTestCourses(@PathVariable("id") Long courseId) {
		ModelAndView mv = new ModelAndView("student/tests.html");
		List<TestDto> testDtos = testService.getTestsByCourseId(courseId);
		mv.addObject("tests", testDtos);
		return mv;
	}

	@GetMapping(value = "/goToTest/{id}")
	public ModelAndView goToSubmitTest(@PathVariable("id") Long testId) {
		ModelAndView mv = new ModelAndView("student/submitTest.html");
		TestDto test = testService.getTestById(testId);
		List<QuestionDto> questions = test.getQuestions();
		mv.addObject("test", test);
		mv.addObject("questions", questions);
		return mv;
	}

}
