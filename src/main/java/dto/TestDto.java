package dto;

import java.util.List;

import lombok.Data;

@Data
public class TestDto {

	private Long id;

	private String testDate;

	private String duration;

	private String description;

	private CourseDto courseDto;

	private List<QuestionDto> questions;

	private List<Long> questionIds;

	private Long courseId;

}
