package dto;

import java.util.List;

import lombok.Data;

@Data
public class QuestionDto {

	protected Long id;

	private String description;

	private QuestionTypeDto questionType;

	private List<AlternativeDto> alternatives;

}
