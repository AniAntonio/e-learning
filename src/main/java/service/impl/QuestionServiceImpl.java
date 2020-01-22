package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import converter.AlternativeConverter;
import converter.QuestionConverter;
import converter.QuestionTypeConverter;
import dto.QuestionDto;
import dto.QuestionTypeDto;
import entities.Alternative;
import entities.Question;
import entities.Test;
import repository.QuestionRepository;
import repository.TestRepository;
import repository.UserRepository;
import service.QuestionService;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public QuestionDto createQuestion(QuestionDto questionDto, Long idPedagogue) {
		Question question = QuestionConverter.toEntity(questionDto);
		question.setQuestionType(
				questionRepository.getQuestionTypeById(questionDto.getQuestionType().getQuestionTypeId()));
		question.setPedagogue(userRepository.getUserById(idPedagogue));
		List<Alternative> alternatives = AlternativeConverter.toEntities(questionDto.getAlternatives());
		alternatives.forEach(question::addAlternativeInQuestion);
		question = questionRepository.createQuestion(question);
		return QuestionConverter.toDto(question);
	}

	@Override
	public List<QuestionDto> getTestQuestions(Long idTest) {
		Test test = testRepository.getTestById(idTest);
		return QuestionConverter.toDtos(test.getQuestions());
	}

	@Override
	public List<QuestionTypeDto> getAllQuestionTypes() {
		return QuestionTypeConverter.toDtos(questionRepository.getAllQuestionTypes());
	}

	@Override
	public List<QuestionDto> getPedagogueQuestions(Long id) {
		return QuestionConverter.toDtos(questionRepository.getAllQuestionsOfPedagogue(id));
	}

}
