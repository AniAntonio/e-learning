package converter;

import java.util.ArrayList;
import java.util.List;

import dto.QuestionDto;
import entities.Question;

public class QuestionConverter {

	public static Question toEntity(QuestionDto questionDto) {
		Question question = new Question();
		question.setId(questionDto.getId());
		question.setDescription(questionDto.getDescription());
		//question.setQuestionType(QuestionTypeConverter.toEntity(questionDto.getQuestionType()));
		//question.setAlternatives(AlternativeConverter.toEntities(questionDto.getAlternatives()));
		return question;
	}

	public static QuestionDto toDto(Question question) {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(question.getId());
		questionDto.setDescription(question.getDescription());
		questionDto.setQuestionType(QuestionTypeConverter.toDto(question.getQuestionType()));
		questionDto.setAlternatives(AlternativeConverter.toDtos(question.getAlternatives()));
		return questionDto;
	}

	public static List<QuestionDto> toDtos(List<Question> questions) {
		List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
		for (Question question : questions) {
			questionDtos.add(toDto(question));
		}
		return questionDtos;
	}

}
