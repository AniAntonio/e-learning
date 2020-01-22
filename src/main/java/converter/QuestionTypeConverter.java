package converter;

import java.util.ArrayList;
import java.util.List;

import dto.QuestionTypeDto;
import entities.QuestionType;

public class QuestionTypeConverter {

	public static QuestionTypeDto toDto(QuestionType questionType) {
		QuestionTypeDto questionTypeDto = new QuestionTypeDto();
		questionTypeDto.setQuestionTypeId(questionType.getId());
		questionTypeDto.setTypeName(questionType.getName());
		return questionTypeDto;
	}

	public static QuestionType toEntity(QuestionTypeDto quetionTypeDto) {
		QuestionType questionType = new QuestionType();
		questionType.setId(quetionTypeDto.getQuestionTypeId());
		questionType.setName(quetionTypeDto.getTypeName());
		return questionType;
	}

	public static List<QuestionTypeDto> toDtos(List<QuestionType> questionTypes) {
		List<QuestionTypeDto> questionTypeDtos = new ArrayList<QuestionTypeDto>();
		for (QuestionType questionType : questionTypes) {
			questionTypeDtos.add(toDto(questionType));
		}
		return questionTypeDtos;
	}

}
