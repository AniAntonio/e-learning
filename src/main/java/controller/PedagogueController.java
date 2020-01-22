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
import dto.QuestionTypeDto;
import dto.TestDto;
import dto.UserDto;
import dto.UtilDto;
import entities.Faculty;
import service.CourseService;
import service.QuestionService;
import service.TestService;
import service.UserService;

@Controller
@RequestMapping("/pedagogue")
public class PedagogueController {

	private static final String MESSAGE = "message";
	private static final String ALERT_CLASS = "alertClass";
	private static final String ALERT_SUCCESS = "alert alert-success";

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private TestService testService;

	@GetMapping("/pedagogueProfile/edit/{id}")
	public ModelAndView goToEditProfilePage(@PathVariable("id") Long id) {
		UserDto pedagogueDto = userService.getUserById(id);
		List<Faculty> faculties = userService.getAllFaculties();
		ModelAndView mv = new ModelAndView("pedagog/editPedagogue.html");
		mv.addObject("pedagogue", pedagogueDto);
		mv.addObject("addStatus", Boolean.FALSE);
		mv.addObject("faculties", faculties);
		return mv;
	}

	@PostMapping("/editPedagogue")
	public ModelAndView savePedagogue(@ModelAttribute UserDto pedagogue, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		ModelAndView mv = new ModelAndView("pedagog/editPedagogue.html");
		mv.addObject("pedagogue", pedagogue);
		mv.addObject(MESSAGE, "Pedagogue with this username already exists!");
		mv.addObject(ALERT_CLASS, "alert-danger");
		if (!userService.editUser(pedagogue)) {
			return mv;
		} else {
			redirectAttrs.addFlashAttribute(MESSAGE, "Pedagogue edited successfully!!");
			redirectAttrs.addFlashAttribute(ALERT_CLASS, ALERT_SUCCESS);
		}
		UserDto user = userService.getUserById(pedagogue.getId());
		request.getSession().setAttribute("user", user);
		ModelAndView mv1 = new ModelAndView("redirect:/Home");
		return mv1;
	}

	@GetMapping("/goToAddCourse")
	public ModelAndView goToAddCoursePage(CourseDto courseDto) {
		ModelAndView mv = new ModelAndView("pedagog/addCourse.html");
		mv.addObject("courseDto", courseDto);
		return mv;
	}

	@PostMapping("/addCourse")
	public ModelAndView addCourse(@ModelAttribute CourseDto courseDto, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		ModelAndView mv = new ModelAndView("pedagog/addCourse.html");
		UserDto pedagogue = (UserDto) request.getSession().getAttribute("user");
		mv.addObject(MESSAGE, "Date of start must be before finish date!");
		mv.addObject(ALERT_CLASS, "alert-danger");
		if (!courseService.createCourse(courseDto, pedagogue.getId())) {
			return mv;
		}
		return new ModelAndView("redirect:/pedagogue/courses");
	}

	@GetMapping(value = "/courses")
	public ModelAndView goToCoursesList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("pedagog/courses.html");
		UserDto pedagogue = (UserDto) request.getSession().getAttribute("user");
		List<CourseDto> courses = courseService.getAllPedagogueCourses(pedagogue.getId());
		mv.addObject("courses", courses);
		return mv;
	}

	@GetMapping(value = "/tests")
	public ModelAndView goToTestList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("pedagog/tests.html");
		UserDto pedagogue = (UserDto) request.getSession().getAttribute("user");
		List<TestDto> tests = testService.getPedagogueTests(pedagogue.getId());
		mv.addObject("tests", tests);
		return mv;
	}

	@GetMapping("/addStudentToCourse/{id}")
	public ModelAndView goToAddStudentInCoursePage(@PathVariable("id") Long idCourse) {
		List<UserDto> studentsNotRegistered = courseService.getAllStudentsNotRegisteredInCourse(idCourse);
		ModelAndView mv = new ModelAndView("pedagog/addStudentsInCourse.html");
		UtilDto utilDto = new UtilDto();
		utilDto.setIdCourse(idCourse);
		mv.addObject("utilDto", utilDto);
		mv.addObject("students", studentsNotRegistered);
		return mv;
	}

	@PostMapping("/addStudentsInCourse")
	public ModelAndView addStudentsInCourse(@ModelAttribute UtilDto utilDto) {
		courseService.addStudentsInCourse(utilDto);
		return new ModelAndView("redirect:/pedagogue/courses");
	}

	@GetMapping(value = "/createQestion")
	public ModelAndView goToCreateQuestionPage() {
		ModelAndView mv = new ModelAndView("pedagog/createQuestion.html");
		List<QuestionTypeDto> questionTypeDtos = questionService.getAllQuestionTypes();
		mv.addObject("question", new QuestionDto());
		mv.addObject("questionTypes", questionTypeDtos);
		return mv;
	}

	@GetMapping(value = "/createTest/{id}")
	public ModelAndView goToCreateTestPage(HttpServletRequest request, @PathVariable("id") Long idCourse) {
		ModelAndView mv = new ModelAndView("pedagog/createTest.html");
		TestDto testDto = new TestDto();
		UserDto pedagogue = (UserDto) request.getSession().getAttribute("user");
		testDto.setCourseId(idCourse);
		List<QuestionDto> questionDtos = questionService.getPedagogueQuestions(pedagogue.getId());
		testDto.setQuestions(questionDtos);
		mv.addObject("testDto", testDto);
		return mv;
	}

	@PostMapping("/addTest")
	public ModelAndView createTest(@ModelAttribute TestDto testDto) {
		testService.createTest(testDto);
		return new ModelAndView("redirect:/pedagogue/courses");
	}

}
