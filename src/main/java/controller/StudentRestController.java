package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.QuestionDto;
import dto.UserDto;
import service.TestService;

@RestController
@RequestMapping(value = "/rest/student")
public class StudentRestController {

	@Autowired
	private TestService testService;

	@PostMapping("/submit-test/{testId}")
	public void submitOnlineExam(@PathVariable("testId") Long testId, @RequestBody List<QuestionDto> questionDtos,
			HttpServletRequest request) {
		UserDto user = (UserDto) request.getSession().getAttribute("user");
		System.out.println(user);
		testService.submitExamAndCalculateGrade(testId, questionDtos, user.getId());
	}

}
