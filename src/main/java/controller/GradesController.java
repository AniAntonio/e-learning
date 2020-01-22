package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import dto.UserGradeDto;
import service.GradeService;

@Controller
public class GradesController {

	@Autowired
	private GradeService gradeService;

	@GetMapping(value = "/pedagogue/test/grades/{id}")
	public ModelAndView goToCreateQuestionPage(HttpServletRequest request, @PathVariable("id") Long testId) {
		ModelAndView mv = new ModelAndView("pedagog/grades.html");
		List<UserGradeDto> userGrades = gradeService.getTestGrades(testId);
		mv.addObject("userGrades", userGrades);
		return mv;
	}

}
