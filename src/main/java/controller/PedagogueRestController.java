package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.QuestionDto;
import dto.UserDto;
import service.QuestionService;

@RestController
@RequestMapping(value = "/rest/pedagogue")
public class PedagogueRestController {

	@Autowired
	private QuestionService questionService;

	@PostMapping(value = "/create-question")
	public void createQuestion(@RequestBody QuestionDto questionDto, HttpServletRequest request) {
		UserDto pedagogue = (UserDto) request.getSession().getAttribute("user");
		questionService.createQuestion(questionDto, pedagogue.getId());
	}

}
