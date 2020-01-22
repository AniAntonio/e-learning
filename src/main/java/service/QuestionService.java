package service;

import java.util.List;

import dto.QuestionDto;
import dto.QuestionTypeDto;

public interface QuestionService {

	QuestionDto createQuestion(QuestionDto questionDto, Long idPedagogue);

	List<QuestionDto> getTestQuestions(Long idTest);

	List<QuestionTypeDto> getAllQuestionTypes();

	List<QuestionDto> getPedagogueQuestions(Long id);

}
