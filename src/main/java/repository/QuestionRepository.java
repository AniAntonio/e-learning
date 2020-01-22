package repository;

import java.util.List;

import entities.Alternative;
import entities.Question;
import entities.QuestionType;

public interface QuestionRepository {

	Question createQuestion(Question question);

	QuestionType getQuestionTypeById(Long id);

	List<Question> getQuestionListByIds(List<Long> ids);

	Alternative getAlternativeById(Long id);

	List<QuestionType> getAllQuestionTypes();

	List<Question> getAllQuestionsOfPedagogue(Long id);

	Question getQuestionById(Long id);

	List<Alternative> getAlternativeListByIds(List<Long> ids);

}
