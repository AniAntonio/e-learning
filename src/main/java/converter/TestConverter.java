package converter;

import java.util.ArrayList;
import java.util.List;

import dto.TestDto;
import entities.Test;

public class TestConverter {

	public static TestDto toDto(Test test) {
		TestDto testDto = new TestDto();
		testDto.setId(test.getId());
		testDto.setDuration(test.getDuration());
		testDto.setDescription(test.getDescription());
		testDto.setCourseDto(CourseConverter.toDto(test.getCourse()));
		testDto.setTestDate(test.getTestDate());
		testDto.setQuestions(QuestionConverter.toDtos(test.getQuestions()));
		return testDto;
	}

	public static List<TestDto> toDtos(List<Test> tests) {
		List<TestDto> testDtos = new ArrayList<>();

		for (Test test : tests) {
			testDtos.add(toDto(test));
		}

		return testDtos;
	}
}
